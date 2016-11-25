/**
 * 
 */
package com.prft.notifier.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author praveen.muthusamy
 *
 */
@XmlRootElement(name = "message")
@XmlAccessorType (XmlAccessType.FIELD)
public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String groupIdList;
	private String message;
	private String status;
	private String userName;
	
	/**
	 * Constructor
	 * @param groupIdList
	 * @param message
	 * @param status
	 * @param userName
	 */
	public Message(String groupIdList, String message, String status, String userName) {
		super();
		this.groupIdList = groupIdList;
		this.message = message;
		this.status = status;
		this.userName = userName;
	}

	/**
	 * Default constructor
	 */
	public Message() {
		
	}


	/**
	 * @return the groupIdList
	 */
	public String getGroupIdList() {
		return groupIdList;
	}



	/**
	 * @param groupIdList the groupIdList to set
	 */
	public void setGroupIdList(String groupIdList) {
		this.groupIdList = groupIdList;
	}



	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}



	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}



	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}



	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}



	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String toString(){
		return "{\"Name\" : \""+this.userName+"\", \"message\" :\""+this.message+"\"}";
		
	}
	
}
