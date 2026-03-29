package api;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import config.ConfigReader;

public class OpenAiAgentService {

    private static final String DEFAULT_AGENT_INSTRUCTIONS = """
            You are a QA automation AI agent embedded in a Java Selenium framework.
            Help with test design, bug analysis, smoke scenarios, and concise implementation guidance.
            Prefer actionable answers that can be used by QA engineers immediately.
            """;

    private final OpenAIClient client;
    private final ChatModel model;
    private final String defaultInstructions;

    public OpenAiAgentService() {
        this(createClient(), resolveModel(ConfigReader.getOpenAiModel()), DEFAULT_AGENT_INSTRUCTIONS);
    }

    public OpenAiAgentService(OpenAIClient client, ChatModel model, String defaultInstructions) {
        this.client = client;
        this.model = model;
        this.defaultInstructions = defaultInstructions;
    }

    public String runTask(String userTask) {
        return runTask(defaultInstructions, userTask);
    }

    public String runTask(String instructions, String userTask) {
        if (userTask == null || userTask.isBlank()) {
            throw new IllegalArgumentException("User task must not be blank.");
        }

        ResponseCreateParams params = ResponseCreateParams.builder()
                .model(model)
                .instructions(instructions)
                .input(userTask)
                .build();

        Response response = client.responses().create(params);
        String outputText = extractOutputText(response);

        if (outputText == null || outputText.isBlank()) {
            throw new IllegalStateException("OpenAI agent returned an empty response.");
        }

        return outputText.trim();
    }

    private static OpenAIClient createClient() {
        String apiKey = ConfigReader.getOpenAiApiKey();
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("OpenAI API key is missing. Set OPENAI_API_KEY or openai.api.key.");
        }

        return OpenAIOkHttpClient.builder()
                .apiKey(apiKey)
                .build();
    }

    private static ChatModel resolveModel(String modelName) {
        return switch (modelName.toLowerCase()) {
            case "gpt-4.1" -> ChatModel.GPT_4_1;
            case "gpt-4.1-mini" -> ChatModel.GPT_4_1_MINI;
            case "gpt-4.1-nano" -> ChatModel.GPT_4_1_NANO;
            case "gpt-5", "gpt-5.2" -> ChatModel.GPT_5_2;
            default -> throw new IllegalArgumentException("Unsupported OpenAI model: " + modelName);
        };
    }

    private static String extractOutputText(Response response) {
        String text = response.output().stream()
                .filter(item -> item.message().isPresent())
                .flatMap(item -> item.asMessage().content().stream())
                .filter(content -> content.outputText().isPresent())
                .map(content -> content.asOutputText().text())
                .reduce("", String::concat);

        if (!text.isBlank()) {
            return text;
        }

        return response.output().stream()
                .filter(item -> item.message().isPresent())
                .flatMap(item -> item.asMessage().content().stream())
                .filter(content -> content.refusal().isPresent())
                .map(content -> content.asRefusal().refusal())
                .reduce("", String::concat);
    }
}
