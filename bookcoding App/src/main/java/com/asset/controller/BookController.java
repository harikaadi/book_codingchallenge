package com.asset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asset.dto.MessageDto;
import com.asset.exceptions.InvalidIsbnException;
import com.asset.model.BookCoding;
import com.asset.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookS;
	
	@PostMapping("/add")
	public ResponseEntity<BookCoding> addBookCoding(@RequestBody BookCoding c) {
		System.out.println("inside book"+c);
		BookCoding savedb = bookS.addBookCoding(c);
		return ResponseEntity.ok(savedb);
	}
	
	//public BookCoding getBooks()
	@GetMapping("/getall")
    public List<BookCoding> getAllBooks() {
        return bookS.getAllBooks();
    }

	@GetMapping("/by/{isbn}")
    public ResponseEntity<?> getBookByIsbn(@PathVariable String isbn, MessageDto dto) {
        try {
            // Retrieve the book by ISBN from the service
            BookCoding book = bookS.getByIsbn(isbn);

            // Return the found book
            return ResponseEntity.ok(book);
        } catch (InvalidIsbnException e) {
            // Handle invalid ISBN case
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }
	
	
	@PutMapping("/update/{isbn}")
	    public ResponseEntity<?> updateBook(@PathVariable String isbn, @RequestBody BookCoding newBook, MessageDto dto) {
	        try {
	            // Call service to update the book by ISBN
	            BookCoding updatedBook = bookS.updateBook(isbn, newBook);

	            // Return the updated book
	            return ResponseEntity.ok(updatedBook);
	        } catch (InvalidIsbnException e) {
	            // Handle the case where the ISBN is not found
	            dto.setMsg(e.getMessage());
	            return ResponseEntity.badRequest().body(dto);
	        }
	    }
	
	@DeleteMapping("/delete/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn, MessageDto dto) {
        try {
            // Call service to delete the book by its ISBN
            int numberofrows = bookS.deleteBook(isbn);

            // Set a success message in the response DTO
            dto.setMsg("Book Deleted Successfully.");
            return ResponseEntity.ok(dto);
        } catch (InvalidIsbnException e) {
            // Set the error message if the ISBN is invalid or book not found
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }



}


	
	


