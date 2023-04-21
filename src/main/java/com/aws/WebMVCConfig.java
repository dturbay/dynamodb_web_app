package com.aws;

import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addWebRequestInterceptor(
        new WebRequestInterceptor() {
          @Override
          public void preHandle(WebRequest request) throws Exception {
            var requestId = request.getHeader("x-amzn-RequestId");
            if (requestId != null) {
              MDC.put("x-requestId", requestId);
            }
          }

          @Override
          public void postHandle(WebRequest request, ModelMap model) throws Exception {}

          @Override
          public void afterCompletion(WebRequest request, Exception ex) throws Exception {
            MDC.clear();
          }
        });
  }
}
