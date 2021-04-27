package pl.programistazacny.javadeveloperexercise


import org.testcontainers.containers.GenericContainer

class DynamoDBLocalContainerWrapper {
    private static final String DYNAMODB_LOCAL_IMAGE = "amazon/dynamodb-local:latest"
    private static final Integer DYNAMODB_LOCAL_PORT = 8000

    private static final GenericContainer CONTAINER;

    static {
        CONTAINER = new GenericContainer<>(DYNAMODB_LOCAL_IMAGE)
        CONTAINER.withExposedPorts(DYNAMODB_LOCAL_PORT)
    }

    static String address() {
        start()
        "http://${CONTAINER.getContainerIpAddress()}:${CONTAINER.getMappedPort(DYNAMODB_LOCAL_PORT)}"
    }

    static GenericContainer startAndGet() {
        start()
        CONTAINER
    }

    private static void start() {
        if (!CONTAINER.isRunning()) CONTAINER.start()
    }
}
