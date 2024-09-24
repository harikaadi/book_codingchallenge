package com.asset.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.asset.model.BookCoding;

@Repository
public interface BookRepository  extends JpaRepository<BookCoding, Integer>{
	@Query("select b from  BookCoding b  where b.isbn=?1")
	Optional<BookCoding> FindByIsbn(String isbn);
	
	@Modifying
	@Query("delete  from  BookCoding b  where b.isbn=?1")
	int DeleteByIsbn(String isbn);

}
