package com.emqx.check;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.emqx.model.Emqx;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;


public class App {
	private final static Logger logger = LoggerFactory.getLogger(App.class);
	public static long total=0;
	private static Emqx emqx=null;
    public static void main( String[] args ){
    	try {
    		URL url=App.class.getClassLoader().getResource("");
    		YamlReader reader=new YamlReader(new FileReader(url.getPath()+"/config/config.yaml"));
			//YamlReader reader=new YamlReader(new FileReader(System.getProperty("user.dir")+"/config/config.yaml"));
			emqx=reader.read(Emqx.class);
			logger.info("读取配置文件config/config.yaml 成功");
			try {
    			MemoryPersistence persistence = new MemoryPersistence();
    		
    			MqttClient client=new MqttClient(emqx.getBroker(), emqx.getClientId(),persistence);
    			// MQTT 连接选项
                MqttConnectOptions connOpts = new MqttConnectOptions();
                connOpts.setKeepAliveInterval(emqx.getKeepAlive());
            	// 保留会话
                connOpts.setCleanSession(emqx.getCleanSession());
                connOpts.setConnectionTimeout(emqx.getTimeout());
                connOpts.setAutomaticReconnect(emqx.getReconnect());
                
                if(emqx.getUserName()!=null) {
                	connOpts.setUserName(emqx.getUserName());
                }
                if (emqx.getPassword()!=null) {
                	connOpts.setPassword(emqx.getPassword().toCharArray());
				}
                
                // 设置回调
                client.setCallback(new SubCallback(client,emqx.getSubTopic()));
                client.connect(connOpts);
                logger.info("连接成功："+emqx.getBroker());
                // 订阅
                client.subscribe(emqx.getSubTopic(),0);
                
                int date=17;
                while(date>0) {
                	try {
						Thread.currentThread().sleep(24*60*60*1000);
						date--;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }
                client.disconnect();
                logger.info("关闭连接");
                client.close();
                System.exit(0);
                
			} catch (MqttException me) {
				logger.error("reason " + me.getReasonCode());
				logger.error("msg " + me.getMessage());
				logger.error("loc " + me.getLocalizedMessage());
				logger.error("cause " + me.getCause());
				logger.error("excep " + me);
	            me.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("配置文件config/config.yaml 未找到");
		}catch (YamlException e) {
			e.printStackTrace();
			logger.error("读取配置文件config/config.yaml 异常，请检查配置文件");
		}
    	
    	System.out.println( "Hello World!" );
    }
}
