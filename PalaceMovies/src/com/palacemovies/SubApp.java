package com.palacemovies;

import com.moviemobile.R;
import com.pushbots.push.PBGenerateCustomNotification;
import com.pushbots.push.Pushbots;

import android.app.Activity;
import android.app.Application;

public class SubApp extends Application {
	private String SENDER_ID = "119259985053";
	private String PUSHBOTS_APPLICATION_ID = "532b05da1d0ab1f57c8b45c1";
	@Override
    public void onCreate() {
        super.onCreate();
        Pushbots.init(this, SENDER_ID,PUSHBOTS_APPLICATION_ID);
        Pushbots.getInstance().setMsgReceiver(customPushReceiver.class);
        
        PBGenerateCustomNotification PBCustom = new PBGenerateCustomNotification();
        PBCustom.layout = R.layout.notification;
        PBCustom.titleId = R.id.title;
        PBCustom.messageId = R.id.message;
        PBCustom.iconId = R.id.image;
        PBCustom.icondId = R.drawable.logo4;
        Pushbots.getInstance().setNotificationBuilder(PBCustom);
        
//        Intent in = new Intent(this,MainActivity.class);
//		activity.startActivity(in);
     }
}
