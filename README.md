# SOASTA-CloudTest-JCM-MQClient
AMQP Client Java Custom Module for SOASTA CloudTest is an easy-to-use wrapper around the rabbitmq java library.

##Pre-requisites
Java JDK 1.7

###rabbitmq client API
* rabbitmq-client.jar 3.6 1

###Soasta Custom Scripting Module API
* cloudtest-plugin-api.jar 1
* cloudtest-scripting-environment.jar 2

##Export
* rabbitmq-client - Java Module Dependent Library 1
* soasta-amqp - Java Module Client Wrapper 2
* AMPQ-Publish Clip - Sample Clip that calls AMQP-Publish 3
* AMQP-Publish - Sample Script that uses the soasta-amqp JCM to publish a message 4 

##References
https://www.rabbitmq.com/java-client.html
