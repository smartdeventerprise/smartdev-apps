package com.moviemobile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.moviemobile.utils.Adapter;
import com.moviemobile.utils.Constant;
import com.moviemobile.utils.Utils;
import com.moviemobile.R;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class WeeklySchedule extends Fragment {

	Context context;
	public static TextView txtError;
	LinearLayout layoutWeeklySchedule;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_weekly_schedule, container, false);

		context = getActivity();
		
		layoutWeeklySchedule = (LinearLayout) rootView.findViewById(R.id.layoutWeeklySchedule);
		txtError = (TextView) rootView.findViewById(R.id.txtError);
		Utils.showProgressDialog(context);

		//list=(ListView) rootView.findViewById(R.id.listWeeklySchedule);

		if (Utils.isNetworkAvailable(context)) {
			new getMovies().execute();
		}

		else {
			Utils.closeProgressDialog(context);

			txtError.setVisibility(View.VISIBLE);
			txtError.setText("No Internet Connection");

		}

		return rootView;
	}

	@Override
	public void onDestroy() {
		//list.setAdapter(null);
		super.onDestroy();
	}

	public class getMovies extends AsyncTask<Void, Void, Integer> {

		private final String TAG = getMovies.class.getSimpleName();
		ArrayList<String> items = new ArrayList<String>();

		public getMovies() {

		}


		@Override
		protected void onPostExecute(Integer result) {
			if (Constant.weeklyScheduleJsonArr != null) {
				final ListView movieList = new ListView(context);
				final LinearLayout spinnerWrap = new LinearLayout(context);
				final Spinner dateSpinner = new Spinner(context);
				List<String> spinnerArray =  new ArrayList<String>();
				for (int i = 0; i < Constant.weeklyScheduleJsonArr.length(); i++) {
					JSONObject jObj;
					try {
						jObj = Constant.weeklyScheduleJsonArr.getJSONObject(i);
						//Constant.movieJsonArr = jObj.getJSONArray("movies");
						String date = jObj.getString("dateTitle");
						
						spinnerArray.add(date);
						

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,
						spinnerArray);

				adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
				dateSpinner.setAdapter(adapter);
				dateSpinner.setLayoutParams(new ViewGroup.LayoutParams(
				        ViewGroup.LayoutParams.MATCH_PARENT,
				        ViewGroup.LayoutParams.WRAP_CONTENT));
				spinnerWrap.setBackgroundColor(Color.WHITE);
				spinnerWrap.addView(dateSpinner);
				layoutWeeklySchedule.addView(spinnerWrap);
				layoutWeeklySchedule.addView(movieList);
				
				dateSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

					String strItem = dateSpinner.getItemAtPosition(arg2).toString();
					for (int i = 0; i < Constant.weeklyScheduleJsonArr.length(); i++) {
						try {
							JSONObject jObj = Constant.weeklyScheduleJsonArr.getJSONObject(i);
							if (strItem.equalsIgnoreCase(jObj.getString("dateTitle"))) {
								Constant.movieJsonArr = jObj.getJSONArray("movies");
								Adapter adapter = new Adapter(getActivity());
								movieList.setAdapter(adapter);

							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub

				}
			});


			} else {

			}
			Utils.closeProgressDialog(context);
		}

		@Override
		protected Integer doInBackground(Void... params) {

			try {

				getHtmlData(Constant.WEEKLY_SCHEDULE_URL);

			} catch (Exception e) {
				e.printStackTrace();
				Utils.closeProgressDialog(context);
			}

			return null;
		}

		public void getHtmlData(String url) {

			JSONArray jsonArrSchedule = new JSONArray();
			
			
			
			Elements dates = new Elements();
			Elements scheduleTables = new Elements();
			try {
				Document doc = Jsoup.connect(url).get();
				Element content = doc.getElementById("weeklyschedule");
				dates = content.getElementsByTag("h4");
				
				//getting schedule table
				scheduleTables = doc.getElementsByClass("schedule");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String movieJSONString = "";
			String movieUrl = "";
			String strYear = "";
			String notFound = "{\"Response\":\"False\",\"Error\":\"Movie not found!\"}";
			for (int i = 0; i < dates.size(); i++) {
				JSONArray jsonArrMovies = new JSONArray();
				JSONObject jsonDate = new JSONObject();
				Element e = dates.get(i);
				String dateTitle = e.text();
				Elements movies = scheduleTables.get(i).getElementsByAttributeValueStarting("href", "javascript:GetMovie");
				
				Elements tbody = scheduleTables.get(i).getElementsByTag("tbody");
				Elements trs = tbody.get(0).getElementsByTag("tr");
				for (int mov = 0; mov < movies.size(); mov++) {
					JSONObject movieJson = new JSONObject();
					JSONObject jsonMovie = new JSONObject();
					String title = movies.get(mov).text();
					String titleForSearch = title.replace(" (3D)", "");
					titleForSearch = titleForSearch.replace(" ", "+");
	
					Calendar now = Calendar.getInstance();
					int year = now.get(Calendar.YEAR);
					strYear = String.valueOf(year);
					
					Elements tds = trs.get(mov).getElementsByTag("td");
					Elements ratings = trs.get(mov).getElementsByClass("rating");
					String rating = ratings.get(0).text();
					int strIndx = rating.indexOf(",");
					if(strIndx>0){
						String ratingSubStr = rating.substring(strIndx);
						rating = rating.replace(ratingSubStr, "");
					}
					
					String desc = "Showing at: ";
					if(!tds.get(2).text().isEmpty())
						desc += "Carib 5 ("+tds.get(2).text();
					if(!tds.get(2).text().isEmpty() && (!tds.get(3).text().isEmpty() || !tds.get(4).text().isEmpty()))
						desc+="), ";
					else if(!tds.get(2).text().isEmpty() && tds.get(3).text().isEmpty())
						desc+=")";
					if(!tds.get(3).text().isEmpty())
						desc+="Palace Cineplex ("+tds.get(3).text();
					if(!tds.get(3).text().isEmpty() && !tds.get(4).text().isEmpty())
						desc+="), ";
					else if(!tds.get(3).text().isEmpty() && tds.get(2).text().isEmpty()&& tds.get(4).text().isEmpty())
						desc+=")";
					if(!tds.get(4).text().isEmpty())
						desc+="Palace Multiplex ("+tds.get(4).text()+")";
					desc+="<BR><BR>Rating: "+rating+"<BR><BR>";				
	
					for (int m = 0; m <= 2; m++) {
						strYear = String.valueOf(year);
						movieUrl = "http://www.omdbapi.com/?&t=" + titleForSearch + "&y=" + strYear + "&type=movie";
						movieJSONString = getResponseString(movieUrl);
						try {
							if (!movieJSONString.equals(notFound)) {
								movieJson = new JSONObject(movieJSONString);
								if (!(movieJson.getString("Plot")).equals("N/A")
										&& !(movieJson.getString("Poster")).equals("N/A"))
									break;
								else
									year--;
							} else {
								year--;
							}
						} catch (JSONException e1) {
	
							e1.printStackTrace();
						}
					}
	
					try {
						if (!movieJSONString.equals(notFound)) {
	
							jsonMovie.put("poster", movieJson.getString("Poster"));
							jsonMovie.put("genre", movieJson.getString("Genre"));
							jsonMovie.put("release_date", movieJson.getString("Released"));
							jsonMovie.put("rating", movieJson.getString("imdbRating"));
							jsonMovie.put("runtime", movieJson.getString("Runtime"));
							jsonMovie.put("actors", movieJson.getString("Actors"));
							jsonMovie.put("synopsis", movieJson.getString("Plot"));
	
						} else {
							jsonMovie.put("poster", "");
							jsonMovie.put("genre", "N/A");
							jsonMovie.put("release_date", "N/A");
							jsonMovie.put("rating", "N/A");
							jsonMovie.put("runtime", "N/A");
							jsonMovie.put("actors", "N/A");
							jsonMovie.put("synopsis", "Movie details not found.");
	
						}
						jsonMovie.put("id", mov);
						jsonMovie.put("title",  title);
						jsonMovie.put("description", desc);
						jsonArrMovies.put(jsonMovie);
						
	
					} catch (JSONException e1) {
	
						e1.printStackTrace();
					}

				}
				try {
					int indx = dateTitle.indexOf(",");
					String subStr = dateTitle.substring(indx, indx+6);
					dateTitle = dateTitle.replace(subStr, "");
					jsonDate.put("dateTitle", dateTitle);
					
					jsonDate.put("movies", jsonArrMovies);
					jsonArrSchedule.put(jsonDate);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			Constant.weeklyScheduleJsonArr = jsonArrSchedule;
		}

		public String getResponseString(String url) {
			String responseString = "";
			try {
				// Setup the object that makes the http request

				HttpClient httpclient = new DefaultHttpClient();
				HttpRequestBase request = new HttpGet(url);
				// request.addHeader(Utils.getBasicAuthenticationHeader(context));
				// request.addHeader("User-Agent", Constant.HTTP_USER_AGENT);
				HttpResponse response = httpclient.execute(request);
				StatusLine statusLine = response.getStatusLine();

				// If there was no error in the http request
				if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					response.getEntity().writeTo(out);
					out.close();
					responseString = out.toString();
				} else {
					response.getEntity().getContent().close();
					// Log.i("getResponseString", responseString);
					throw new IOException(statusLine.getReasonPhrase());
				}
			} catch (Exception e) {
				e.printStackTrace();
				Utils.closeProgressDialog(context);

				txtError.setVisibility(View.VISIBLE);
				txtError.setText("No Internet Connection");

			}
			return responseString;
		}

	}

}
