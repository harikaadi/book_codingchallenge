package com.asset.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.asset.exceptions.InvalidIsbnException;
import com.asset.model.BookCoding;
import com.asset.repo.BookRepository;

import jakarta.transaction.Transactional;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;

	public BookCoding addBook(BookCoding bookc) {
		return bookRepository.save(bookc);
		
	}

	public BookCoding addBookCoding(BookCoding c) {
		
	 return bookRepository.save(c);
	}

	public List<BookCoding> getAllBooks() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

	public BookCoding getByIsbn(String isbn) throws InvalidIsbnException {
		// TODO Auto-generated method stub
		 
		Optional<BookCoding> optionalBook =bookRepository.FindByIsbn(isbn);
		
        if (optionalBook.isEmpty()) {
            throw new InvalidIsbnException("No books found for this isbn number");
        }
        return optionalBook.get();
	}
	
	public BookCoding updateBook(String isbn, BookCoding newBook) throws InvalidIsbnException {
	    Optional<BookCoding> optional = bookRepository.FindByIsbn(isbn);
	    if (optional.isEmpty()) {
	        throw new InvalidIsbnException("Invalid ISBN Given");
	    }

	    BookCoding bookDB = optional.get(); // This has old values of the book
	    // Transfer newBook records to bookDB
	    bookDB.setTitle(newBook.getTitle());
	    bookDB.setAuthor(newBook.getAuthor());
	    bookDB.setPublicationYear(newBook.getPublicationYear());

	    // Save the bookDB again with updated values
	    return bookRepository.save(bookDB);
	}
	
	@Transactional
	public int deleteBook(String isbn) throws  InvalidIsbnException{
	    Optional<BookCoding> optional = bookRepository.FindByIsbn(isbn);
	    if (optional.isEmpty()) {
	       
	        throw new InvalidIsbnException("Invalid Book  for the given ISBN Number");
	    }
	    //logger.info("Book record deletion based on given ID: " + bookId);
	    //logger.warn("Hard delete of book record");
	    return bookRepository.DeleteByIsbn(isbn);
	}



	
	
	 

}
