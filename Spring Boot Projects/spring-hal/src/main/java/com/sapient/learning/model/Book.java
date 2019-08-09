package com.sapient.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Book {

	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Setter
	@Getter
	@Column(columnDefinition = "VARCHAR", length = 100)
	private String title;

	@NotNull
	@Setter
	@Getter
	@Column(columnDefinition = "VARCHAR", length = 100)
	private String author;

	@Setter
	@Getter
	@Column(columnDefinition = "VARCHAR", length = 1000)
	private String blurb;

	@Setter
	@Getter
	private int pages;

}