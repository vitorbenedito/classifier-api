package com.classifier.persistence.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

import groovy.transform.CompileStatic
import groovy.transform.ToString

@Entity
@ToString
@CompileStatic
class Classifier {

  @Id
  String classifierId
  
  String text
  String languageCode

  Classifier(){}

  Classifier(String classifierId, String text, String languageCode) {
    this.classifierId = classifierId
    this.text = text
	this.languageCode = languageCode
  }
}