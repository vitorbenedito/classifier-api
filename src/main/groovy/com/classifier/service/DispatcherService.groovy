package com.classifier.service


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import com.classifier.handler.Handler
import com.classifier.lambda.Request
import com.classifier.lambda.Response

import groovy.transform.CompileStatic
import groovy.util.logging.Log4j

@Component
@CompileStatic
@Log4j
class DispatcherService {

  @Autowired
  List<Handler> handlers

  Response dispatch(Request request) {
    log.info("request: ${request}")
    try {
      def handlers = handlers?.findAll { it.route(request) }
      if (handlers?.size() != 1) {
        throw new RuntimeException('Unable to resolve unique handler for route/request')
      }
      final Response response = handlers.first().respond(request)
      response.addHeader('X-Request-Id', request.requestId())
      log.info("response: ${response}")
      return response
    } catch (e) {
      return Response.builder().statusCode(500).body(e.getMessage()).build()
    }
  }
}
