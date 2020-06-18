package com.surender.narlakanti.library.test;

import static org.assertj.core.api.Assertions.assertThat;

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
import com.surender.narlakanti.developer.library.model.Library;
import com.surender.narlakanti.developer.library.service.LibraryServiceImpl;

@Rollback(false)
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = { LibraryApplication.class, LibraryServiceImpl.class })
public class LibraryServiceTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private LibraryServiceImpl libraryServiceImpl;

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
	public void whenGetAllLibraries_thenReturLibraryList() {

		Library newLibrary = libraryServiceImpl.getAllLibraries().get(0);

		assertThat(newLibrary.getName().equals("John Doe Library"));
		assertThat(newLibrary.getPostalCode().equals("00023"));
	}

}
