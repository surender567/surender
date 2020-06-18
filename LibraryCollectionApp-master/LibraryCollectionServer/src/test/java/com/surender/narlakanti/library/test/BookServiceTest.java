package com.surender.narlakanti.library.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.surender.narlakanti.developer.library.LibraryApplication;
import com.surender.narlakanti.developer.library.model.Book;
import com.surender.narlakanti.developer.library.model.Library;
import com.surender.narlakanti.developer.library.service.BookServiceImpl;

@Rollback(false)
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = { LibraryApplication.class, BookServiceImpl.class })
public class BookServiceTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BookServiceImpl bookServiceImpl;
	Library library;

	private boolean getDefaultLibrary = false;

	@Before
	public void setUp() {
		if (!getDefaultLibrary) {
			library = LibraryAppTestUtil.getDefaultLibrary();
			entityManager.persist(library);
			entityManager.flush();
			getDefaultLibrary = true;
		}

	}

	@Test
	public void whenGetAllBooks_thenReturnBookList() {

		library = entityManager.find(Library.class, 1L);

		for (int i = 0; i < 5; i++) {
			Book book = new Book();
			book.setAuthor("Ayan Rynd" + i);
			book.setGenre("Horror" + i);
			book.setName("Interesting" + i);
			library.addBookToLibrary(book);
		}

		entityManager.persist(library);

		entityManager.flush();

		List<Book> books = bookServiceImpl.getAllBooks(Optional.of(1L));
		assertThat(books.size() == 5);
		assertThat(books.get(0).getName().equals("Interesting0"));
		assertThat(books.get(1).getGenre().equals("Horror1"));

	}

	@Test
	public void whenCreateBook_thenReturnNewBook() {

		library = entityManager.find(Library.class, 1L);

		Book book1 = new Book();
		book1.setAuthor("William Shakes");
		book1.setGenre("ROmantic");
		book1.setName("All is Well Thats end Well");
		library.addBookToLibrary(book1);
		entityManager.persist(library);

		entityManager.flush();

		Book book = new Book();
		book.setAuthor("Ayan Rynd");
		book.setGenre("Horror");
		book.setName("Interesting");

		Book dbbook = bookServiceImpl.addBookToLibrary(Optional.of(1L), book);
		assertThat(dbbook.getName().equals("Interesting"));
		assertThat(dbbook.getGenre().equals("Horror"));
		assertThat(dbbook.getLibrary().getLibraryId() == 1L);

	}

	@Test
	public void whenUpdateBook_thenReturnUBook() {

		library = entityManager.find(Library.class, 1L);
		Book book1 = new Book();
		Random rand = new Random();
		long id = rand.nextLong();
		book1.setAuthor("William Shakes");
		book1.setGenre("ROmantic");
		book1.setName("All is Well Thats end Well");
		library.addBookToLibrary(book1);
		entityManager.persist(library);

		entityManager.flush();

		library = entityManager.find(Library.class, 1L);
		Book newBook = library.getBooks().get(0);
		newBook.setAuthor("Ayan Rynd");
		bookServiceImpl.updateBookToLibrary(Optional.of(library.getLibraryId()), library.getBooks().get(0).getId(),
				newBook);
		library = entityManager.find(Library.class, 1L);

		assertThat(library.getBooks().get(0).getAuthor().equals("Ayan Rynd"));

	}

}
