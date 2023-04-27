/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.aws;

// import static net.logstash.logback.argument.StructuredArguments.v;
// import static net.logstash.logback.marker.Markers.append;

import static com.aws.AppProperties.APP_REGION;

import java.net.URI;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.message.ObjectMessage;
import org.apache.logging.log4j.message.StringMapMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

@SpringBootApplication
public class App {

  @Value(value = "${spring.dynamodb.port:-1}")
  private Integer dynamoDbTestPort;

  @Bean
  public DynamoDbClient getClient() {
    Region region = APP_REGION;
      DynamoDbClientBuilder builder = DynamoDbClient.builder().region(region);
    if (this.dynamoDbTestPort != -1) {
      builder.endpointOverride(URI.create(String.format("http://localhost:%d", this.dynamoDbTestPort)));
    }
    return builder.build();
  }

  private static final Logger logger = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) throws Throwable {

    // Logback:
    //        MDC.put("mdcID", "778787");
    //        logger.info("Order saved ({})", "qqqqqqqq", kv("orderId", 123), kv("status",
    // "APPROVED"));
    //        logger.error("exception", new RuntimeException("smth. went wrong"), kv("status",
    //     "REJECTED"));

//    var logger = LogManager.getLogger(App.class);
//    logger.info("Plain text log message");
//    logger.info(
//        new StringMapMessage().with("message", "Hello World!").with("foo", "bar"),
//        new RuntimeException("Smth. happened"));
//
//    logger.info(new ObjectMessage(Map.of("key1", "val1", "key2", "val2")));

    SpringApplication.run(App.class, args);
  }
}
