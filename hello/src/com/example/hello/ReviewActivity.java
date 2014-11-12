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
	
	private Activity reviewActivity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review);
		reviewActivity = this;

		Button send = (Button) findViewById(R.id.sendButton);
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String body="";
				//--- �c�A�[���r���[�ۑ����� ---//
				RatingBar rb = (RatingBar) findViewById(R.id.ratingBar);
				float f = rb.getRating(); // 5�i�K�]���擾
				
				EditText detail = (EditText) findViewById(R.id.reviewDetail);
				String s = detail.getText().toString(); // �ڍ׃e�L�X�g�擾
				
				//JSON�`���ł̕ۑ�����
				
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost("http://rhyth.dip.jp/213test_get.php");

				JSONObject json = new JSONObject();
				try {
					json.accumulate("rating",(double)f);
					json.accumulate("detail",s);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				PostJsonTask pjt = new PostJsonTask(reviewActivity);
				pjt.execute(json.toString());
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
}