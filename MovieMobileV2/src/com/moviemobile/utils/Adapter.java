package com.moviemobile.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.moviemobile.MovieDetails;
import com.moviemobile.NowShowing;
import com.moviemobile.R;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
    
    
    public Adapter(Activity a) {
        activity = a;
        //data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        

        
        

        
    
    }

    public int getCount() {

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
            vi = inflater.inflate(R.layout.movie_list_item, null);

        
        TextView title=(TextView)vi.findViewById(R.id.txtMovieTitle);
        TextView description=(TextView)vi.findViewById(R.id.txtDescription);

        
        try{
        	
            
        	JSONObject jObj = Constant.movieJsonArr.getJSONObject(position);

				        
				        title.setText(jObj.getString("title"));
				        String a = jObj.getString("description");
				        a=a.replace("Showing at: ", "");
				        a=a.replace("<BR><BR>", ",");
				        a=a.replace(", " , "\n");
				        a=a.replace("," , "\n");
				        //String substr=a.substring(a.indexOf(","));
				        description.setText(a);

        }
        
        
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
        
       
        
        vi.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
		
    			
    		}
        
        });
        
        return vi;
    }
    
    
}