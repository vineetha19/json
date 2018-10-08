package com.example.admin.json;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressDialog mProgressDialog;
    private ListView listView;
    private List<JobListItems> gs;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        String url = "https://raw.githubusercontent.com/nvlakshmi/JsonWithAsynctask/master/VedioPlay";
        new ResponseAsync().execute(url);


    }

    private class ResponseAsync extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("get response Tutorial");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();

        }


        @Override
        protected String doInBackground(String... url) {
            String respURL = url[0];

            try {
                URL ur = new URL(respURL);
                InputStream is = ur.openConnection().getInputStream();

                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }


                return buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override

        protected void onPostExecute(String s) {
            List<JobListItems> gsList = new ArrayList<>();
            try {
                JSONArray array = new JSONArray(s);

                for (int i = 0; i < array.length(); i++) {
                    JSONObject list_obj = array.getJSONObject(i);
                    JobListItems item = new JobListItems();
                    item.setDes(list_obj.getString("description"));
                    item.setId(list_obj.getString("id"));
                    item.setTitle(list_obj.getString("title"));
                    item.setImage(list_obj.getString("thumb"));
                    item.setUrl(list_obj.getString("url"));

                    gsList.add(item);
                }


                //JSONObject object= array.getJSONObject();


            } catch (JSONException e) {
                e.printStackTrace();

            }
            Adapter adapter = new Adapter(getApplicationContext(), gsList);
            listView.setAdapter(adapter);
            mProgressDialog.dismiss();


        }
    }
}