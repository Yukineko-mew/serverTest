package com.example.hello;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GetJsonTask extends AsyncTask<String, Integer, String>{

	private String response;
    private Activity tourActivity;

    public GetJsonTask(Activity activity) {

        // 呼び出し元のアクティビティ
        this.tourActivity = activity;
    }
	
	@Override
	protected String doInBackground(String... contents) {
		// TODO Auto-generated method stub
		
        // 通信の際に送信するデータを付加
        // 今回の場合はレビューに対応したツアーのid
        ArrayList <NameValuePair> params = new ArrayList <NameValuePair>();
        params.add( new BasicNameValuePair("tour_id", contents[0]));
        String query = URLEncodedUtils.format(params, "UTF-8");
        
		// 送信先URLを指定して通信を確立
		String url="http://rhyth.dip.jp/213test_post.php";
		HttpGet httpGet = new HttpGet(url + "/?" + query);
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse httpResponse = null;

		try {
			 httpResponse = client.execute(httpGet);

			// ステータスコードを取得
			int statusCode = httpResponse.getStatusLine().getStatusCode();

			// レスポンスを取得
			HttpEntity entity = httpResponse.getEntity();
			response = EntityUtils.toString(entity);

			// リソースを解放
			entity.consumeContent();

			// クライアントを終了させる
			client.getConnectionManager().shutdown();
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
	
	   // このメソッドは非同期処理の終わった後に呼び出されます
    @Override
	protected void onPostExecute(String result) {
		try {
			JSONObject jsonResponse = new JSONObject(result);

			JSONArray data = jsonResponse.getJSONArray("cj");

			ListView reviewList = (ListView) tourActivity
					.findViewById(R.id.reviewList);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					tourActivity,
					android.R.layout.simple_expandable_list_item_1);

			for (int i = 0; i < data.length(); i++) {
				JSONObject c = data.getJSONObject(i);
				adapter.add("["+i + "] rating:" + c.getString("rating") + ", detail:"
						+ c.getString("detail"));
				Log.d("JOSN(" + i + ")", "rating:" + c.getString("rating")
						+ ", detail:" + c.getString("detail"));
			}

			reviewList.setAdapter(adapter);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
