package org.ly.unittest.util;

import org.mockserver.client.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.ly.unittest.extension.MockServerExtension.mockServerContainer;
import static org.mockserver.model.MediaType.APPLICATION_JSON_UTF_8;

public class MockServerUtils {

    private MockServerUtils() {
        throw new UnsupportedOperationException();
    }

    private static final MockServerClient MOCK_SERVER_CLIENT =
            new MockServerClient(mockServerContainer.getHost(), mockServerContainer.getServerPort());

    public static MockServerClient mockServerClient() {
        return MOCK_SERVER_CLIENT;
    }

    public static String mockServerEndpoint() {
        return mockServerContainer.getEndpoint();
    }

    public static void resetMockServer() {
        MOCK_SERVER_CLIENT.reset();
    }

    public static void mockRequest(HttpRequest request, HttpResponse response) {
        MOCK_SERVER_CLIENT.when(request).respond(response);
    }

    public static void mockRequest(HttpRequest request, String responseFilePath) {
        try {
            var response = Files.readString(new ClassPathResource(responseFilePath).getFile().toPath(), UTF_8);
            mockRequest(request, HttpResponse.response(response).withContentType(APPLICATION_JSON_UTF_8));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void mockRequest(String requestPath, String responseFilePath) {
        mockRequest(HttpRequest.request(requestPath), responseFilePath);
    }
}
