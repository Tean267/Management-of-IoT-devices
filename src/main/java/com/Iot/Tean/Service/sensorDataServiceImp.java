package com.Iot.Tean.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import com.Iot.Tean.ProjectIotApplication;
import com.Iot.Tean.Model.SensorData;
import com.Iot.Tean.Repository.sensorDataRepository;

@Service 
public class sensorDataServiceImp implements sensorDataService{
	@Autowired private  sensorDataRepository dataRepository;
	@Override 
	public List<SensorData> getAllData() {
		return dataRepository.getAllData();
	}

	@Override
	public List<Integer> getTempData() {
		return dataRepository.getAllTemp();
	}

	@Override
	public List<Integer> getHumData() {
		return dataRepository.getAllHumi();
	}

	@Override
	public Integer getTempNew() {
		return dataRepository.getTempData();
	}

	@Override
	public Integer getHumiNew() {
		return dataRepository.getHumiData();
	}

	@Override
	public List<SensorData> getArrayData(int from,int to) {
		
		return dataRepository.getArrayData(from,to);
	}

	@Override
	public SensorData saveData(SensorData sensorData) {
		return dataRepository.save(sensorData);
		
	}

	
	

}

