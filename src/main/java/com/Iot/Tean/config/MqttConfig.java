package com.Iot.Tean.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {
//	    private String brokerUrl;
//
//	    private String clientId;

	    @Bean
	    public MqttClient mqttClient() throws MqttException {
	        MqttConnectOptions options = new MqttConnectOptions();
	        options.setAutomaticReconnect(true);

	        MqttClient mqttClient = new MqttClient("tcp://192.168.43.12", "Tean");
	        mqttClient.connect(options);

	        return mqttClient;
	    }
}
