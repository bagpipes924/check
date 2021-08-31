package com.emqx.model;

public class Emqx {
	/**
     * emq服务器地址
     */
    private String broker;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
     */
    private Boolean cleanSession;
    /**
     * 是否断线重连
     */
    private Boolean reconnect;
    /**
     * 连接超时时间
     */
    private Integer timeout;
    /**
     * 心跳间隔
     */
    private Integer keepAlive;
    /**
     * 主题
     */
    private String subTopic;
    
    private String clientId;
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getCleanSession() {
		return cleanSession;
	}
	public void setCleanSession(Boolean cleanSession) {
		this.cleanSession = cleanSession;
	}
	public Boolean getReconnect() {
		return reconnect;
	}
	public void setReconnect(Boolean reconnect) {
		this.reconnect = reconnect;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	public Integer getKeepAlive() {
		return keepAlive;
	}
	public void setKeepAlive(Integer keepAlive) {
		this.keepAlive = keepAlive;
	}
	public String getSubTopic() {
		return subTopic;
	}
	public void setSubTopic(String subTopic) {
		this.subTopic = subTopic;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
    

}
