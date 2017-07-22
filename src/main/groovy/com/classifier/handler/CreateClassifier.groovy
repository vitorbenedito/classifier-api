package com.classifier.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import com.classifier.lambda.Request
import com.classifier.lambda.Response
import com.classifier.persistence.entity.Classifier
import com.classifier.persistence.repository.ClassifierRepository

import groovy.json.JsonOutput
import groovy.transform.CompileStatic
import groovy.util.logging.Log4j

@Component
@Log4j
@CompileStatic
class CreateClassifier implements Handler {

  @Autowired
  private ClassifierRepository classifierRepository

  @Override
  boolean route(Request request) {
    request.resourcePath() == '/classifiers/create' && request.httpMethod() == 'POST'
  }

  @Override
  Response respond(final Request request) {
    def classifierId = request.body('classifierId')
    def text = request.body('text')
	def languageCode = request.body('languageCode')
	
    def classifier = new Classifier(classifierId, text, languageCode)
	
    classifierRepository.save(classifier)
    Response.builder()
        .statusCode(201)
        .body(JsonOutput.prettyPrint(JsonOutput.toJson(classifier)))
        .build()
  }


}
