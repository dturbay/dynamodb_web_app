package com.aws.rest;

import static com.aws.AppProperties.APP_REGION;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.net.URI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.CreateTableResponse;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableResponse;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;
import software.amazon.awssdk.services.dynamodb.waiters.DynamoDbWaiter;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class MainControllerTest {

  @Value(value = "${local.server.port}")
  private int appPort;

  @Autowired private TestRestTemplate restTemplate;

  @Container
  public static GenericContainer dynamodbContainer =
      new GenericContainer(DockerImageName.parse("amazon/dynamodb-local")).withExposedPorts(8000);

  @DynamicPropertySource
  static void redisProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.dynamodb.test.port", dynamodbContainer::getFirstMappedPort);
  }

  @BeforeAll
  static void setUp() {
    int mappedPort = dynamodbContainer.getMappedPort(8000);
    final String tableName = "Work";
    DynamoDbClient ddb =
        DynamoDbClient.builder()
            .endpointOverride(URI.create(String.format("http://localhost:%d", mappedPort)))
            // The region is meaningless for local DynamoDb but required for client builder
            // validation
            .region(APP_REGION)
            .build();

    DynamoDbWaiter dbWaiter = ddb.waiter();
    CreateTableRequest request =
        CreateTableRequest.builder()
            .attributeDefinitions(
                AttributeDefinition.builder()
                    .attributeName("id")
                    .attributeType(ScalarAttributeType.S)
                    .build())
            .keySchema(KeySchemaElement.builder().attributeName("id").keyType(KeyType.HASH).build())
            .provisionedThroughput(
                ProvisionedThroughput.builder()
                    .readCapacityUnits(10L)
                    .writeCapacityUnits(10L)
                    .build())
            .tableName(tableName)
            .build();

    CreateTableResponse response = ddb.createTable(request);
    DescribeTableRequest tableRequest = DescribeTableRequest.builder().tableName(tableName).build();

    // Wait until the Amazon DynamoDB table is created
    WaiterResponse<DescribeTableResponse> waiterResponse =
        dbWaiter.waitUntilTableExists(tableRequest);
    waiterResponse.matched().response().ifPresent(System.out::println);
  }

  @Test
  void addItem() {
    assertThat(
            this.restTemplate.getForObject(
                "http://localhost:" + appPort + "/api/items", String.class))
        .contains("[]");
  }
}
