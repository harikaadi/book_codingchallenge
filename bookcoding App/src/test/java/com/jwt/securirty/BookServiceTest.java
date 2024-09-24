package com.jwt.securirty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.asset.exceptions.InvalidIsbnException;
import com.asset.model.BookCoding;
import com.asset.repo.BookRepository;
import com.asset.service.BookService;

@SpringBootTest(classes = BookService.class)
@ContextConfiguration
public class BookServiceTest {
	
	@Autowired
	private BookService bookservice;
	
	@Autowired
	private BookRepository bookrepository;
	
	
	/*public void addBookTest() {
		
		int 
		
	}*/
	@Test
	public void getAllBooksTest() {
		int eval=1;
		int aval=bookservice.getAllBooks().size();
		assertEquals(eval, aval);
	}
	
	@Test
	public void getByIsbnTest() throws InvalidIsbnException {
		
		BookCoding book = bookservice.getByIsbn("JA2");
		assertNotNull(book);
		
		
		BookCoding samebook=bookservice.getByIsbn("JA2");
		
		assertEquals(book, samebook);
	}
	
	

}
