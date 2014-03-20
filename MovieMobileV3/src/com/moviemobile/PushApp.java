//package com.moviemobile;
//
//import com.pushbots.push.Pushbots;
//
//import android.app.Activity;
//import android.app.Application;
//import android.content.Intent;
//
//public class PushApp extends Application {
//	private String SENDER_ID = "119259985053";
//	private String PUSHBOTS_APPLICATION_ID = "532b05da1d0ab1f57c8b45c1";
//	private Activity activity;
//
//	@Override
//    public void onCreate() {
//        super.onCreate();
//        Pushbots.init(this, SENDER_ID,PUSHBOTS_APPLICATION_ID);
//        
//        Intent in = new Intent(this,MainActivity.class);
//		activity.startActivity(in);
//     }
//}
