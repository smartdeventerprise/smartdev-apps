package com.moviemobile;

import org.json.JSONException;
import org.json.JSONObject;

import com.moviemobile.R;
import com.moviemobile.utils.Constant;
import com.moviemobile.utils.ImageLoader;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetails extends Activity {
	
	TextView title, genre, rDate,rated, runtime,cast,synopsis; 
	ImageView poster;
	public ImageLoader imageLoader; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_details);
		
		imageLoader=new ImageLoader(MovieDetails.this);
		int position = Constant.movieJsonArr.length();
		
		for(int index = 0; index < position; index ++)
		{
			try {
				JSONObject jObj = Constant.movieJsonArr.getJSONObject(index);
				
				if(jObj.getInt("id") == Constant.selectedMovieId)
				{
					title = (TextView) findViewById(R.id.txtDetTitle);
					genre = (TextView) findViewById(R.id.txtDetGenre);
					poster = (ImageView) findViewById(R.id.imageDetPoster);
					rDate = (TextView) findViewById(R.id.txtRDate);
					rated = (TextView) findViewById(R.id.txtDetRated);
					runtime = (TextView) findViewById(R.id.txtRuntime);
					cast = (TextView) findViewById(R.id.txtCast);
					synopsis = (TextView) findViewById(R.id.txtSynopsis);
				
					title.setText(jObj.getString("movie"));
					genre.setText(jObj.getString("genre"));
					imageLoader.DisplayImage((jObj.getString("image")), poster);
					rDate.setText(jObj.getString("release_date"));
					rated.setText(jObj.getString("rating"));
					runtime.setText(jObj.getString("runtime"));
					cast.setText(jObj.getString("actors"));
					synopsis.setText(jObj.getString("synopsis"));
				}
				
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
		}
		
		
		
	}
}
