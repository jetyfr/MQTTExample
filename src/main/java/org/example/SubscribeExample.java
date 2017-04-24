package org.example;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class SubscribeExample {

	private static final String BROKER_URL = "tcp://iot.eclipse.org:1883";
	private static final String CLIENT_ID = "receiver";
	private static final String TOPIC = "citius/temperature";

	public static void main(String[] args) throws MqttException {
		MemoryPersistence persistence = new MemoryPersistence();
		MqttClient mqttClient = new MqttClient(BROKER_URL, CLIENT_ID, persistence);
		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(false);
		mqttClient.connect(connOpts);
		mqttClient.setCallback(new MqttCallback() {

			public void messageArrived(String topic, MqttMessage message) throws Exception {
				System.out.println("Topic:   " + topic + "\nMessage: " + new String(message.getPayload()));
			}

			public void connectionLost(Throwable cause) {
				System.out.println("Connection to Solace broker lost!" + cause.getMessage());
			}

			public void deliveryComplete(IMqttDeliveryToken token) {
			}

		});
		mqttClient.subscribe(TOPIC, 0);
		System.out.println("Subscribed to:" + TOPIC);
	}

}
