package com.surender.narlakanti.developer.library.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "library_details")

public class Library {

	@Id
    @GeneratedValue
    private Long libraryId;
    @NonNull
    private String name;
    private String address;
    private String city;
    private String stateOrProvince;
    private String postalCode;
    
    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="library")
    private List<Book> books;

	public List<Book> getBooks() {
		if(books == null) {
			books = new ArrayList<>();
		}
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void addBookToLibrary(Book book) {
		getBooks().add(book);
		book.setLibrary(this);
	}
	public Library(Long id, String name, String address, String city, String stateOrProvince, 
			
			String postalCode) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.stateOrProvince = stateOrProvince;
		this.postalCode = postalCode;
	}
   
    public Library() {
    	
    }

	@Override
	public String toString() {
		return "Library [libraryId=" + libraryId + ", name=" + name + ", address=" + address + ", city=" + city
				+ ", stateOrProvince=" + stateOrProvince + ", postalCode=" + postalCode + "]";
	}

	public Long getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(Long libraryId) {
		this.libraryId = libraryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateOrProvince() {
		return stateOrProvince;
	}

	public void setStateOrProvince(String stateOrProvince) {
		this.stateOrProvince = stateOrProvince;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
    
}