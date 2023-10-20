package com.Iot.Tean.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Light {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	String command;
	String time;
	public Light(String command, String time) {
		super();
		this.command = command;
		this.time = time;
	}
	
}
