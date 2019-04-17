package com.cookandroid.talsuke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        LinearLayout button1 = (LinearLayout) findViewById(R.id.btn1);
        LinearLayout button2 = (LinearLayout) findViewById(R.id.btn2);
        LinearLayout button3 = (LinearLayout) findViewById(R.id.btn3);
        LinearLayout button4 = (LinearLayout) findViewById(R.id.btn4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame, new MyFragment1()).commit();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame, new MyFragment2()).commit();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame, new MyFragment3()).commit();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame, new MyFragment4()).commit();
            }
        });
    }
}
