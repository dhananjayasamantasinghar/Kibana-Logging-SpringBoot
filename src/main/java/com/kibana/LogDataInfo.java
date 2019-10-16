package com.kibana;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class LogDataInfo implements LogData {

	private String responseCode;
	private Map<String, String> requestHeaders;
	private String requestUrl;
	private String clientId;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public Map<String, String> getRequestHeaders() {
		return requestHeaders;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public static LogDataInfo buildLogDataInfo(String responseCode, String clientId, HttpServletRequest request) {
		LogDataInfo logData = new LogDataInfo();
		logData.setClientId(clientId);
		logData.setRequestHeaders(request);
		logData.setRequestUrl(request);
		logData.setResponseCode(responseCode);
		return logData;
	}

	private void setRequestUrl(HttpServletRequest request) {
		if(request!=null)
			this.requestUrl = request.getRequestURI();
	}

	private void setRequestHeaders(HttpServletRequest request) {
		if(request!=null){
			this.requestHeaders = new LinkedHashMap<>();
			List<String> headerList = Collections.list(request.getHeaderNames());
			headerList.forEach(e -> requestHeaders.put(e, request.getHeader(e)));
		}
	}
}
