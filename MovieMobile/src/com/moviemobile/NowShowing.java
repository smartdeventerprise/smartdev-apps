package com.moviemobile;




import java.io.InputStream;
import java.util.ArrayList;

import com.moviefone.R;
import com.moviemobile.utils.Constant;
import com.moviemobile.utils.MovieBean;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class NowShowing extends Fragment {
	
	private ListView movie_list;
	Context context;
	
	
	ArrayList<MovieBean> arr = null;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_now_showing,
				container, false);
		
		movie_list = (ListView) rootView.findViewById(R.id.listNowShowing);
		
		movie_list.setDivider(new ColorDrawable(0x99FFFFFF));
		movie_list.setDividerHeight(1);
		movie_list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		context = getActivity();
		
		
		
		arr = Constant.movieObject.movieArray;
		
		
		if (arr != null)
		{
			movie_list.setAdapter(new movieAdapter(context,arr));
		}
		
		return rootView;
	}
	
	@Override
	public void onResume() {
		super.onResume();

		fillAdaptor();
	}
	
public void fillAdaptor() {
		
		movieAdapter adapter =null;
		
		if (arr != null)
		{
			adapter = new movieAdapter(context,arr);
		}
		
		movie_list.setAdapter(adapter);
		
}
	
	
	public class movieAdapter extends BaseAdapter
	{
		Context con;
		ArrayList<MovieBean> array;
		private int mItemIndex = -1; 
		
		public movieAdapter(Context context, ArrayList<MovieBean> list) {
	        this.con = context;
	        array=list;
	    }
		
		
		 public View getView(final int position, View convertView, ViewGroup parent) 
		 {
			 View view = convertView;
			 
			 
			 
			 view = LayoutInflater.from(context).inflate(R.layout.now_showing_list_placeholder, null, true);
			 MovieBean mBean = new MovieBean();
			 
			 ImageView poster = (ImageView) view.findViewById(R.id.imagePoster);
			 TextView title = (TextView) view.findViewById(R.id.txtMovieTitle);
			 TextView time = (TextView) view.findViewById(R.id.txtTime);
			 TextView genre = (TextView) view.findViewById(R.id.txtGenre);
			 TextView rating = (TextView) view.findViewById(R.id.txtRating);
			 ImageView threed = (ImageView) view.findViewById(R.id.image3d);
			 
			 mBean = this.array.get(position);
			 
			 title.setText(mBean.movieTitle);
			 time.setText(mBean.time);
			 genre.setText(mBean.genre);
			 rating.setText(mBean.rating);
			 
			
			 
			 
			 //set image
			 
			 poster.setImageBitmap(mBean.bitmap);
			 
//			 if ((mBean.image).equals("a"))
//			 {
//				 poster.setImageResource(R.drawable.a);
//			 }
//			 if ((mBean.image).equals("b"))
//			 {
//				 poster.setImageResource(R.drawable.b);
//			 }
//			 if ((mBean.image).equals(""))
//			 {
//				 poster.setImageResource(R.drawable.default_movie);
//			 }
//			 
			 //set 3d image
			 if ((mBean.threeD.equals("yes")))
			 {
				 threed.setImageResource(R.drawable.threed);
			 }
			 
			 
			 
			 
			 view.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						
						Intent in = new Intent(context,MovieDetails.class);
						startActivity(in);
					}
			 
			 });
			 
			 return view;
		 }
	
		 

			@Override
			public Object getItem(int position) {
				return null;
			}

			@Override
			public long getItemId(int position) {
				return 0;
			}
			
			public void setSelectItem(int index) {
		        mItemIndex = index;
		    }

		    public int getSelectItem() {
		        return mItemIndex;
		    }


			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				if(array!=null)
		   		{			
					return this.array.size();
		   		
		   		}
		   		else
		   		{
		   			return 0;
		   		}
			}
	
	}
	
	
}
