package com.classifier.handler

import org.apache.commons.httpclient.util.URIUtil
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import com.classifier.lambda.Request
import com.classifier.lambda.Response
import com.classifier.persistence.repository.ClassifierRepository

import groovy.transform.CompileStatic
import groovy.util.logging.Log4j

@Component
@Log4j
@CompileStatic
class TranslateText implements Handler {
	
  private static String GOOGLE_TRANSLATE_URL = "https://translation.googleapis.com/language/translate/v2"
  private static String GOOGLE_KEY = "AIzaSyB-dFJTwnRrwjae1SSO3sS-r6QN3aVRqXE"
  private static String GOOGLE_DEFAULT_SOURCE = "en"
  
  @Autowired
  private ClassifierRepository classifierRepository

  @Override
  boolean route(Request request) {
    request.resourcePath() == '/classifiers/translate/{classifierId}/{languageCode}' && request.httpMethod() == 'GET'
  }

  @Override
  Response respond(final Request request) {
	def classifier = classifierRepository.findByClassifierId(request.pathParameter('classifierId'))
    println "Form request to google"
	String text = classifier?.text?.trim()
	String googleRequest = GOOGLE_TRANSLATE_URL + "/?key=" + GOOGLE_KEY + "&q=" + text + "&source=" + GOOGLE_DEFAULT_SOURCE + "&target=" + request.pathParameter('languageCode')
	
	HttpGet getRequest = new HttpGet(URIUtil.encodeQuery(googleRequest))
	HttpClient client = HttpClientBuilder.create().build();
	println "Request to google: " + googleRequest
	HttpResponse response = client.execute(getRequest);
	String translateText = response.getEntity().toString();
	println "Text: " + text
	
    Response.builder()
        .statusCode(201)
        .body(translateText)
        .build()
  }


}
