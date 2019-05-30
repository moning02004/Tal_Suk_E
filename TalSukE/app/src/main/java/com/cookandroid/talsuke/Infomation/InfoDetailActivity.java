package com.cookandroid.talsuke.Infomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cookandroid.talsuke.R;

public class InfoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_clicked);
        this.setTitle("공지사항");

        TextView title = (TextView) findViewById(R.id.info_clicked_title);
        TextView date = (TextView) findViewById(R.id.info_clicked_date);
        TextView content = (TextView) findViewById(R.id.info_clicked_content);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("info_title"));
        date.setText(intent.getStringExtra("info_date"));
        content.setText(intent.getStringExtra("info_content"));
    }
}