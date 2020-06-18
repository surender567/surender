package com.surender.narlakanti.developer.library.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surender.narlakanti.developer.library.service.LibraryServiceImpl;

@CrossOrigin
@RestController
public class LibraryController {

	@Autowired
	private LibraryServiceImpl libraryServiceImpl;

	@GetMapping(path = { "/libraries" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllLibraries() {

		return ResponseEntity.ok().body(libraryServiceImpl.getAllLibraries());

	}

}
