package com.surender.narlakanti.developer.library.model;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BorrowHistory {
	
	public ZonedDateTime getBorrowedOn() {
		return borrowedOn;
	}

	public void setBorrowedOn(ZonedDateTime borrowedOn) {
		this.borrowedOn = borrowedOn;
	}

	public ZonedDateTime getReturnedOn() {
		return returnedOn;
	}

	public void setReturnedOn(ZonedDateTime returnedOn) {
		this.returnedOn = returnedOn;
	}

	public Long getBrorrowHistoryId() {
		return brorrowHistoryId;
	}

	public void setBrorrowHistoryId(Long brorrowHistoryId) {
		this.brorrowHistoryId = brorrowHistoryId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	private ZonedDateTime borrowedOn;
	private ZonedDateTime returnedOn;
	
	@Id
    @GeneratedValue
    private Long brorrowHistoryId;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private Book book;

	@Override
	public String toString() {
		return "BorrowHistory [borrowedOn=" + borrowedOn + ", returnedOn=" + returnedOn + ", brorrowHistoryId="
				+ brorrowHistoryId + ", book=" + book + "]";
	}
	
	public BorrowHistory() {
		
	}

}
