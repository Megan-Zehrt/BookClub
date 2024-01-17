//pair programmed with Jacob
package com.megan.bookclub.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;



@Entity
@Table(name = "book")
public class Book {

	// VARIABLES + VALIDATION
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// TO CONNECT USER TO BOOKS (ONE-TO-MANY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;

	// TITLE VAR
	@NotBlank(message = "Title name is required")
	private String title;
	
	// AUTHOR VAR
	@NotBlank(message = "Author is required")
	private String author;


	// THOUGHTS VAR
	@NotBlank(message = "Your thoughts is required")
	private String thoughts;
	
	// CreatedAt, UpdatedAt
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
	
	// Empty constructor
	public Book() {}
	
	// create a new book
	public Book(@NotEmpty(message = "Title Name is required") String title, @NotEmpty(message = "Author is required") String author, @NotEmpty(message = "Your thoughts are required") String thoughts) {
		super();
		this.title = title;
		this.author = author;
		this.thoughts = thoughts;
	}
	
	// GETTER AND SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThoughts() {
		return thoughts;
	}

	public void setThoughts(String thoughts) {
		this.thoughts = thoughts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	
	
	
}
