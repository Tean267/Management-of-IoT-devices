package com.Iot.Tean.Controller;

import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Iot.Tean.Model.Light;
import com.Iot.Tean.Model.SensorData;
import com.Iot.Tean.Repository.LightRepository;
import com.Iot.Tean.Service.MqttService;
import com.Iot.Tean.Service.sensorDataService;


@RestController
@RequestMapping("/Iot")
public class sensorDataController {
	
	@Autowired
	MqttService mqttService;
	@Autowired private sensorDataService dataService;
	 @Autowired
	private LightRepository lightRepository;
	
	@CrossOrigin
	@GetMapping("/getAllData")
	public List<SensorData> getAllData() {
		List<SensorData> listAllData = dataService.getAllData();
//		int length = listAllData.size();
//		for(SensorData ssd : listAllData) {
//			ssd.setSensor(null);
//		}
		return listAllData;
		
	}
	@CrossOrigin
	@GetMapping("/getAllTemp")
	public ResponseEntity<List<Integer>> getTempData() {
		List<Integer> listTempData = dataService.getTempData();
		return new ResponseEntity<List<Integer>>(listTempData,HttpStatus.OK);
		
	}
	@CrossOrigin
	@GetMapping("/getTempNew")
	public ResponseEntity<Integer> getTempDataNew() {
		Integer TempDataNew = dataService.getTempNew();
		return new ResponseEntity<Integer>(TempDataNew,HttpStatus.OK);	
	}
	@CrossOrigin
	@GetMapping("/getHumiNew")
	public ResponseEntity<Integer> getTempHumiNew() {
		Integer HumiDataNew = dataService.getHumiNew();
		return new ResponseEntity<Integer>(HumiDataNew,HttpStatus.OK);
		
	}
	@CrossOrigin
	@GetMapping("/getAllHumi")
	public ResponseEntity<List<Integer>> getHumiData() {
		List<Integer> listHumiData = dataService.getHumData();
		return new ResponseEntity<List<Integer>>(listHumiData,HttpStatus.OK);
		
	}
	@CrossOrigin
	@GetMapping("/gettest")
	public ResponseEntity<String> getAlltest() {
		String test ="hello";
		return new ResponseEntity<String>(test,HttpStatus.OK);
		
	}
	@CrossOrigin
	@GetMapping("/getArrayData/{from}/{to}")
	public ResponseEntity<List<SensorData>> getArrayData(@PathVariable("from") int from ,@PathVariable("to") int to) {
		List<SensorData> listAllData = dataService.getArrayData(from,to);
//		for(SensorData ssd : listAllData) {
//			ssd.setSensor(null);
//		}
		return new ResponseEntity<List<SensorData>>(listAllData,HttpStatus.OK);
		
	}
	@CrossOrigin
	@PostMapping("/controlLamp")
	public ResponseEntity<String> onLampRight(@RequestBody String controlConmmand) {
		String s1 = "room/lamp,onlampright";
		String[] str = controlConmmand.replaceAll("\"", "").split(",");
		try {
//			mqttService.sendMessage("room/lamp","offlampright");
			mqttService.sendMessage(str[0], str[1]);
			
//			System.out.println("mqttService.sendMessage("+str[0]+","+str[1]+")");
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(str[0]+" "+str[1]);
//		System.out.println(s1+" ");
//		System.out.println(controlConmmand+" ");
//		System.out.println(str[0]+" "+str[1]);
		return new ResponseEntity<String>(controlConmmand,HttpStatus.OK);
		
	}
	@CrossOrigin
	@GetMapping("/getArrayCommand")
	public ResponseEntity<List<Light>> getArrayCommand() {
		List<Light> listAllCommand = lightRepository.findAll();
//		for(SensorData ssd : listAllData) {
//			ssd.setSensor(null);
//		}
		return new ResponseEntity<List<Light>>(listAllCommand,HttpStatus.OK);
	
	}
	
//	@CrossOrigin
//	@GetMapping("/searchFromColumn/{column}/{data}")
//	public ResponseEntity<List<Light>> getDataFromColumn(@PathVariable("column") String column ,@PathVariable("data") double data) {
//		List<Light> listAllCommand = lightRepository.getArrayCommand(from, to);
////		for(SensorData ssd : listAllData) {
////			ssd.setSensor(null);
////		}
//		return new ResponseEntity<List<Light>>(listAllCommand,HttpStatus.OK);
//	
//	}
	
}
