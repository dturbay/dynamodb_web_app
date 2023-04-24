
# Refs:
- Original source code [link](https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2/usecases/creating_dynamodb_web_app)

### Useful commands/links
- API design https://cloud.google.com/apis/design
- docker build -t tda_dynamo_web_app ./
- curl -X POST -H "Content-Type: application/json" -d '{"name": "dennis", "guide": "Dynamodb basics", "description": "Create sample dynamo db app", "status": "IN_PROGRESS"}' http://localhost:8080/api/items
- curl -X POST -H "Content-Type: application/json" -d '{"name": "dennis", "guide": "Kubernetes basics", "description": "Create sample kubernetes app", "status": "IN_PROGRESS"}' http://localhost:8080/api/items
- curl -s http://localhost:8080/api/items | jq
- 

### Next steps

Congratulations, you have created a decoupled React application that consumes data from a Spring Boot application. The
Spring Boot application uses the AWS SDK for Java (v2) to invoke AWS services. As stated at the beginning of this
tutorial, be sure to delete all of the resources that you create during this tutorial so that you won't continue to be
charged.

For more AWS multiservice examples, see
[usecases](https://github.com/awsdocs/aws-doc-sdk-examples/tree/master/javav2/usecases).


