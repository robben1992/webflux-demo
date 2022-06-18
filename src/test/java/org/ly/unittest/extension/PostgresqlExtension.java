package org.ly.unittest.extension;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresqlExtension implements BeforeAllCallback, AfterAllCallback {

    private final PostgreSQLContainer postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:9.6.24-bullseye");

    @Override
    public void beforeAll(ExtensionContext extensionContext){
        postgreSQLContainer.start();
        System.setProperty("spring.r2dbc.url", String.format("r2dbc:postgresql://%s:%s/%s", postgreSQLContainer.getContainerIpAddress(),
                postgreSQLContainer.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT), postgreSQLContainer.getDatabaseName()));
        System.setProperty("spring.r2dbc.name", postgreSQLContainer.getDatabaseName());
        System.setProperty("spring.r2dbc.host", postgreSQLContainer.getHost());
        System.setProperty("spring.r2dbc.port", postgreSQLContainer.getFirstMappedPort().toString());
        System.setProperty("spring.r2dbc.username", postgreSQLContainer.getUsername());
        System.setProperty("spring.r2dbc.password", postgreSQLContainer.getPassword());
    }

    @Override
    public void afterAll(ExtensionContext extensionContext){
        postgreSQLContainer.stop();
    }

}
