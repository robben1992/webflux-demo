package org.ly.unittest.extension;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.localstack.LocalStackContainer;

import static org.testcontainers.utility.DockerImageName.parse;

public class AwsS3Extension implements BeforeAllCallback {

    private static final LocalStackContainer localstack = new LocalStackContainer(parse("localstack/localstack:0.11.3"))
            .withServices(LocalStackContainer.Service.S3);

    @Override
    public void beforeAll(ExtensionContext context) {
        localstack.start();
        System.setProperty("aws.access_key_id", localstack.getAccessKey());
        System.setProperty("aws.secret_access_key", localstack.getSecretKey());
        System.setProperty("aws.default_region", localstack.getRegion());
        System.setProperty("aws.s3_overriden_url", localstack.getEndpointOverride(LocalStackContainer.Service.S3).toString());
    }
}