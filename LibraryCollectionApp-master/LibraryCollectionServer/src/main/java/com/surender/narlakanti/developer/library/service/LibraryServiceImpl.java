package com.surender.narlakanti.developer.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surender.narlakanti.developer.library.dao.LibraryRepository;
import com.surender.narlakanti.developer.library.model.Library;


@Service
public class LibraryServiceImpl {

	@Autowired
	private LibraryRepository libraryRepository;

	public List<Library> getAllLibraries() {

		return libraryRepository.findAll();

	}

}
