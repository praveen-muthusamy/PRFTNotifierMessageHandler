package com.prft.notifier.controller;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prft.notifier.config.MQConfig;
import com.prft.notifier.domain.Message;
 
@RestController
public class NotifierRESTController 
{
	
	private static final Logger logger = LoggerFactory.getLogger(NotifierRESTController.class);
	
	@Autowired
	MQConfig mqConfig;
   
	/**
	 * Method to broadcast message
	 * @param message
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/rest/publish")
	public @ResponseBody String broadcastMessage(@RequestBody Message message){
		logger.info("Start broadcastMessage");
		mqConfig.publishMessage(message);
		logger.info("Exit broadcastMessage");
		return message.toString();
   }
}