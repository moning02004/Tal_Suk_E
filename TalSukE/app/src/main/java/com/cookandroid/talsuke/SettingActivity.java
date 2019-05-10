package com.cookandroid.talsuke;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        this.setTitle("Setting");
    }

    void logout(View v) {
        SharedPreferences.Editor editor = getSharedPreferences("SESSION", MODE_PRIVATE).edit();
        editor.remove("username");
        editor.apply();
        finish();
    }

    void regDelete(View v) {
        Intent intent = new Intent(getApplicationContext(), SettingRegDelActivity.class);
        startActivity(intent);
    }

    void regInfo(View v) {
        Intent intent = new Intent(getApplicationContext(), SettingRegInfoActivity.class);
        startActivity(intent);
    }

    void regback(View v) {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }

    void WF(View v){
        Intent intent = new Intent(this, WeightfeePopupActivity.class);
        startActivity(intent);
    }
}
