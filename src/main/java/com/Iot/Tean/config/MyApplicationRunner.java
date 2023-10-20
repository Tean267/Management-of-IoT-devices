package com.Iot.Tean.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.Iot.Tean.Model.SensorData;
import com.Iot.Tean.Service.MqttService;
import com.Iot.Tean.Service.sensorDataService;

@Component
public class MyApplicationRunner implements ApplicationRunner {
	@Autowired
	private sensorDataService dataService;
	@Autowired
    private MqttService mqttClient;	
    @Override
    public void run(ApplicationArguments args) throws Exception {
    	
//	        mqttClient.subscribe(topic);
	        //Vì vậy, khi bạn sử dụng setCallback, bạn đang thiết lập một callback chung cho toàn bộ MQTT client
	        //để xử lý các sự kiện chung. Khi bạn sử dụng subscribe với một biểu thức lambda hoặc 
	        //IMqttMessageListener, bạn đang đăng ký một callback cụ thể cho một chủ đề cụ thể để xử lý 
	        //tin nhắn đến cho chủ đề đó
//	        mqttClient.setCallback(myMqttaCallBack);
//    	mqttClient.sendMessage("room/lamp", "offlampright");
	        mqttClient.receiveMessage("room/temperature");
	       
//    	dataService.saveData(new SensorData(15,80,100,"6:25"));
	     
    }
}