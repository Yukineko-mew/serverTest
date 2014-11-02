package com.example.hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class ReviewActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review);

		Button send = (Button) findViewById(R.id.sendButton);
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String body="";
				//--- ツアーレビュー保存処理 ---//
				RatingBar rb = (RatingBar) findViewById(R.id.ratingBar);
				float f = rb.getRating(); // 5段階評価取得
				
				EditText detail = (EditText) findViewById(R.id.reviewDetail);
				String s = detail.getText().toString(); // 詳細テキスト取得
				
				//JSON形式での保存処理
				
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost("hogehogehogehoge");

				JSONObject json = new JSONObject();
				try {
					json.accumulate("rating",(double)f);
					json.accumulate("detail",s);
					Log.d("CREATE_JSON", json.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					Log.d("CREATE_ERR_JSON", e.toString());
					e.printStackTrace();
				}
				
				try {
					StringEntity se = new StringEntity(json.toString(), "UTF-8");
					
					httpPost.setEntity(se);
					httpPost.setHeader("Accept", "application/json");
					httpPost.setHeader("Content-type", "application/json");
					Log.d("Ready", "SUCCESS the dataset.");
					try {
						Log.d("GO!!!!!", "startHTTP Response");
						HttpResponse httpResponse = httpClient.execute(httpPost);
						Log.d("Finish!!!!", "finish the response");

						InputStream inputStream = httpResponse.getEntity().getContent();
												
			            if(inputStream != null)
			                Log.d("RESPONSE", convertInputStreamToString(inputStream));
			            else
			            	Log.d("RE_SUCCESS", json.toString());
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						Log.d("ClientProtocol", e.toString());
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						Log.d("IOException", e.toString());
						e.printStackTrace();
					}
			
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					Log.d("UnsupportEncodingError", e.toString());
					e.printStackTrace();
				}

	            finish();
			}
		});;
		
		Button cancel = (Button) findViewById(R.id.cancelButton);
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});;
	}
	
	private static String convertInputStreamToString(InputStream inputStream) throws IOException{
	    BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
	    String line = "";
	    String result = "";
	    while((line = bufferedReader.readLine()) != null)
	        result += line;

	    inputStream.close();
	    return result;

	}
}
