/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.aws;

// import static net.logstash.logback.argument.StructuredArguments.v;
// import static net.logstash.logback.marker.Markers.append;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
  private static final Logger logger = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) throws Throwable {

    //    MDC.put("mdcID", "778787");
    //    logger.info("Order saved ({})", "qqqqqqqq", kv("orderId", 123), kv("status", "APPROVED"));
    //    logger.error("exception", new RuntimeException("smth. went wrong"), kv("status",
    // "REJECTED"));

    SpringApplication.run(App.class, args);
  }
}
