package com.cookandroid.talsuke;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    EditText username;
    EditText password1;
    EditText password2;
    EditText name;
    EditText address1;
    EditText address2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("회원가입");

        username = findViewById(R.id.reg_id);
        password1 = findViewById(R.id.reg_pw1);
        password2 = findViewById(R.id.reg_pw2);
        name = findViewById(R.id.reg_name);
        address1 = findViewById(R.id.reg_address1);
        address2 = findViewById(R.id.reg_address2);
    }

    void register(View v){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username.getText());
            jsonObject.put("password", password1.getText());
            jsonObject.put("name", name.getText());
            jsonObject.put("address1", address1.getText());
            jsonObject.put("address2", address2.getText());

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
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
