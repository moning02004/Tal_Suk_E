package com.cookandroid.talsuke.Infomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cookandroid.talsuke.R;

public class UserInfoClickedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_clicked);
        this.setTitle("회원 정보 조회");

        TextView infoClickedID = (TextView) findViewById(R.id.user_info_clicked_id);
        TextView infoClickedName = (TextView) findViewById(R.id.user_info_clicked_name);
        TextView infoClickedPhone = (TextView) findViewById(R.id.user_info_clicked_phone);

        Intent intent = getIntent();
        infoClickedID.setText(intent.getStringExtra("user_info_ID"));
        infoClickedName.setText(intent.getStringExtra("user_info_name"));
        infoClickedPhone.setText(intent.getStringExtra("user_info_phone"));
    }

    void user_info_cancel(View v){
        finish();
    }

    void user_info_reset(View v){
        Toast.makeText(getApplicationContext(), "회원 비밀번호가 초기화 되었습니다.", Toast.LENGTH_SHORT).show();
    }
}
