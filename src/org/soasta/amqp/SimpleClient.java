package org.soasta.amqp;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.soasta.scripting.v1.ResultLevel;
import com.soasta.scripting.v1.ScriptEnvironment;

public class SimpleClient {
	private static ScriptEnvironment environment = null;
	private static ConnectionFactory cfconn = new ConnectionFactory();
	private static Connection conn = null;
	private static Channel channel = null;

	public SimpleClient(ScriptEnvironment inputEnvironment) {
		environment = inputEnvironment;
	}

	public static void setEnvironment(ScriptEnvironment inputEnvironment) {
		environment = inputEnvironment;
	}

	public static void openConnection(String uri) {
		try {
			cfconn.setUri(uri);
			environment
					.getContext()
					.getResult()
					.postMessage(ResultLevel.INFO,
							" Opening Connection to'" + uri + "'");
			conn = cfconn.newConnection();
		} catch (Exception e) {
			environment
					.getContext()
					.getResult()
					.postMessage(ResultLevel.ERROR,
							" Error '" + e.getMessage() + "'");
		}
	}

	public static void openQueue(String queueName) {
		try {
			environment
					.getContext()
					.getResult()
					.postMessage(ResultLevel.INFO,
							" Create Channel to'" + queueName + "'");
			channel = conn.createChannel();
			channel.queueDeclare(queueName, true, false, false, null);
		} catch (Exception e) {
			environment
					.getContext()
					.getResult()
					.postMessage(ResultLevel.ERROR,
							" Error '" + e.getMessage() + "'");
		}
	}

	//If uri is provided assume we are going to open and close connection and queue
	public static void publish(String message, String queueName, String uri) {
			openConnection(uri);
			openQueue(queueName);
			publish(message, queueName);
			closeQueue();
			closeConnection();
	}

	//If since uri is not provided assume we are going going to keep the queue open if it's not already open
	public static void publish(String message, String queueName) {
		try {

			if (channel == null)
				openQueue(queueName);
			environment
					.getContext()
					.getResult()
					.postMessage(ResultLevel.INFO,
							" Send message to queue'" + queueName + "'");
			channel.basicPublish("", queueName, null, message.getBytes());
			environment
					.getContext()
					.getResult()
					.postMessage(ResultLevel.INFO,
							" [x] Sent '" + message + "'");

		} catch (Exception e) {
			environment
					.getContext()
					.getResult()
					.postMessage(ResultLevel.ERROR,
							" Error '" + e.getMessage() + "'");
		}
	}

	public static void closeQueue() {
		try {
			channel.close();
		} catch (IOException e) {
			environment
					.getContext()
					.getResult()
					.postMessage(ResultLevel.ERROR,
							" IOException '" + e.getMessage() + "'");
		} catch (TimeoutException e) {
			environment
					.getContext()
					.getResult()
					.postMessage(ResultLevel.ERROR,
							" TimeoutException '" + e.getMessage() + "'");
		}
	}

	public static void closeConnection() {
		try {
			conn.close();
		} catch (IOException e) {
			environment
					.getContext()
					.getResult()
					.postMessage(ResultLevel.ERROR,
							" IOException '" + e.getMessage() + "'");
		}
	}

}
