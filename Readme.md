
Original source code [link](https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2/usecases/creating_dynamodb_web_app)

Added:
- Structured logs (based on logback) (TODO: consider to use standard appenders for structued logging that comes with log4j2/logback)
- CI (build and push image on `push on tags` events) `.github/workflows/docker-image.yml`
- AWS ECS cluster provisioning and app deployment cdk script `setup-infra`
- - `cdk deploy` / `cdk destroy`


### Useful links
- [Google API design approach](https://cloud.google.com/apis/design)
- [AWS CDK blueprints](https://docs.aws.amazon.com/solutions/latest/constructs/aws-fargate-dynamodb.html)

### Commands
- docker build -t tda_dynamo_web_app ./
- curl -X POST -H "Content-Type: application/json" -d '{"name": "dennis", "guide": "Dynamodb basics", "description": "Create sample dynamo db app", "status": "IN_PROGRESS"}' http://localhost:8080/api/items
- curl -X POST -H "Content-Type: application/json" -d '{"name": "dennis", "guide": "Kubernetes basics", "description": "Create sample kubernetes app", "status": "IN_PROGRESS"}' http://localhost:8080/api/items
- curl -s http://localhost:8080/api/items | jq

