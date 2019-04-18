package com.util;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TestBed {

	public static void main(String[] args) {

		URI filePath = new File("C:\\Dev_Tools\\apache-tomcat-8.5.33\\logs" + "\\" + "catalina.2018-11-30.log").toURI();
		//System.out.println(filePath.toString());
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String test = contentBuilder.toString();
		System.out.println(test);
	}

}
