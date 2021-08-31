package com.emqx.check;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubCallback implements MqttCallback {
	private final static Logger logger = LoggerFactory.getLogger(SubCallback.class);

	private MqttClient client;
	private String topic;
	

	public SubCallback(MqttClient client,String topic) {
		super();
		this.client = client;
		this.topic=topic;
	}

	public void connectionLost(Throwable cause) {
		logger.error("连接断开，准备重连:"+cause.getMessage());
		try {
			client.reconnect();
			client.subscribe(topic);
			logger.info("重新连接成功");
		} catch (MqttException e) {
			logger.error("重连失败");
			e.printStackTrace();
		}
		
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// TODO Auto-generated method stub
		App.total+=1;
		logger.info("第 "+App.total+" 条接收消息,主题:" + topic+",接收消息Qos:" + message.getQos()+"接收消息内容:" + new String(message.getPayload()));
		
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		
	}

}
