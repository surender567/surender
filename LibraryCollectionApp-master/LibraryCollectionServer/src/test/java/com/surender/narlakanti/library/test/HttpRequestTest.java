package com.surender.narlakanti.library.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

import com.surender.narlakanti.developer.library.LibraryApplication;
import com.surender.narlakanti.developer.library.model.Book;
import com.surender.narlakanti.developer.library.service.BookServiceImpl;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = { TestRestTemplate.class, LibraryApplication.class, BookServiceImpl.class })

public class HttpRequestTest {

	private TestRestTemplate restTemplate = new TestRestTemplate();
	Random random = new Random();
	
	@BeforeClass
	public static void startApplication() {
		LibraryApplication.main(new String[] {""});
	}

	@Test
	public void createBooks() throws Exception {

		for (int j = 0; j < 100; j++) {
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {

					try {
						Book book = new Book();
						book.setAuthor("Ayan");
						book.setGenre("Horror");
						book.setLanguage("English");
						book.setName("Surender Love Story");
						restTemplate.postForObject("http://localhost:" + "9999/libraries/1/book", book, Book.class);
						@SuppressWarnings("unchecked")
						List<String> list = restTemplate.getForObject("http://localhost:" + "9999" + "/libraries/books",
								ArrayList.class);
						System.out.println("Size : " + list.size());
						assertTrue(list.size() > 0);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			});

			thread.start();
		}
		
		
		Thread.sleep(30000);
	}

	
}
