package com.cookandroid.talsuke;

import android.annotation.SuppressLint;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private Button weight;
    private TextView fee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("탈숙이");

    }

    @Override
    public void onBackPressed() {
        System.out.println(getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", ""));
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setCancelable(true);
        builder.setTitle("종료 확인");
        builder.setMessage("종료하시겠어요?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", "");
                        System.out.println("AAAA" + username);
                        finishAffinity();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void update(View v) {

    }

    void dehydration(View v) {

    }

    void me(View v) {
        Intent intent = new Intent(getApplicationContext(), MeActivity.class);
        startActivity(intent);
    }

    void local(View v) {
        Intent intent = new Intent(getApplicationContext(), LocalActivity.class);
        startActivity(intent);
    }

    void info(View v) {
        Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
        startActivity(intent);
    }

    void setting(View v) {
        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
        startActivityForResult(intent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_CANCELED) {
            switch (requestCode){
                case 1000:
                    String username = getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", "");
                    if (username != null && username.equals("")) {
                        Toast.makeText(getApplicationContext(), "로그아웃 되었습니다.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    break;
            }
        }
    }
}
