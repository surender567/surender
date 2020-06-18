package com.surender.narlakanti.developer.library.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	private String username;
	private int phone;
	private int userID;
	
	 public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	@JsonIgnore
	    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="book")
	    private List<Book> books;

	@Override
	public String toString() {
		return "User [username=" + username + ", phone=" + phone + ", books=" + books + "]";
	}
	 
	 

}
