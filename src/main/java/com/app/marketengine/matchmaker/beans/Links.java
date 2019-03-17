package com.app.marketengine.matchmaker.beans;

public class Links {

	String apiLinks;
	String linkDesc;
	String requestType;
	
	public String getApiLinks() {
		return apiLinks;
	}
	public void setApiLinks(String apiLinks) {
		this.apiLinks = apiLinks;
	}
	
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getLinkDesc() {
		return linkDesc;
	}
	public void setLinkDesc(String linkDesc) {
		this.linkDesc = linkDesc;
	}
	public Links(String apiLinks, String requestType, String linkDesc) {
		super();
		this.apiLinks = apiLinks;
		this.linkDesc = linkDesc;
		this.requestType = requestType;
	}
	@Override
	public String toString() {
		return "Links {apiLinks:" + apiLinks + ", linkDesc:" + linkDesc + ", requestType:" + requestType + "}";
	}
	
	
}
