package com.learning.generics;

import java.util.Collection;

public class Wildcards {
	
	public static <T> T writeAll(Collection<? extends T> coll, Sink<? super T> snk) {
	    T last = null;
	    for (T t : coll) {
	        last = t;
	        snk.flush(last);
	    }
	    return last;
	}
	
	public static void main(String[] args) {
		
		Sink<Object> s = null;
		Collection<String> cs = null;
		String str = writeAll(cs, s);
		System.out.println(str);
	}


}
