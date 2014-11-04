/**
 * 
 */
package com.example.hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author b1012234
 *
 */
public class AsyncTaskTest extends AsyncTask<ArrayAdapter<String> , Void, String> {
	
	private Activity TourActivity;
	ArrayAdapter<String> adapter;
	ListView reviewList;
	
	public AsyncTaskTest(Activity activity) {

        // 呼び出し元のアクティビティ
        this.TourActivity = activity;
    }

	


	/* (非 Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected String doInBackground(ArrayAdapter<String>... params) {
		// TODO 自動生成されたメソッド・スタブ
		// httpリクエスト投げる処理を書く。
        // ちなみに私はHttpClientを使って書きましたー
		
		try {
			URL cjURL = new URL("http://rhyth.dip.jp/213test_post.php");
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
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.d("e1", "JSONException");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d("e2", "IOException");
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	protected void onPreExecute() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPreExecute();
		reviewList.setAdapter(adapter);
	}

}
