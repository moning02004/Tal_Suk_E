package com.cookandroid.talsuke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeTest3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_test3);
        TextView text1 = (TextView) findViewById(R.id.textView4);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeTestActivity.class);
                startActivity(intent);
            }
        });
        TextView text2 = (TextView) findViewById(R.id.textView5);
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeTest2Activity.class);
                startActivity(intent);
            }
        });

        TextView text3 = (TextView)findViewById(R.id.textView6);
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeTest3Activity.class);
                startActivity(intent);
            }
        });

        TextView text4 = (TextView)findViewById(R.id.textView7);
        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeTest4Activity.class);
                startActivity(intent);
            }
        });
    }
}
