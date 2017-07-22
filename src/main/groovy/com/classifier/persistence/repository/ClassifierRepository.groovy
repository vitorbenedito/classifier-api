package com.classifier.persistence.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

import com.classifier.persistence.entity.Classifier

import groovy.transform.CompileStatic

@CompileStatic
@Repository
interface ClassifierRepository extends CrudRepository<Classifier, String> {

  Classifier findByClassifierId(String classifierId)
}