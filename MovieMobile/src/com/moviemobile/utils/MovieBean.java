package com.moviemobile.utils;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class MovieBean {
	
	public int movieId = 0;
	public String image = "";
	public String movieTitle = "";
	public String time = "";
	public String genre = "";
	public String rating = "";
	public String threeD = "";
	public Bitmap bitmap;
	
	public ArrayList<MovieBean> movieArray = new ArrayList<MovieBean>();

}
