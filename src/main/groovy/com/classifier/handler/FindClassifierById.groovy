package com.classifier.handler


import com.classifier.lambda.Request
import com.classifier.lambda.Response
import com.classifier.persistence.repository.ClassifierRepository
import groovy.json.JsonOutput
import groovy.transform.CompileStatic
import groovy.util.logging.Log4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@Log4j
@CompileStatic
class FindClassifierById implements Handler {

  @Autowired
  private ClassifierRepository classifierRepository

  @Override
  boolean route(final Request request) {
    request.resourcePath() == '/classifiers/{classifierId}' && request.httpMethod() == 'GET'
  }

  @Override
  Response respond(final Request request) {
    def classifier = classifierRepository.findByClassifierId(request.pathParameter('classifierId'))
    Response.builder()
        .statusCode(200)
        .body(JsonOutput.prettyPrint(JsonOutput.toJson(classifier)))
        .build()
  }
}
