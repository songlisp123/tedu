package com.cloudCar.demo;

import com.cloudCar.demo.windowHandle.CustonFormatter;
import com.cloudCar.demo.windowHandle.windowHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class CloudCarApplication {

	private static final Logger logger = Logger.getLogger("car");
	private static final windowHandler windowHandler = new windowHandler();
	private static final CustonFormatter formatter = new CustonFormatter();

	static {
		logger.setLevel(Level.ALL);
		windowHandler.setFormatter(formatter);
		logger.setUseParentHandlers(false);
		logger.addHandler(windowHandler);
	}

	public static void main(String[] args) {
		SpringApplication.run(CloudCarApplication.class, args);
	}

}
