package com.cookandroid.talsuke;

import android.annotation.SuppressLint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class HomeActivity extends AppCompatActivity {
    private TextView weight;
    private TextView fee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("탈숙이");

        weight = findViewById(R.id.home_weight);
        fee = findViewById(R.id.home_fee);
    }

    void refresh(View v){
        JSONObject userInfo = new JSONObject();
        System.out.println(getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", ""));
        try {
            userInfo.put("username", getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            @SuppressLint("StaticFieldLeak")
            JsonConnection connection = new JsonConnection(Constant.REFRESH_URL){
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
