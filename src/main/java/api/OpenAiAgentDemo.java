package api;

public class OpenAiAgentDemo {

    public static void main(String[] args) {
        OpenAiAgentService agentService = new OpenAiAgentService();

        String task = args.length > 0
                ? String.join(" ", args)
                : "Create a short smoke checklist for the login page.";

        String result = agentService.runTask(task);
        System.out.println(result);
    }
}
