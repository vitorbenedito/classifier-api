package com.classifier.handler

import com.classifier.lambda.Request
import com.classifier.lambda.Response

interface Handler {
  boolean route(Request request)
  Response respond(Request request)
}