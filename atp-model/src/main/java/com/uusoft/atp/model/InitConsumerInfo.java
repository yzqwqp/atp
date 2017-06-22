package com.uusoft.atp.model;

public class InitConsumerInfo {
	/**
     * consumerid
     */
	private int consumerid;
	/**
     * 服务名称
     */
	private String servername;
	/**
     * 接口全称
     */
	private String interfacename;
	/**
     * 版本信息
     */
	private String version;
	/**
     * generic。泛化调用开启
     */
	private String generic;
	/**
     * owner
     */
	private String owner;
	public int getConsumerid() {
		return consumerid;
	}
	public void setConsumerid(int consumerid) {
		this.consumerid = consumerid;
	}
	
	public String getServername() {
		return servername;
	}
	public void setServername(String servername) {
		this.servername = servername;
	}
	public String getInterfacename() {
		return interfacename;
	}
	public void setInterfacename(String interfacename) {
		this.interfacename = interfacename;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getGeneric() {
		return generic;
	}
	public void setGeneric(String generic) {
		this.generic = generic;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
}
