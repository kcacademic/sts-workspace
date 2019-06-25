package com.learning.annotations;

public class Main {

	public static void main(String[] args) throws JsonSerializeException {
		
		JsonSerializer serializer = new JsonSerializer();
		
		String json = serializer.serialize(new Car("Maruti", "Swift", "2008"));
		
		System.out.println(json);

	}

}
