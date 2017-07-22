package com.classifier.lambda

import com.amazonaws.services.lambda.runtime.Context

import groovy.json.JsonSlurper
import groovy.transform.ToString

@ToString(includePackage = false)
class Request {
  private final Map input
  private final Context context
  private final Map body

  Request(final Map input, final Context context) {
    this.input = input
    this.context = context
	if(input?.body)
		this.body = new JsonSlurper().parseText(input?.body)
  }

  String requestId() {
    context?.awsRequestId
  }

  String resourcePath() {
    input?.resource ?: 'unknown'
  }

  String httpMethod() {
    input?.httpMethod ?: 'unknown'
  }

  String queryString(String name) {
    input?.queryStringParameters?."${name}"?.trim()
  }

  String pathParameter(String name) {
    input?.pathParameters?."${name}"?.trim()
  }
  
  String body(String name) {
	  this.body?."${name}"?.trim()
  }
}
