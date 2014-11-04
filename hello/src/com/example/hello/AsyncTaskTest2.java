/**
 * 
 */
package com.example.hello;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * @author b1012234
 *
 */
public class AsyncTaskTest2 extends AsyncTask<String, Void, String> {

	private Activity ReviewActivity;
	
	public AsyncTaskTest2(Activity activity) {

        // 呼び出し元のアクティビティ
        this.ReviewActivity = activity;
    }

	/* (非 Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected String doInBackground(String... params) {
		// TODO 自動生成されたメソッド・スタブ
		
		
		//JSON�`���ł̕ۑ�����
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://rhyth.dip.jp/213test_get.php");

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
		return null;
	}
	
	


}
