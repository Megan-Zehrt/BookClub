// Pair Programmed with Jacob 

package com.megan.bookclub.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.megan.bookclub.models.Book;

@Repository

// Creates a find all method for books
public interface BookRepository extends CrudRepository<Book, Long>{
	List<Book> findAll();

}
