package org.ly.unittest.extension;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.RabbitMQContainer;

public class RabbitMQExtension implements BeforeAllCallback {

    private static final RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:3.7.25-management-alpine");

    @Override
    public void beforeAll(ExtensionContext context) {
        rabbitMQContainer.start();
        System.setProperty("rabbitmq.host", rabbitMQContainer.getHost());
        System.setProperty("rabbitmq.port", rabbitMQContainer.getAmqpPort().toString());
        System.setProperty("rabbitmq.virtual-host", "/");
        System.setProperty("rabbitmq.username", rabbitMQContainer.getAdminUsername());
        System.setProperty("rabbitmq.password", rabbitMQContainer.getAdminPassword());
    }
}