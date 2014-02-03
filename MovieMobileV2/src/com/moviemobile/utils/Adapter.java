package com.moviemobile.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.moviemobile.MovieDetails;
import com.moviemobile.NowShowing;
import com.moviemobile.R;

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
    public ImageLoader imageLoader; 
    
    public Adapter(Activity a) {
        activity = a;
        //data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
        int movieCount = 0;
        try
        {
        	Constant.movieFilterJsonArr = new JSONArray();
        	movieCount = Constant.movieJsonArr.length();
        	
        
        }
        catch(Exception e)
        {
//        	NowShowing.txtCinema.setVisibility(View.GONE);
//        	NowShowing.txtError.setText("No Internet Connection");
//        	NowShowing.txtError.setVisibility(View.VISIBLE);
        	
        	
        }
        
        
    
//	        for(int index = 0; index < movieCount; index ++) //filters the movie by cinema
//			{
//	        	JSONObject jObj;
//				try {
//					jObj = Constant.movieJsonArr.getJSONObject(index);
//					
//					 switch(Constant.selectedCinema)
//			            {
//			            	case 0:
//			            		if((jObj.getString("Carib5")).equals("1"))
//			            			Constant.movieFilterJsonArr.put(jObj);
//			    			
//			            		break;
//			            	case 1:
//			            		if((jObj.getString("PalaceCine")).equals("1"))
//			            			Constant.movieFilterJsonArr.put(jObj);
//			    			
//			            		break;
//			            		
//			            	case 2:
//			            		if((jObj.getString("PalaceMulti")).equals("1"))
//			            			Constant.movieFilterJsonArr.put(jObj);
//			    			
//			            		break;
//			            	case 3:
//			            		if((jObj.getString("Odeon")).equals("1"))
//			            			Constant.movieFilterJsonArr.put(jObj);
//			    			
//			            		break;
//			            		
//			            	case 4:
//			            		if((jObj.getString("Marquee")).equals("1"))
//			            			Constant.movieFilterJsonArr.put(jObj);
//			    			
//			            		break;
//			            
//			            }
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					NowShowing.txtCinema.setVisibility(View.GONE);
//					NowShowing.txtError.setText("No Internet Connection");
//					NowShowing.txtError.setVisibility(View.VISIBLE);
//					
//				}
//	           
//        }
        
    
    }

    public int getCount() {
        //return data.length;
//    	if(Constant.movieFilterJsonArr.length() == 0)
//    	{
//    		NowShowing.txtCinema.setVisibility(View.GONE);
//			NowShowing.txtError.setVisibility(View.VISIBLE);
//			NowShowing.txtError.setText("No Movies Found");
//    	}
        //return Constant.movieFilterJsonArr.length();
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
//        ImageView image=(ImageView)vi.findViewById(R.id.imagePoster);
//        TextView time = (TextView)vi.findViewById(R.id.txtTime);
//        TextView genre = (TextView)vi.findViewById(R.id.txtGenre);
//        TextView rating = (TextView)vi.findViewById(R.id.txtRating);
//        ImageView image3D=(ImageView)vi.findViewById(R.id.image3d);
        
        try{
        	//JSONObject jObj = Constant.movieFilterJsonArr.getJSONObject(position);
            
        	JSONObject jObj = Constant.movieJsonArr.getJSONObject(position);
        
				        //vi.setId(jObj.getInt("id"));
				        
				        
				        
				        //imageLoader.DisplayImage((jObj.getString("image")), image);
				        
				        title.setText(jObj.getString("title"));
				        description.setText(jObj.getString("description"));
//				        switch(Constant.selectedCinema) //filters the movie times based on cinema
//				        {
//				        case 0:
//				        	time.setText(jObj.getString("carib_time"));
//				        	break;
//				        	
//				        case 1:
//				        	time.setText(jObj.getString("palCine_time"));
//				        	break;
//				        	
//				        case 2:
//				        	time.setText(jObj.getString("palMulti_time"));
//				        	break;
//				        	
//				        case 3:
//				        	time.setText(jObj.getString("odeon_time"));
//				        	break;
//				        	
//				        case 4:
//				        	time.setText(jObj.getString("marquee_time"));
//				        	break;
//				        	
//				        }
				        
//				        genre.setText(jObj.getString("genre"));
//				        rating.setText(jObj.getString("rating"));
//				        
//				        if((jObj.getString("3d").equals("1")))
//				        {
//				        	image3D.setVisibility(View.VISIBLE);
//				        }
		

        }
        
        
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
        
       
        
        vi.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			
//    			Constant.selectedMovieId=v.getId();
//    			Intent in = new Intent(activity,MovieDetails.class);
//    			activity.startActivity(in);
    			
    			
    		}
        
        });
        
        return vi;
    }
    
    
}