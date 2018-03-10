package com.lachesis.windranger.authentication.model.vo;

import java.io.Serializable;

public class MailDTO implements Serializable {

	/** 
	 * @fields serialVersionUID : 序列号
	 */
	private static final long serialVersionUID = 8388582156417551127L;
	private String from;
	private String to;
	private String subject;
	private String message;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
