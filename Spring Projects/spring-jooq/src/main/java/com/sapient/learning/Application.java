package com.sapient.learning;

import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sapient.learning.data.public_.tables.Author;

public class Application {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.sapient.learning");

		DSLContext dsl = context.getBean(DSLContext.class);

		Author author = Author.AUTHOR;
		try {
			dsl.insertInto(author).set(author.ID, 1).set(author.FIRST_NAME, "Kumar")
					.set(author.LAST_NAME, "Chandrakant").execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Result<Record3<Integer, String, Integer>> result = dsl.select(author.ID, author.LAST_NAME, DSL.count())
				.from(author).groupBy(author.ID).fetch();

		System.out.println(result.size());
		System.out.println(result.getValue(0, author.LAST_NAME));

		context.close();

	}

}
