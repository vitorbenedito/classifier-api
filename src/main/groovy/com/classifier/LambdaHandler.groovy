package com.classifier

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.ComponentScan

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.classifier.lambda.Request
import com.classifier.lambda.Response
import com.classifier.service.DispatcherService

import groovy.transform.Memoized
import groovy.util.logging.Log4j

@SpringBootApplication
@ComponentScan(basePackages = "com.classifier")
@Log4j
class LambdaHandler implements RequestHandler<Map, Response> {

  static void main(String[] args) throws Exception {
    LambdaHandler.newInstance().getApplicationContext(args)
  }

  @Memoized
  ApplicationContext getApplicationContext(String[] args = []) {
    return SpringApplication.run(LambdaHandler.class, args)
  }

  @Override
  Response handleRequest(Map input, Context context) {
    final Request request = new Request(input, context)
    DispatcherService dispatcher = getApplicationContext().getBean(DispatcherService.class)
    return dispatcher.dispatch(request)
  }
}
