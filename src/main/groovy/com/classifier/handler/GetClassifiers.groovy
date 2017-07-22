package com.classifier.handler


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import com.classifier.lambda.Request
import com.classifier.lambda.Response
import com.classifier.persistence.repository.ClassifierRepository

import groovy.json.JsonOutput
import groovy.transform.CompileStatic
import groovy.util.logging.Log4j

@Component
@Log4j
@CompileStatic
class GetClassifiers implements Handler {

  @Autowired
  private ClassifierRepository classifierRepository

  @Override
  boolean route(final Request request) {
    request.resourcePath() == '/classifiers' && request.httpMethod() == 'GET'
  }

  @Override
  Response respond(final Request request) {
    def allClassifiers = classifierRepository.findAll()
    Response.builder()
        .statusCode(200)
        .body(JsonOutput.prettyPrint(JsonOutput.toJson(allClassifiers)))
        .build()
  }
}
