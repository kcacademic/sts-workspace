package com.sapient.learning.service;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.stereotype.Service;

import jdk.nashorn.api.scripting.NashornScriptEngine;

@Service
public class MyService {

	public String serve() throws FileNotFoundException, ScriptException, NoSuchMethodException {
		NashornScriptEngine nashorn = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");

		nashorn.eval(new FileReader("./src/main/webapp/js/react.js"));
		nashorn.eval(new FileReader("./src/main/webapp/js/react-dom-server.js"));
		
		nashorn.eval(new FileReader("./src/main/webapp/app.js"));
		Object html = nashorn.eval(
	    	      "ReactDOMServer.renderToString(" +
	    	        "React.createElement(App, {data: [0,1,1]})" +
	    	      ");"
	    	    );
		return String.valueOf(html);
	}

}