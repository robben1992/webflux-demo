package org.ly.unittest.extension;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.MongoDBContainer;

public class MongoDBExtension implements BeforeAllCallback, AfterEachCallback {

    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");

    /**
     * Some Microservices use spring.data.mongodb.uri, others mongodb.uri. Both configuration are injected with
     * Testcontainers MongoDB properties for the sake of compatibility and reusability of this Extension
     * across all microservices.
     */
    @Override
    public void beforeAll(ExtensionContext context) {
        mongoDBContainer.start();
        System.setProperty("spring.data.mongodb.uri", mongoDBContainer.getReplicaSetUrl());
        System.setProperty("spring.data.mongodb.database", "test");
        System.setProperty("mongodb.uri", mongoDBContainer.getReplicaSetUrl());
        System.setProperty("mongodb.database", "test");
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        mongoDBContainer.execInContainer("mongo", "--eval", "db.dropDatabase()", "test");
    }
}