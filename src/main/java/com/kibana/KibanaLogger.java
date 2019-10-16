package com.kibana;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KibanaLogger {

	private static final Logger logger = LoggerFactory.getLogger(KibanaLogger.class);
	private static final Priority DEFAULT_PRIORITY = Priority.LOW;

	public static void logMessage(String messageId, String message, String messageDescription,LogLevel logLevel, String serviceId,
			String serviceName, String appName, String logCode, String hostName, String env, String domain,
			String clientId, boolean serviceImpact, Priority priority, LogData moreInformation, Throwable exception) {

		String logMessage = KibanaMessage.build()
					 .setMessageId(messageId)
					 .setMessage(message)
					 .setMessageDescription(messageDescription)
					 .setServiceId(serviceId)
					 .setServiceName(serviceName)
					 .setAppName(appName)
					 .setLogCode(logCode)
					 .setHostName(hostName)
					 .setEnv(env)
					 .setDomain(domain)
					 .setClientId(clientId)
					 .setServiceImpact(serviceImpact)
					 .setPriority(priority == null ?DEFAULT_PRIORITY : priority)
					 .setMoreInformation(moreInformation)
					 .toString();
		
		LogLevel level = determineLoglevel(logLevel, logCode, logMessage);

		switch (level) {
		case ERROR:
			if (exception != null) {
				logger.error(logMessage, exception);
			} else {
				logger.error(logMessage);
			}
			//logger.error(logMessage);
			break;
		case WARN:
			logger.warn(logMessage);
			break;

		case INFO:
			logger.info(logMessage);
			break;
		case DEBUG:
			logger.debug(logMessage);
			break;
		default:
			logger.info(logMessage);
			break;
		}
	}

	private static LogLevel determineLoglevel(LogLevel logLevel, String logCode, String logMessage) {
		LogLevel level =null;
		if(logLevel == null && (logMessage != null && logCode!=null)){
			String logFLetter = logCode.substring(0, 1);
			
			switch (logFLetter) {
			case "E":
				level = LogLevel.ERROR;
				break;
				
			case "I":
				level = LogLevel.INFO;
				break;
			case "D":
				level = LogLevel.DEBUG;
				break;
			case "W":
				level = LogLevel.WARN;
				break;
			default:
				level = LogLevel.INFO;
				break;
			}
		}else{
			level= logLevel;
		}
		return level;
	}
}
