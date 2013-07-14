package com.moviemobile;

import org.json.JSONException;
import org.json.JSONObject;

import com.moviemobile.R;
import com.moviemobile.utils.Constant;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MovieDetails extends Activity {
	
	TextView title; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_details);
		
		int position = Constant.movieJsonArr.length();
		
		for(int index = 0; index < position; index ++)
		{
			try {
				JSONObject jObj = Constant.movieJsonArr.getJSONObject(index);
				
				if(jObj.getInt("id") == Constant.selectedMovieId)
				{
				title = (TextView) findViewById(R.id.txtDetTitle);
				
				title.setText(jObj.getString("movie"));
				}
				
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
		}
		
		
		
	}
}
