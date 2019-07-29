package com.sapient.learning;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class Main {

	public static void main(String[] args) throws FileNotFoundException, ScriptException {
		ScriptEngine nashorn = new ScriptEngineManager().getEngineByName("nashorn");
		
		nashorn.eval(new FileReader("./src/main/webapp/lib/react.js"));
		nashorn.eval(new FileReader("./src/main/webapp/lib/react-dom-server.js"));

		System.out.println(nashorn.eval(new FileReader("./src/main/webapp/app.js")));
		
		System.out.println(nashorn
				.eval("ReactDOMServer.renderToString(" + "React.createElement('div', null, 'Hello World!')" + ");"));
	}

}