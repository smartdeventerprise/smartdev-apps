//package com.moviemobile.utils;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.StatusLine;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpRequestBase;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import com.moviemobile.MainActivity;
//import com.moviemobile.R;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.widget.ImageView;
//
//public class Loading extends Activity
//{
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.loading);
//		
//		new getMovies().execute();
//	}
//	
//	
//	
//public class getMovies extends AsyncTask<Void, Void,Integer>{
//	
//	public getMovies()
//	{
//	}
//	
//	
//	//private ArrayList<MovieBean> list = new ArrayList<MovieBean>();
//	
//
//	
//	@Override
//	protected void onPostExecute(Integer result)
//	{
//		Intent in = new Intent(Loading.this, MainActivity.class);
//		startActivity(in);
//		finish();
//	}
//	
//	@Override
//	protected Integer doInBackground(Void... params)
//	{
//		
//	try
//	{
//		String movieJSONString = getResponseString(Constant.MOVIE_LIST_URL);
//		
////		JSONArray movieJArr = new JSONArray(movieJSONString);
////		int movieCount = movieJArr.length();
//		
//		Constant.movieJsonArr = new JSONArray(movieJSONString);
//		
//		//String[] imageUrls;
////		for(int index = 0; index < movieCount; index ++) 
////		{
////			JSONObject movieJObj = movieJArr.getJSONObject(index);
////			
////			//Bitmap bm = getImage(movieJObj.getString("image"));
////			
//////			MovieBean mbean = new MovieBean();
//////			
//////			mbean.image=movieJObj.getString("image");
//////			mbean.movieId = movieJObj.getInt("id");
//////			mbean.movieTitle=movieJObj.getString("movie");
//////			mbean.time=movieJObj.getString("time");
//////			mbean.genre=movieJObj.getString("genre");
//////			mbean.rating=movieJObj.getString("rating");
//////			mbean.threeD=movieJObj.getString("3d");
////			//mbean.bitmap = bm;
////			
////			//list.add(mbean);
////			
////			}
//		
//		//Constant.movieObject.movieArray=list;
//		
//	}
//	catch(Exception e)
//	{
//		System.out.println(e.getMessage());
//	}
//	
//		
//		return null;
//	}
//	
//	
//	
//	public String getResponseString (String url) 
//	{
//		String responseString = "";
//		try {
//			// Setup the object that makes the http request
//			
//			HttpClient httpclient = new DefaultHttpClient();
//			HttpRequestBase request = new HttpGet(url);
////			request.addHeader(Utils.getBasicAuthenticationHeader(context));
////			request.addHeader("User-Agent", Constant.HTTP_USER_AGENT);
//			HttpResponse response = httpclient.execute(request);
//			StatusLine statusLine = response.getStatusLine();
//			
//			// If there was no error in the http request
//			if(statusLine.getStatusCode() == HttpStatus.SC_OK){
//				ByteArrayOutputStream out = new ByteArrayOutputStream();
//				response.getEntity().writeTo(out);
//				out.close();
//				responseString = out.toString();
//			} else {
//				response.getEntity().getContent().close();
//				//Log.i("getResponseString", responseString);
//				throw new IOException(statusLine.getReasonPhrase());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return responseString;
//	}
//	
//	
//}
//
//
//
////
////	  protected Bitmap getImage(String url) {
////	      
////	      Bitmap mIcon11 = null;
////	      try {
////	        InputStream in = new java.net.URL(url).openStream();
////	        mIcon11 = BitmapFactory.decodeStream(in);
////	      } catch (Exception e) {
////	          System.out.println(e.getMessage());
////	          e.printStackTrace();
////	      }
////	      return mIcon11;
////	  }
//
//	
//	      
//	
//	
//
//}
//
//
//
//
