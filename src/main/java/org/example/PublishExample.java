package org.example;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PublishExample {

	private static final String BROKER_URL = "tcp://iot.eclipse.org:1883";
	private static final String CLIENT_ID = "sender";
	private static final String TOPIC = "citius/temperature";
	private static final String MESSAGE_CONTENT = "51.5 ºC";

	public static void main(String[] args) {
		try {
			MqttClient client = new MqttClient(BROKER_URL, CLIENT_ID);
			client.connect();
			MqttMessage message = new MqttMessage();
			message.setPayload(MESSAGE_CONTENT.getBytes());
			message.setQos(2);
			client.publish(TOPIC, message);
			client.disconnect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}
