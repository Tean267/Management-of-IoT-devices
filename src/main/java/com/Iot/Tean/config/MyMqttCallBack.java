package com.Iot.Tean.config;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Service
public class MyMqttCallBack implements MqttCallback {

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		   System.out.println("Received message on topic: " + topic);
	       System.out.println("Message: " + new String(message.getPayload()));
		
	}

}
