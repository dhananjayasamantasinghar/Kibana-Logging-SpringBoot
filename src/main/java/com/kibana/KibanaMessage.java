package com.kibana;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KibanaMessage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String messageId;
	private String message;
	private String messageDescription;
	private String serviceId;
	private String serviceName;
	private String appName;
	private String logCode;
	private String hostName;
	private String env;
	private String domain;
	private String clientId;
	private boolean serviceImpact;
	private Priority priority;
	private LogData moreInformation;
	
	public static KibanaMessage build(){
		return new KibanaMessage();
	}
	public KibanaMessage setMessageId(String messageId) {
		this.messageId = messageId;
		return this;
	}
	public KibanaMessage setMessage(String message) {
		this.message = message;
		return this;
	}
	public KibanaMessage setMessageDescription(String messageDescription) {
		this.messageDescription = messageDescription;
		return this;
	}
	public KibanaMessage setServiceId(String serviceId) {
		this.serviceId = serviceId;
		return this;
	}
	public KibanaMessage setServiceName(String serviceName) {
		this.serviceName = serviceName;
		return this;
	}
	public KibanaMessage setAppName(String appName) {
		this.appName = appName;
		return this;
	}
	public KibanaMessage setLogCode(String logCode) {
		this.logCode = logCode;
		return this;
	}
	public KibanaMessage setHostName(String hostName) {
		this.hostName = hostName;
		return this;
	}
	public KibanaMessage setEnv(String env) {
		this.env = env;
		return this;
	}
	public KibanaMessage setDomain(String domain) {
		this.domain = domain;
		return this;
	}
	public KibanaMessage setClientId(String clientId) {
		this.clientId = clientId;
		return this;
	}
	public KibanaMessage setServiceImpact(boolean serviceImpact) {
		this.serviceImpact = serviceImpact;
		return this;
	}
	public KibanaMessage setPriority(Priority priority) {
		this.priority = priority;
		return this;
	}
	public KibanaMessage setMoreInformation(LogData moreInformation) {
		this.moreInformation = moreInformation;
		return this;
	}
	
	public String getMessageId() {
		return messageId;
	}
	public String getMessage() {
		return message;
	}
	public String getMessageDescription() {
		return messageDescription;
	}
	public String getServiceId() {
		return serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public String getAppName() {
		return appName;
	}
	public String getLogCode() {
		return logCode;
	}
	public String getHostName() {
		return hostName;
	}
	public String getEnv() {
		return env;
	}
	public String getDomain() {
		return domain;
	}
	public String getClientId() {
		return clientId;
	}
	public boolean isServiceImpact() {
		return serviceImpact;
	}
	public Priority getPriority() {
		return priority;
	}
	public LogData getMoreInformation() {
		return moreInformation;
	}
	@Override
	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// No Code
		}
		return super.toString();
	}
	
	
}
