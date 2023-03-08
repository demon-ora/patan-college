package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Blog extends AppCompatActivity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        list = findViewById(R.id.mylist);
        extractBlog();
    }

    private void extractBlog() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.bimash.com.np/patan/api/v1/get?token=cGF0YW5fYmNhZ3V5cw==&blog=true";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("mainresponse", "this is main" + response);
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray dataArray = jsonObject.getJSONArray("data");

                            String[] images = new String[dataArray.length()];
                            String[] descriptions = new String[dataArray.length()];
                            String[] codes = new String[dataArray.length()];

                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject dataObject = dataArray.getJSONObject(i);

                                String image = dataObject.getString("image");
                                String code = dataObject.getString("code");
                                String description = dataObject.getString("description");
                                images[i] = image;
                                descriptions[i] = description;
                                codes[i] = code;
                            }
                            MyAdapter adapter = new MyAdapter(Blog.this, codes, descriptions, images);
                            list.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("apierror", "this is error " + error.toString());

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
