/**
 * 
 */
package com.prft.notifier.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.prft.notifier.domain.Message;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author praveen.muthusamy
 *
 */
@Component
public class MQConfig {

	private static final Logger logger = LoggerFactory.getLogger(MQConfig.class);
	
	// Rabbit MQ - URL
	@Value("${mquri}")
	private String uri;

	// Exchange Name
	@Value("${exchange}")
	private String exchangeName;

	/**
	 * Method to publish message
	 * @param message
	 */
	public void publishMessage(Message message) {
		logger.info("Start publishMessage");
		Connection connection = null;
		Channel channel = null;
		boolean durable = true; 	// durable - RabbitMQ will never lose the queue if a crash occurs
		boolean exclusive = false; 	// exclusive - if queue only will be used by one connection
		boolean autoDelete = false; // autodelete - queue is deleted when last consumer unsubscribes
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUri(uri);
			factory.setRequestedHeartbeat(30);
			factory.setConnectionTimeout(30000);
			
			logger.info("Open Rabbit MQ connection");
			connection = factory.newConnection();
			
			logger.info("Create channel");
			channel = connection.createChannel();
			
			String[] routingList = message.getGroupIdList().split(",");
			for(String routingKey : routingList){
				String queue = routingKey.replace(".", "_").trim(); 	// queue name
				logger.info("Publish message to queue === "+queue);

				channel.queueDeclare(queue, durable, exclusive, autoDelete, null);
				channel.basicPublish(exchangeName, routingKey, null, message.toString().getBytes());
				
				logger.info(" [x] Sent '" + message.toString() + "'");
			}
			
		} catch (Exception e) {
			logger.error("Error ======> "+e.getMessage());
		} finally {
			if (connection != null) {
				try {
					logger.error("Closing connection on exit");
					connection.close();
				} catch (Exception ignore) {
					logger.error("Error ======> "+ignore.getMessage());
				}
			}
		}
	}
}
