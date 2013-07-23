package com.moviemobile.utils;

import org.json.JSONObject;

import com.moviemobile.MovieDetails;
import com.moviemobile.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter extends BaseAdapter {
    
    private Activity activity;
    //private String[] data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    
    public Adapter(Activity a) {
        activity = a;
        //data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        //return data.length;
        return Constant.movieJsonArr.length();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.now_showing_list_placeholder, null);

        TextView title=(TextView)vi.findViewById(R.id.txtMovieTitle);
        ImageView image=(ImageView)vi.findViewById(R.id.imagePoster);
        TextView time = (TextView)vi.findViewById(R.id.txtTime);
        TextView genre = (TextView)vi.findViewById(R.id.txtGenre);
        TextView rating = (TextView)vi.findViewById(R.id.txtRating);
        
        try{
        JSONObject jObj = Constant.movieJsonArr.getJSONObject(position);
        
        vi.setId(jObj.getInt("id"));
        
        imageLoader.DisplayImage((jObj.getString("image")), image);
        
        title.setText(jObj.getString("movie"));
        time.setText(jObj.getString("time"));
        genre.setText(jObj.getString("genre"));
        rating.setText(jObj.getString("rating"));
        
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
        
       
        
        vi.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			
    			Constant.selectedMovieId=v.getId();
    			Intent in = new Intent(activity,MovieDetails.class);
    			activity.startActivity(in);
    			
    			
    		}
        
        });
        
        return vi;
    }
    
    
}