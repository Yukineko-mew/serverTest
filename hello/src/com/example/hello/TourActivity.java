package com.example.hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class TourActivity extends Activity {
	
	ListView reviewList;
	ArrayAdapter<String> adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tour);

		RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
		TextView t = (TextView) findViewById(R.id.textview02_id);
		reviewList = (ListView) findViewById(R.id.reviewList);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1);
		
		// --- ���r���[�t�H�[���ւ̑J�ڃ{�^�� ---///
		Button btn = (Button) findViewById(R.id.reviewTransition);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TourActivity.this,
						ReviewActivity.class);
				startActivityForResult(intent, 0);
			}
		});
		;

		// --- �߂�{�^�� ---//
		Button back = (Button) findViewById(R.id.backButton);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		;	
		
		// Bundle extra = getIntent().getExtras();
		// float rating = extra.getFloat("rating", 0);
		// String detail = extra.getString("detail");

		// TextView tv = (TextView) findViewById(R.id.textview02_id);
		// tv.setText(detail);
		// if(!(detail==null)) {
		// adapter.add(detail);
		// lv.setAdapter(adapter);
		// }
	}

	@Override
	public void onResume() {
		super.onResume();
		try {
			URL cjURL = new URL("hogehogehogehoge");
			URLConnection con = cjURL.openConnection();
			con.setDoOutput(true);
			
			InputStreamReader isr = new InputStreamReader(con.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			// �P�s�������o��
			
			JSONObject jsonResponse = new JSONObject(br.readLine());
			JSONArray data = jsonResponse.getJSONArray("cj");

			// ��ڂ��擾
			for(int i=0; i<data.length(); i++ ) {
				JSONObject c = data.getJSONObject(i);
				adapter.add("rating:"+c.getString("rating")+", detail:"+c.getString("detail"));
				Log.d("JOSN("+i+")","rating:"+c.getString("rating")+", detail:"+c.getString("detail"));
			}
			br.close();
			isr.close();
			
			reviewList.setAdapter(adapter);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.d("e1", "JSONException");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d("e2", "IOException");
			e.printStackTrace();
		}

	}
	
	private void json_parse(String jsondata) {

		try {
			JSONObject jsonResponse = new JSONObject(jsondata);

			JSONArray data = jsonResponse.getJSONArray("cj");

			for (int i = 0; i < data.length(); i++) {

				JSONObject c = data.getJSONObject(i);

				Log.d("rating", c.getString("rating"));
				Log.d("detail", c.getString("detail"));
			}
		} catch (JSONException e) {

			e.printStackTrace();
		}

	}
}
