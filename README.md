# SOASTA-CloudTest-JCM-MQClient
AMQP Client Java Custom Module for SOASTA CloudTest is an easy-to-use wrapper around the rabbitmq java library.

##Pre-requisites
Java JDK 1.7

###rabbitmq client API
* rabbitmq-client.jar 3.6

###Soasta Custom Scripting Module API
* cloudtest-plugin-api.jar 
* cloudtest-scripting-environment.jar 

##Export
* rabbitmq-client - Java Module Dependent Library 
* soasta-amqp - Java Module Client Wrapper 
* AMPQ-Publish Clip - Sample Clip that calls AMQP-Publish 
* AMQP-Publish - Sample Script that uses the soasta-amqp JCM to publish a message  

```javascript
importPackage(org.soasta.amqp);
var amqpClient = new SimpleClient($context.createJavaEnvironment());
var message = "Hello World!";
var queueName = "test";
var host ="amqp://server.compute-1.amazonaws.com";
amqpClient.publish(message,queueName,host);
```

##References
https://www.rabbitmq.com/java-client.html
