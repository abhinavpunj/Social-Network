package com.social.network.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Inbox")
public class Inbox {

	@Id	@GeneratedValue
	@Column(name = "InboxId")
	private int inboxId;
	
	@Column(name = "Message")
	private String message;
	
	//Inbox-->Person
	@ManyToOne
	@JoinColumn(name = "toUser")
	private Person toUser;

	//Inbox-->Person
	@ManyToOne
	@JoinColumn(name = "fromUser")
	private Person fromUser;
	
	@Column(name = "isRead")
	private boolean isRead;
	
	@Column(name = "DateOfMessage")
	private Date dateOfMessage;
	
	public Date getDateOfMessage() {
		return dateOfMessage;
	}

	public void setDateOfMessage(Date dateOfMessage) {
		this.dateOfMessage = dateOfMessage;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public int getInboxId() {
		return inboxId;
	}

	public void setInboxId(int inboxId) {
		this.inboxId = inboxId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Person getToUser() {
		return toUser;
	}

	public void setToUser(Person toUser) {
		this.toUser = toUser;
	}

	public Person getFromUser() {
		return fromUser;
	}

	public void setFromUser(Person fromUser) {
		this.fromUser = fromUser;
	}

	
}
