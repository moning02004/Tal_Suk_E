package com.cookandroid.talsuke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeTurnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_turn);

        TextView turn = (TextView) findViewById(R.id.turnButton);
        turn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeTurnActivity.class);
                startActivity(intent);
            }
        });
        TextView graph = (TextView) findViewById(R.id.graphButton);
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeGraphActivity.class);
                startActivity(intent);
            }
        });

        TextView main = (TextView)findViewById(R.id.mainButton);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeMainActivity.class);
                startActivity(intent);
            }
        });

        TextView setting = (TextView)findViewById(R.id.settingButton);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeSettingActivity.class);
                startActivity(intent);
            }
        });

    }
}

