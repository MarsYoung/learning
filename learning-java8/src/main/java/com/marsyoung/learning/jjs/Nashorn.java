package com.marsyoung.learning.jjs;


import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * 
 * 
 * @author zhiyuma
 *  1、 有一个Node应用程序，并希望使用某个Java库作为Node API的补充
	2、希望切换到JavaScript和Node API，但需要将遗留的Java代码部分或全部嵌入
 *
 *
 */
public class Nashorn extends TestCase{
	
	@Test
	public void readFile() throws FileNotFoundException, ScriptException{
		ScriptEngineManager engineManager= new ScriptEngineManager();
		ScriptEngine engine=engineManager.getEngineByName("nashorn");
		engine.eval(new FileReader("src/main/java/com/marsyoung/learning/jjs/nashorn.js"));
	}
	
	@Test
	public void readString() throws FileNotFoundException, ScriptException{
		ScriptEngineManager engineManager= new ScriptEngineManager();
		ScriptEngine engine=engineManager.getEngineByName("nashorn");
		engine.eval("function sum(a,b){return a+b;} sum(1,2)");
	}
	
}
