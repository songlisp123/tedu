package com._06article.demo;

import com._06article.demo.windowHandle.CustonFormatter;
import com._06article.demo.windowHandle.windowHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class Application {
	private static final Logger logger = Logger.getLogger("article");
	private static final windowHandler windowHandler = new windowHandler();
	private static final CustonFormatter formatter = new CustonFormatter();

	static {
		logger.setLevel(Level.ALL);
		windowHandler.setFormatter(formatter);
		logger.setUseParentHandlers(false);
		logger.addHandler(windowHandler);
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
