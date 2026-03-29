package listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Attachment;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.function.Supplier;

final class AllureAttachmentSupport {

    private static final String ALLURE_TEST_CASE_UUID_ATTRIBUTE = "allure.testCaseUuid";

    private AllureAttachmentSupport() {
    }

    static void captureTestCaseUuid(ITestResult result) {
        if (result.getAttribute(ALLURE_TEST_CASE_UUID_ATTRIBUTE) instanceof String) {
            return;
        }

        Allure.getLifecycle()
                .getCurrentTestCase()
                .ifPresent(uuid -> result.setAttribute(ALLURE_TEST_CASE_UUID_ATTRIBUTE, uuid));
    }

    static boolean addByteAttachment(ITestResult result, String name, String type, String extension, byte[] content) {
        if (content == null || content.length == 0) {
            return false;
        }

        return addAttachment(result, name, type, extension, () -> new ByteArrayInputStream(content));
    }

    static boolean addTextAttachment(ITestResult result, String name, String content) {
        byte[] textBytes = safe(content).getBytes(StandardCharsets.UTF_8);
        return addAttachment(result, name, "text/plain", ".txt", () -> new ByteArrayInputStream(textBytes));
    }

    private static boolean addAttachment(
            ITestResult result,
            String name,
            String type,
            String extension,
            Supplier<InputStream> contentSupplier
    ) {
        captureTestCaseUuid(result);

        AllureLifecycle lifecycle = Allure.getLifecycle();
        if (lifecycle.getCurrentTestCase().isPresent()) {
            lifecycle.addAttachment(name, type, normalizeExtension(extension), contentSupplier.get());
            return true;
        }

        String testCaseUuid = resolveTestCaseUuid(result);
        if (testCaseUuid == null || testCaseUuid.isBlank()) {
            return false;
        }

        if (lifecycle.setCurrentTestCase(testCaseUuid)) {
            lifecycle.addAttachment(name, type, normalizeExtension(extension), contentSupplier.get());
            return true;
        }

        String source = UUID.randomUUID() + "-attachment" + normalizeExtension(extension);
        lifecycle.updateTestCase(
                testCaseUuid,
                testResult -> testResult.getAttachments().add(
                        new Attachment()
                                .setName(name)
                                .setType(type)
                                .setSource(source)
                )
        );
        lifecycle.writeAttachment(source, contentSupplier.get());
        return true;
    }

    private static String resolveTestCaseUuid(ITestResult result) {
        Object attribute = result.getAttribute(ALLURE_TEST_CASE_UUID_ATTRIBUTE);
        return attribute instanceof String uuid ? uuid : null;
    }

    private static String normalizeExtension(String extension) {
        if (extension == null || extension.isBlank()) {
            return "";
        }

        return extension.startsWith(".") ? extension : "." + extension;
    }

    private static String safe(String value) {
        return value == null ? "" : value;
    }
}
