package com.palacemovies;

import org.json.JSONException;
import org.json.JSONObject;

import com.moviemobile.R;
import com.palacemovies.utils.Constant;
import com.palacemovies.utils.ImageLoader;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetails extends Activity {
	
	TextView title, genre, rDate,rated, runtime,cast,synopsis,rating; 
	ImageView poster;
	public ImageLoader imageLoader; 
	public String trailerUrl = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActionBar actionBar = getActionBar();
		
		//actionBar.setDisplayShowTitleEnabled(false);
		//actionBar.setLogo(R.drawable.logo2);
		actionBar.setTitle("Movie Details");
		
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
					
					runtime = (TextView) findViewById(R.id.txtRuntime);
					cast = (TextView) findViewById(R.id.txtCast);
					synopsis = (TextView) findViewById(R.id.txtSynopsis);
					rating = (TextView) findViewById(R.id.txtRating);
				
					title.setText(jObj.getString("title"));
					genre.setText(jObj.getString("genre"));
					imageLoader.DisplayImage((jObj.getString("poster")), poster);
					rDate.setText(jObj.getString("release_date"));
					rating.setText(jObj.getString("rating"));
					runtime.setText(jObj.getString("runtime"));
					cast.setText(jObj.getString("actors"));
					synopsis.setText(jObj.getString("synopsis"));
					
					//trailerUrl = (jObj.getString("trailer"));
				}
				
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
	    MenuItem item= menu.findItem(R.id.action_trailer);
	    if(!(trailerUrl.equals("")))
	    	item.setVisible(true);
	    
	    super.onPrepareOptionsMenu(menu);
	    return true;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.other, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.action_trailer:
	    	startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl)));
	        return true;
	  
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
}
