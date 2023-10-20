package com.Iot.Tean.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Iot.Tean.Model.SensorData;

@Repository
public interface sensorDataRepository extends JpaRepository<SensorData,Integer> {
	

	@Query(value = "SELECT temperature\r\n"
			+ "FROM iot.sensor_data\r\n"
			+ "WHERE temperature IS NOT NULL\r\n"
			+ "ORDER BY id DESC\r\n"
			+ "LIMIT 1;\r\n"
			+ " ", nativeQuery = true)
	Integer getTempData();
	
	@Query(value = "SELECT humidity\r\n"
			+ "FROM iot.sensor_data\r\n"
			+ "WHERE humidity IS NOT NULL\r\n"
			+ "ORDER BY id DESC\r\n"
			+ "LIMIT 1;\r\n"
			+ " ", nativeQuery = true)
	Integer getHumiData();
	
	@Query(value = "select * FROM iot.sensor_data ", nativeQuery = true)
	List<SensorData> getAllData();
	
	@Query(value = "select temperature FROM iot.sensor_data ", nativeQuery = true)
	List<Integer> getAllTemp();
	
	@Query(value = "select humidity FROM iot.sensor_data ", nativeQuery = true)
	List<Integer> getAllHumi();
	
	@Query(value = "SELECT * FROM sensor_data  WHERE id BETWEEN ?1 AND ?2 ", nativeQuery = true)
	List<SensorData> getArrayData(int from , int to);
}
