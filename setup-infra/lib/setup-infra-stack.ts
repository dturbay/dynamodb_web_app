import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import * as ecs from 'aws-cdk-lib/aws-ecs';
import * as ecsp from 'aws-cdk-lib/aws-ecs-patterns';

export class SetupInfraStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    new ecsp.ApplicationLoadBalancedFargateService(this, 'webapp', {
      taskImageOptions: {
        image: ecs.ContainerImage.fromRegistry('registry.hub.docker.com/tda123123/tda_dynamo_web_app:0.0.1'),
        containerPort: 8080,
      },
      publicLoadBalancer: true,
      desiredCount: 1,
      cpu: 256,
    });
  }
}
