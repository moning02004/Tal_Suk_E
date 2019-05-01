package com.cookandroid.talsuke;

import android.annotation.SuppressLint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private TextView weight;
    private TextView fee;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("탈숙이");

        weight = findViewById(R.id.home_weight);
        fee = findViewById(R.id.home_fee);
        result = findViewById(R.id.home_result);
    }

    void refresh(View v){
        JSONObject userInfo = new JSONObject();
        try {
            userInfo.put("username", getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            @SuppressLint("StaticFieldLeak")
            JsonConnection connection = new JsonConnection(Constant.REFRESH_URL) {
                @Override
                protected void onPostExecute(JSONObject jsonObject) {
                    System.out.println(jsonObject);
                    try {
                        System.out.println(jsonObject.getJSONArray("year"));
                        ArrayList<Items> items = new ArrayList<Items>() {};
                        for (int y = 0; y < jsonObject.getJSONArray("year").length(); y++) {
                            System.out.println(jsonObject.getJSONArray("year").get(y));
                            JSONObject year = (JSONObject) jsonObject.getJSONArray("year").get(y);
                            System.out.println(year.getString("num"));

                            for (int m = 0; m < year.getJSONArray("month").length(); m++) {
                                JSONObject month = (JSONObject) year.getJSONArray("month").get(m);
                                System.out.println(month.getString("num"));

                                for (int d = 0; d < month.getJSONArray("day").length(); d++) {
                                    JSONObject day = (JSONObject) month.getJSONArray("day").get(d);
                                    System.out.println(day.getString("num"));
                                    Items item = new Items(
                                            year.getString("num")+"년 "+month.getString("num")+"월 "+day.getString("num")+"일",
                                            Integer.parseInt(day.getString("weight")),
                                            Integer.parseInt(day.getString("fee")));
                                    items.add(item);
                                }
                            }
                        }
                        for (int i=0; i<items.size(); i++) {
                            System.out.println(items.get(i).getDate());
                            result.setText(items.get(i).getDate());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            connection.execute(userInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void save(View v){
        JSONObject userInfo = new JSONObject();
        try {
            userInfo.put("username", getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", ""));
            userInfo.put("year", "2019");
            userInfo.put("month", "4");
            userInfo.put("day", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            @SuppressLint("StaticFieldLeak")
            JsonConnection connection = new JsonConnection(Constant.SAVE_URL){
                @Override
                protected void onPostExecute(JSONObject jsonObject) {
                    System.out.println(jsonObject);
                }
            };
            connection.execute(userInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
