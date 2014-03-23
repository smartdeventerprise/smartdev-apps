package com.moviemobile.utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLparser {
	 public static Document getDoc (String url) 
	 {
		 Document doc = null;
		 
		 try {
			doc = Jsoup.connect(url).get();
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: "+e);
			e.printStackTrace();
		}
		return doc;
	 
	 }	 
}
