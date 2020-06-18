package com.surender.narlakanti.developer.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.surender.narlakanti.developer.library.dao.LibraryRepository;
import com.surender.narlakanti.developer.library.model.Library;

@Component
class LibraryInitializer implements CommandLineRunner {
    
	
	@Autowired
	LibraryRepository libraryRepository;

    @Override
    public void run(String... strings) {
         
    	Library library = new Library(1L,"John Doe Library","420 Texas Academy","Dallas","TX","00023");
    	libraryRepository.save(library);
    	libraryRepository.findAll().forEach(System.out::println);
    }
}