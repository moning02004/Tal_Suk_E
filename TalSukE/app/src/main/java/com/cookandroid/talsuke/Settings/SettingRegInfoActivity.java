package com.cookandroid.talsuke.Settings;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.cookandroid.talsuke.Main.Constant;
import com.cookandroid.talsuke.Main.JsonConnection;
import com.cookandroid.talsuke.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SettingRegInfoActivity extends AppCompatActivity {
    private Switch regInfoSwitch;
    private TextView regInfoID;
    private EditText regInfoPW1, regInfoPW2, regInfoName, regInfoPhone, regInfoFee;
    ImageView regInfoSee;
    Boolean regInfoSeeCheck = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_reg_info);
        this.setTitle("회원 정보");

        regInfoSwitch = (Switch) findViewById(R.id.reg_info_switch);
        regInfoID = (TextView) findViewById(R.id.reg_info_id);
        regInfoPW1 = (EditText) findViewById(R.id.reg_info_pw1);
        regInfoPW2 = (EditText) findViewById(R.id.reg_info_pw2);
        regInfoName = (EditText) findViewById(R.id.reg_info_name);
        regInfoPhone = (EditText) findViewById(R.id.reg_info_phone);
        regInfoFee = (EditText) findViewById(R.id.reg_info_fee);

        regInfoPW1.setTransformationMethod(PasswordTransformationMethod.getInstance());
        regInfoPW2.setTransformationMethod(PasswordTransformationMethod.getInstance());

        try {
            String username = getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", "");
            JSONObject userInfo = new JSONObject();
            userInfo.put("username", username);
            @SuppressLint("StaticFieldLeak") JsonConnection connection = new JsonConnection(Constant.GET_INFO) {
                @Override
                protected void onPostExecute(JSONObject jsonObject) {
                    try {
                        regInfoID.setText(getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", ""));
                        regInfoName.setText(jsonObject.getString("name").toString());
                        regInfoPhone.setText(jsonObject.getString("phone").toString());
                        regInfoFee.setText(jsonObject.getString("fee").toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            connection.execute(userInfo);
        } catch (IOException | JSONException e){
            e.printStackTrace();
        }
    }
    void buttonOK(View v) {
        if (!regInfoPW1.getText().toString().equals(regInfoPW2.getText().toString())) {
            Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(SettingRegInfoActivity.this);
            builder.setCancelable(true);
            builder.setTitle("회원 정보 수정");
            builder.setMessage("현재 비밀번호를 입력해 주세요.");
            final EditText PW = new EditText(SettingRegInfoActivity.this);
            PW.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            builder.setView(PW);
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("username", getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", ""));
                        jsonObject.put("password", regInfoPW1.getText().toString());
                        jsonObject.put("current_password", PW.getText().toString());
                        jsonObject.put("name", regInfoName.getText().toString());
                        jsonObject.put("phone", regInfoPhone.getText().toString());
                        jsonObject.put("fee", getSharedPreferences("SESSION", MODE_PRIVATE).getString("fee", "0"));
                        @SuppressLint("StaticFieldLeak") JsonConnection jsonConnection = new JsonConnection(Constant.EDIT_URL){
                            @Override
                            protected void onPostExecute(JSONObject jsonObject) {
                                if(jsonObject == null ) return;
                                try {
                                    if(jsonObject.getString("message").equals("Success")){
                                        Toast.makeText(getApplicationContext(), "변경되었습니다.", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(), "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        jsonConnection.execute(jsonObject);
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
    void buttonCancel(View v) {
        finish();
    }
    void showPW(View v) {
        if (regInfoSeeCheck) {
            regInfoSee.setImageResource(R.drawable.see1);
            regInfoPW1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            regInfoPW2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            regInfoSeeCheck = false;
        } else {
            regInfoSee.setImageResource(R.drawable.see2);
            regInfoPW1.setTransformationMethod(PasswordTransformationMethod.getInstance());
            regInfoPW2.setTransformationMethod(PasswordTransformationMethod.getInstance());
            regInfoSeeCheck = true;
        }
    }

    void toggle(View v) {
        boolean isChecked = regInfoSwitch.isChecked();
        regInfoPW1.setFocusableInTouchMode(isChecked);
        regInfoPW1.setFocusable(isChecked);
        regInfoPW2.setFocusableInTouchMode(isChecked);
        regInfoPW2.setFocusable(isChecked);
        regInfoName.setFocusableInTouchMode(isChecked);
        regInfoName.setFocusable(isChecked);
        regInfoPhone.setFocusableInTouchMode(isChecked);
        regInfoPhone.setFocusable(isChecked);
        regInfoFee.setFocusableInTouchMode(isChecked);
        regInfoFee.setFocusable(isChecked);
    }
}