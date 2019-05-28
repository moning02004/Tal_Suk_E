package com.cookandroid.talsuke.Main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cookandroid.talsuke.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    private EditText username;
    private TextView check;
    private EditText password1;
    private EditText password2;
    private EditText name;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("회원가입");

        username = findViewById(R.id.reg_id);
        check = findViewById(R.id.reg_check);
        password1 = findViewById(R.id.reg_pw1);
        password2 = findViewById(R.id.reg_pw2);
        name = findViewById(R.id.reg_name);
        phone = findViewById(R.id.reg_phone);
    }
    void check(View v) throws IOException {
        if (username.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "아이디를 입력하십시오.", Toast.LENGTH_SHORT).show();
            return;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username.getText());
            @SuppressLint("StaticFieldLeak")
            JsonConnection jsonConnection = new JsonConnection(Constant.CHECK_URL+username.getText().toString()+"/") {
                @Override
                protected void onPostExecute(JSONObject jsonObject) {
                    try {
                        if (jsonObject.getString("message").equals("Success")) {
                            check.setText("사용가능합니다.");
                        } else {
                            check.setText("사용할 수 없습니다.");
                        }
                        super.onPostExecute(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            jsonConnection.execute(jsonObject);
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "에러 발생", Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("ShowToast")
    void register(View v){
        if (!validateUsername()) {
            Toast.makeText(getApplicationContext(), "CHECK 버튼을 클릭하십시오.", Toast.LENGTH_LONG).show();
            return;
        }
        if (!password1.getText().toString().equals(password2.getText().toString())) {
            Toast.makeText(getApplicationContext(), "입력하신 비밀번호를 확인하십시오.", Toast.LENGTH_LONG).show();
            return;
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username.getText());
            jsonObject.put("password", password1.getText());
            jsonObject.put("name", name.getText());
            jsonObject.put("phone", phone.getText());

            @SuppressLint("StaticFieldLeak")
            JsonConnection jsonConnection = new JsonConnection(Constant.REGISTER_URL) {
                @Override
                protected void onPostExecute(JSONObject jsonObject) {
                    try {
                        if (jsonObject.getString("message").equals("Success")) {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            jsonConnection.execute(jsonObject);
        } catch (IOException | JSONException e) {
            Toast.makeText(getApplicationContext(), "에러 발생", Toast.LENGTH_LONG).show();
        }
    }

    boolean validateUsername(){
        return check.getText().equals("사용가능합니다.");
    }
}
