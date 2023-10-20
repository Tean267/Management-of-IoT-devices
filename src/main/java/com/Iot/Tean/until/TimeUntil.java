package com.Iot.Tean.until;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;


public class TimeUntil {
	 private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	    public static String getCurrentFormattedTime() {
	        
	        LocalDateTime currentTime = LocalDateTime.now();
	        return currentTime.format(formatter);
	    }
}
