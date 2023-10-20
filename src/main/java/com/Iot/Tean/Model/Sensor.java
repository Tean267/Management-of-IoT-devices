package com.Iot.Tean.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ss_id")
	private int id;
	private String name;
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "sensor")
//	private List<SensorData> sensorDatas;
//	
}
