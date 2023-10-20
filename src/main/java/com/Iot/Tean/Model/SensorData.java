package com.Iot.Tean.Model;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.CollectionId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private float temperature;
	private float humidity;
	private float light;
	private float mua;
	private String time;
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "ss_id", referencedColumnName = "ss_id")
//	private Sensor sensor;
//	public SensorData(int id, float temperature, float humidity, float light, String time) {
//		super();
//		this.id = id;
//		this.temperature = temperature;
//		this.humidity = humidity;
//		this.light = light;
//		this.time = time;
//	}
	public SensorData( float temperature, float humidity, float light,float mua, String time) {
		super();
		this.temperature = temperature;
		this.humidity = humidity;
		this.light = light;
		this.mua = mua;
		this.time = time;
	}
	
}
