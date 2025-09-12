package com.weiboLast.demo;

import com.weiboLast.demo.windowHandle.CustonFormatter;
import com.weiboLast.demo.windowHandle.windowHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.logging.Level;
import java.util.logging.Logger;

@ServletComponentScan
@SpringBootApplication
public class WeiboLastApplication {

	private static final Logger logger = Logger.getLogger("weibo");
	private static final CustonFormatter formatter = new CustonFormatter();
	private static final windowHandler windowHandler = new windowHandler();

	static {
		logger.setLevel(Level.ALL);
		windowHandler.setFormatter(formatter);
		logger.setUseParentHandlers(false);
		logger.addHandler(windowHandler);
	}

	public static void main(String[] args) {
		logger.info("启动工程成功");
		SpringApplication.run(WeiboLastApplication.class, args);
	}

}
