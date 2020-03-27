package com.sapient.learning;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.sapient.learning.domain.Book;

public class Main {

	public static void main(String[] args) throws JAXBException, FileNotFoundException {

		marshal();
		System.out.println(unmarshall());

	}

	private static void marshal() throws JAXBException {
		Book book = new Book();
		book.setId(1L);
		book.setName("Book1");
		book.setAuthor("Author1");
		book.setDate(new Date());

		JAXBContext context = JAXBContext.newInstance(Book.class);
		Marshaller mar = context.createMarshaller();
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		mar.marshal(book, new File("./target/book.xml"));
	}

	private static Book unmarshall() throws JAXBException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(Book.class);
		return (Book) context.createUnmarshaller().unmarshal(new FileReader("./target/book.xml"));
	}

}