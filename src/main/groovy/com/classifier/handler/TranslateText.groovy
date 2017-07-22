package com.classifier.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import com.classifier.lambda.Request
import com.classifier.lambda.Response
import com.classifier.persistence.repository.ClassifierRepository

import groovy.transform.CompileStatic
import groovy.util.logging.Log4j
import java.io.BufferedReader

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
	
	URL url = new URL("http://g1.globo.com")
	HttpURLConnection con = (HttpURLConnection) url.openConnection()
  
	
	// By default it is GET request
	con.setRequestMethod("GET")
   
	int responseCode = con.getResponseCode()
	System.out.println("Sending get request : "+ url)
	System.out.println("Response code : "+ responseCode)
   
	// Reading response from input Stream
	 BufferedReader stream = new BufferedReader(new InputStreamReader(con.getInputStream()));
	String output
	StringBuffer response = new StringBuffer()
   
	while ((output = stream.readLine()) != null) {
	 response.append(output);
	}
	stream.close();
   
	//printing result from response
	System.out.println(response.toString());
	
//	HttpGet getRequest = new HttpGet(URIUtil.encodeQuery(googleRequest))
//	HttpClient client = HttpClientBuilder.create().build();
//	println "Request to google: " + googleRequest
//	HttpResponse response = client.execute(getRequest);
//	String translateText = response.getEntity().toString();
//	println "Text: " + text
	
    Response.builder()
        .statusCode(201)
        .body(response.toString())
        .build()
  }


}
