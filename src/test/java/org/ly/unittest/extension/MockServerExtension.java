package org.ly.unittest.extension;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.ly.unittest.util.MockServerUtils;
import org.testcontainers.containers.MockServerContainer;

import static org.testcontainers.utility.DockerImageName.parse;

public class MockServerExtension implements AfterEachCallback {

    public static final MockServerContainer mockServerContainer = new MockServerContainer(parse("jamesdbloom/mockserver:mockserver-5.11.2"));

    static {
        mockServerContainer.start();
        System.setProperty("mockserver.host", mockServerContainer.getHost());
        System.setProperty("mockserver.port", mockServerContainer.getServerPort().toString());
        System.setProperty("mockserver.endpoint", mockServerContainer.getEndpoint());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        MockServerUtils.resetMockServer();
    }
}