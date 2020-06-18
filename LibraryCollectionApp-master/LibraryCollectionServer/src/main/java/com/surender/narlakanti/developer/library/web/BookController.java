package com.surender.narlakanti.developer.library.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.surender.narlakanti.developer.library.model.Book;
import com.surender.narlakanti.developer.library.service.BookServiceImpl;

@CrossOrigin
@RestController
public class BookController {

	@Autowired
	private BookServiceImpl bookServiceImpl;

	public BookController() {
	}

	@GetMapping(path = { "/libraries/{id}/books", "/libraries/books" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllBooks(@PathVariable("id") Optional<Long> library_id) {

		return ResponseEntity.ok().body(bookServiceImpl.getAllBooks(library_id));

	}

	@GetMapping("/books/{book_id}")
	public ResponseEntity<?> getBookFromLibrary(@PathVariable("book_id") Long book_id) {
		Optional<Book> book = bookServiceImpl.getBookFromLibrary(book_id);

		if (book == null) {
			return new ResponseEntity<>("Book not available with this id", HttpStatus.OK);
		} else {
			return ResponseEntity.ok().body(book);
		}
	}

	@PostMapping(path = { "/libraries/{id}/book", "/libraries/book" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addBookToLibrary(@PathVariable("id") Optional<Long> library_id, @RequestBody Book book) {

		Book bookData = bookServiceImpl.addBookToLibrary(library_id, book);

		return bookData.getId() != 0 ? ResponseEntity.ok().body(bookData)
				: new ResponseEntity<>("No library with given id", HttpStatus.ACCEPTED);

	}

	@PutMapping(path = { "/libraries/{id}/updatebook/{book_id}",
			"/libraries/updatebook/{book_id}" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateBookToLibrary(@PathVariable("id") Optional<Long> library_id,
			@PathVariable("book_id") Long book_id, @RequestBody Book book) {

		return ResponseEntity.ok(bookServiceImpl.updateBookToLibrary(library_id, book_id, book));
	}

}