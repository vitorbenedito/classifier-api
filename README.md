# classifier-api

Simple rest API using AWS LAMBD,spring-boot and serverless framework to save and list classifiers. 

## Request example

GET https://wdnc4g5g59.execute-api.us-east-2.amazonaws.com/dev/classifiers


Response:

    [
      {
          "text": "Teste post AWS Lambda",
          "classifierId": "123",
          "languageCode": "pt-BR"
      },
      {
          "text": "TESTANDO",
          "classifierId": "xxx123",
          "languageCode": "pt-BR"
      }
    ]

GET https://wdnc4g5g59.execute-api.us-east-2.amazonaws.com/dev/classifiers/123


Response:

    {
      "text": "Teste post AWS Lambda",
      "classifierId": "123",
      "languageCode": "pt-BR"
    }

POST https://wdnc4g5g59.execute-api.us-east-2.amazonaws.com/dev/classifiers/create


Body:

    {
      "text": "Teste post AWS Lambda",
      "classifierId": "123",
      "languageCode": "pt-BR"
   }

## Configure the Environment variables on ESB and deploy

SERVER_PORT

### Build and Deploy
- To build, run `./gradlew clean build`
- To deploy, run `serverless deploy`
