// PAIR PROGRAMMED WITH JACOB

package com.megan.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.megan.bookclub.models.Book;
import com.megan.bookclub.repositories.BookRepository;

@Service
public class BookService {
	
	// CONNECTS THE SERVICE TO THE REPOSITORY
	private final BookRepository bookRepo;

	public BookService(BookRepository bookRepo) {
		this.bookRepo = bookRepo;
	}
	
	// get all
	public List<Book> getAll(){
		return bookRepo.findAll();
	}
	
	// create one
	public Book create(Book book) {
		return bookRepo.save(book);
	}
	
	// get one by id
	public Book getOne(Long id) {
		Optional<Book> optionalBook = bookRepo.findById(id);
		return optionalBook.isPresent() ? optionalBook.get() : null;
	}

	
	// edit
	public Book edit(Book book) {
		return bookRepo.save(book);
	}
	
	// delete by id
	public void Delete(Long id) {
		bookRepo.deleteById(id);
	}
}
