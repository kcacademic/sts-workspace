package com.sapient.learning.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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

		nashorn.eval(new FileReader("./src/main/webapp/index.js"));

		List<String> list = new ArrayList<>();
		list.add("Kumar");
		list.add("Chandrakant");

		Object html = nashorn.invokeFunction("renderServer", list);

		System.out.println(String.valueOf(html));

		return String.valueOf(html);
	}

}