package com.kibana;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.logging.LogLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

@RestController
class ELKController {

	@Autowired
	RestTemplate restTemplete;

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@RequestMapping(value = "/elkdemo")
	public String helloWorld() {
		String response = "Hello user ! " + new Date();
		log("Information", "elk trying","SUCCESS", 
				LogLevel.INFO, "Service-1", "Test-Service",
				"TEst-App", "I5098", "test", "dev", "test", "356789", true, Priority.NORMAL,
				LogDataInfo.buildLogDataInfo("200", "356789", null),null);

		return response;
	}

	@RequestMapping(value = "/elk")
	public String helloWorld1() {

		String response = restTemplete.exchange("http://localhost:8080/elkdemo", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		}).getBody();
		try {
			String exceptionrsp = restTemplete.exchange("http://localhost:8080/exception", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
			}).getBody();
			//LOG.log(Level.INFO, "/elk trying to print exception - > " + exceptionrsp);
			response = response + " === " + exceptionrsp;
			
			log("Information", "elk trying", "SUCCESS", 
					LogLevel.INFO, "Service-1", "Test-Service",
					"TEst-App", "I5011", "test", "dev", "test", "356789", true, Priority.NORMAL,
					LogDataInfo.buildLogDataInfo("200", "356789", null),null);
			
		} catch (Exception e) {
			log("Error-1", e.getMessage(), "INTERNAL SERVER ERROR", LogLevel.ERROR, "Service-1", "Test-Service",
					"TEst-App", "E5098", "test", "dev", "test", "356789", true, Priority.CRITICAL,
					LogDataInfo.buildLogDataInfo("500", "356789", null), e);
		}

		return response;
	}

	@RequestMapping(value = "/exception")
	public String exception() {
		String rsp = "";
			System.out.println(10/0);
		return rsp;
	}
	
	
	private void log(String messageId, String message, String messageDescription,LogLevel logLevel, String serviceId,
			String serviceName, String appName, String logCode, String hostName, String env, String domain,
			String clientId, boolean serviceImpact, Priority priority, LogData moreInformation, Throwable exception){
		KibanaLogger.logMessage(messageId, message, messageDescription, logLevel, serviceId, serviceName, appName,
				logCode, hostName, env, domain, clientId, serviceImpact, priority, moreInformation, exception);
		
	}
	
	
}
