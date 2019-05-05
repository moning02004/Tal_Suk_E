package com.cookandroid.talsuke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoClickedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_clicked);

        Intent intent = getIntent();

        TextView title = (TextView) findViewById(R.id.info_clicked_title);
        TextView author = (TextView) findViewById(R.id.info_clicked_author);
        TextView date = (TextView) findViewById(R.id.info_clicked_date);
        TextView content = (TextView) findViewById(R.id.info_clicked_content);

        title.setText(intent.getStringExtra("info_title"));
        author.setText(intent.getStringExtra("info_author"));
        date.setText(intent.getStringExtra("info_date"));
        content.setText(intent.getStringExtra("info_content"));
    }
}
