package com.Iot.Tean.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Iot.Tean.Model.Light;
import com.Iot.Tean.Model.SensorData;
import com.Iot.Tean.Repository.LightRepository;
import com.Iot.Tean.until.TimeUntil;

@Service
public class MqttService {
	@Autowired
	private sensorDataService dataService;
	 @Autowired
	    private MqttClient mqttClient;
	 
	 
	 @Autowired
	private LightRepository lightRepository;
	 public void sendMessage(String topic, String message) throws MqttException {
	        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
	        mqttClient.publish(topic, mqttMessage);
	        lightRepository.save(new Light(message,TimeUntil.getCurrentFormattedTime()));
	    }
	 public void receiveMessage(String topic) throws MqttException {
		
	        //Vì vậy, khi bạn sử dụng setCallback, bạn đang thiết lập một callback chung cho toàn bộ MQTT client
	        //để xử lý các sự kiện chung. Khi bạn sử dụng subscribe với một biểu thức lambda hoặc 
	        //IMqttMessageListener, bạn đang đăng ký một callback cụ thể cho một chủ đề cụ thể để xử lý 
	        //tin nhắn đến cho chủ đề đó
//	        mqttClient.setCallback(new MyMqttCallBack() {
//	        	@Override
//	        	public void messageArrived(String topic, MqttMessage message) throws Exception {
//	        		   System.out.println("Received message on topic: " + topic);
//	        	       System.out.println("Message: " + new String(message.getPayload()));
//	        		
//	        	}
//
//	        });
		

//			mqttClient.subscribe(topic, new IMqttMessageListener() {
//				 @Override
//				    public void messageArrived(String topic, MqttMessage message) throws Exception {
//				        myMqttaCallBack.messageArrived(topic, message);
//				    }
//				}
//			);
//	        mqttClient.subscribe(topic, (t, m) -> System.out.println("Temperature: " + new String(m.getPayload()) + "°C"));
	        mqttClient.subscribe(topic, (t, m)->{
//	        	System.out.println("Temperature: " + new String(m.getPayload()) + "°C");
	        	try {
	                String[] room = new String(m.getPayload()).split(",");
	                // Xử lý và lưu dữ liệu vào cơ sở dữ liệu
	                dataService.saveData(new SensorData( Float.parseFloat(room[0]),Float.parseFloat(room[1]),  Float.parseFloat(room[2]),Float.parseFloat(room[3]), TimeUntil.getCurrentFormattedTime()));
	            } catch (Exception e) {
	                e.printStackTrace();
	                // Ghi log hoặc xử lý lỗi theo nhu cầu của bạn
	            }
	        });
	    }
}
