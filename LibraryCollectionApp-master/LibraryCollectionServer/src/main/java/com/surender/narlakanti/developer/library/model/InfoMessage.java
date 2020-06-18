/**
 * 
 */
package com.surender.narlakanti.developer.library.model;

import com.surender.narlakanti.developer.library.util.Operations;

/**
 * @author kappera
 *
 */

public class InfoMessage {

	private Operations operationType;

	private String description;

	public InfoMessage(Operations operationType, String description) {
		super();
		this.operationType = operationType;
		this.description = description;
	}

	public InfoMessage() {

	}

	public Operations getOperationType() {
		return operationType;
	}

	public void setOperationType(Operations operationType) {
		this.operationType = operationType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * @JsonValue public String getJsonValue() { return
	 * String.format("{'operationType':%s,'description':%s}", operationType.name(),
	 * description); }
	 */

}
