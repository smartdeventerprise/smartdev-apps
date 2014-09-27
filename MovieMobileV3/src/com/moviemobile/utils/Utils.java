package com.moviemobile.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import com.moviemobile.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;

public class Utils {
	
	private static ProgressDialog progressDialog;
    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
              int count=is.read(bytes, 0, buffer_size);
              if(count==-1)
                  break;
              os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
    
    public static void showProgressDialog(Context context) {
		progressDialog = new ProgressDialog(context);
		//progressDialog.setMessage(message);
		progressDialog.show();
		progressDialog.setContentView(R.layout.progress);
		progressDialog.setCancelable(false);
		
		
	
	}
    
    public static void closeProgressDialog(Context context)
    {
    	progressDialog.cancel();
    }
    
    public static boolean isNetworkAvailable(Context context) 
    {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
    
    public String getJsonResponseString (String url, Context context) 
	{
		String responseString = "";
		try {
			// Setup the object that makes the http request
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpRequestBase request = new HttpGet(url);
//			request.addHeader(Utils.getBasicAuthenticationHeader(context));
//			request.addHeader("User-Agent", Constant.HTTP_USER_AGENT);
			HttpResponse response = httpclient.execute(request);
			StatusLine statusLine = response.getStatusLine();
			
			// If there was no error in the http request
			if(statusLine.getStatusCode() == HttpStatus.SC_OK){
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();
			} else {
				response.getEntity().getContent().close();
				//Log.i("getResponseString", responseString);
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Utils.closeProgressDialog(context);
			
			return "error";
//			txtError.setVisibility(View.VISIBLE);
//			txtError.setText("No Internet Connection");
			
		}
		return responseString;
	}
    
}