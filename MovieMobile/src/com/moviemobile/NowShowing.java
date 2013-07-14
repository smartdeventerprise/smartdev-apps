package com.moviemobile;

import java.util.ArrayList;

import com.moviemobile.R;
import com.moviemobile.utils.Adapter;
import com.moviemobile.utils.MovieBean;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class NowShowing extends Fragment {
	

    ListView list;
    Adapter adapter;
    Context context;
	
	
	ArrayList<MovieBean> arr = null;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_now_showing,
				container, false);
		
		
		 list=(ListView) rootView.findViewById(R.id.listNowShowing);
	        adapter=new Adapter(getActivity());
	        list.setAdapter(adapter);
		
		context = getActivity();
		
		
		
		return rootView;
	}
	
	@Override
    public void onDestroy()
    {
        list.setAdapter(null);
        super.onDestroy();
    }
    
//    public OnClickListener listener=new OnClickListener(){
//        @Override
//        public void onClick(View arg0) {
//            adapter.imageLoader.clearCache();
//            adapter.notifyDataSetChanged();
//        }
//    };
    
    
}