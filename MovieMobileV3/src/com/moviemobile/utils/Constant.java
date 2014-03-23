package com.moviemobile.utils;

import org.json.JSONArray;


public class Constant {
	
	public static MovieBean movieObject = new MovieBean();
	public static JSONArray movieJsonArr = null;
	public static JSONArray movieFilterJsonArr = null;
	public static int selectedMovieId = -1;
	public static final String MOVIE_LIST_URL = "http://smartdeventerprise.com/moviemobile/get_movies.php";
	public static final String CARIB_MOVIES_URL = "http://www.palaceamusement.com/generaterss.php?channel=schedule";
	public static final String COMING_SOON_URL = "http://www.palaceamusement.com/palace.dti?page=comingsoon";
	public static int selectedCinema = -1;

}
