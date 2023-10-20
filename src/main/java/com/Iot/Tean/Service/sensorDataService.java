package com.Iot.Tean.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.Iot.Tean.Model.SensorData;

public interface sensorDataService {
	@Autowired
	
	List<SensorData> getAllData();
	
	List<Integer> getTempData();
	
	List<Integer> getHumData();
	
	Integer getTempNew();
	
	Integer getHumiNew();
	
	List<SensorData> getArrayData(int from,int to);
	
	SensorData saveData(SensorData sensorData);
	
}
