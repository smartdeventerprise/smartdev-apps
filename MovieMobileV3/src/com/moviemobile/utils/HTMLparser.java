package com.moviemobile.utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLparser {
	 public static Document getDoc (String url) 
	 {
		 Document doc = null;
		 
		 try {
			doc = Jsoup.connect("http://google.com").get();
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	 
	 }	 
}
