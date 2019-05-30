package com.cookandroid.talsuke.Infomation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cookandroid.talsuke.Main.Constant;
import com.cookandroid.talsuke.Main.JsonConnection;
import com.cookandroid.talsuke.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class UserInfoClickedActivity extends AppCompatActivity {
    private TextView infoClickedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_clicked);
        this.setTitle("회원 정보 조회");

        infoClickedID = (TextView) findViewById(R.id.user_info_clicked_id);
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

    void user_info_reset(View v) throws IOException, JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", this.infoClickedID.getText().toString());
        @SuppressLint("StaticFieldLeak") JsonConnection jsonConnection = new JsonConnection(Constant.RESET_PASSWORD){
            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                try {
                    if (jsonObject.getString("message").equals("Success")) {
                        Toast.makeText(getApplicationContext(), "초기화 되었습니다.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "초기화 실패했습니다.", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        jsonConnection.execute(jsonObject);
        Toast.makeText(getApplicationContext(), "회원 비밀번호가 초기화 되었습니다.", Toast.LENGTH_SHORT).show();
    }
}
