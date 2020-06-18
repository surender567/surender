package com.surender.narlakanti.developer.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.surender.narlakanti.developer.library.dao.BookRepository;
import com.surender.narlakanti.developer.library.dao.LibraryRepository;
import com.surender.narlakanti.developer.library.model.Book;
import com.surender.narlakanti.developer.library.model.InfoMessage;
import com.surender.narlakanti.developer.library.model.Library;
import com.surender.narlakanti.developer.library.util.Operations;


@Service
public class BookServiceImpl {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private LibraryRepository libraryRepository;

	public BookServiceImpl() {
	}

	public List<Book> getAllBooks(Optional<Long> library_id) {
		return libraryRepository.findById(library_id.isPresent() ? library_id.get() : 1L).get().getBooks();

	}

	public Optional<Book> getBookFromLibrary(@PathVariable("book_id") Long book_id) {

		return bookRepository.findById(book_id);

	}

	public Book addBookToLibrary(Optional<Long> library_id, Book book) {
		Optional<Library> optLibrary = libraryRepository.findById(library_id.isPresent() ? library_id.get() : 1L);
		if (optLibrary.isPresent()) {
			Library library = optLibrary.get();

			library.addBookToLibrary(book);

			libraryRepository.save(library);

			return library.getBooks().stream().sorted((b1, b2) -> b2.getId().intValue() - b1.getId().intValue())
					.findFirst().get();
		}
		return new Book();

	}

	public InfoMessage updateBookToLibrary(Optional<Long> library_id, Long book_id, Book book) {

		Library library = libraryRepository.findById(library_id.isPresent() ? library_id.get() : 1L).get();

		List<Book> bookList = library.getBooks();

		for (int i = 0; i < bookList.size(); i++) {

			if (bookList.get(i).getId() == book_id) {
				book.setId(book_id);
				book.setLibrary(library);
				bookList.set(i, book);
				library.setBooks(bookList);
				libraryRepository.save(library);

				return new InfoMessage(Operations.UPDATE, "Book is Updated");

			}
		}

		return new InfoMessage(Operations.UPDATE, "Book is not Updated");
	}
}
