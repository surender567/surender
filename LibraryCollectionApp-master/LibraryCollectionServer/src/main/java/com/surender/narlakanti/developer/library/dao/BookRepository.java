package com.surender.narlakanti.developer.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.surender.narlakanti.developer.library.model.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	

}