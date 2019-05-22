package com.cookandroid.talsuke.Settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.cookandroid.talsuke.R;

public class SettingRegInfoActivity extends AppCompatActivity {

    Switch regInfoSwitch;
    EditText regInfoID;
    EditText regInfoPW1;
    EditText regInfoPW2;
    EditText regInfoName;
    EditText regInfoAddress;
    EditText regInfoAddressDetail;
    Button regInfoOK;
    Button regInfoCancel;
    ImageView regInfoSee;
    Boolean regInfoSeeCheck = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_reg_info);


        regInfoSwitch = (Switch) findViewById(R.id.reg_info_switch);
        regInfoID = (EditText) findViewById(R.id.reg_info_id);
        regInfoPW1 = (EditText) findViewById(R.id.reg_info_pw1);
        regInfoPW2 = (EditText) findViewById(R.id.reg_info_pw2);
        regInfoName = (EditText) findViewById(R.id.reg_info_name);
        regInfoAddress = (EditText) findViewById(R.id.reg_info_address);
        regInfoAddressDetail = (EditText) findViewById(R.id.reg_info_address_detail);
        regInfoOK = (Button) findViewById(R.id.reg_info_ok);
        regInfoCancel = (Button) findViewById(R.id.reg_info_cancel);
        regInfoSee = (ImageView) findViewById(R.id.reg_info_see);

        regInfoPW1.setTransformationMethod(PasswordTransformationMethod.getInstance());
        regInfoPW2.setTransformationMethod(PasswordTransformationMethod.getInstance());

        setInfo();

        regInfoSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (regInfoSwitch.isChecked()) {
                    infoChangeTrue();
                } else {
                    infoChangeFalse();
                }
            }


        });

        regInfoOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!regInfoPW1.getText().toString().equals(regInfoPW2.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                finish();
            }
        });

        regInfoCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        regInfoSee.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (regInfoSeeCheck == true) {
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
        });
    }

    void setInfo() {
        regInfoID.setText("아이디");
        regInfoPW1.setText("비번1");
        regInfoPW2.setText("비번2");
        regInfoName.setText("이름");
        regInfoAddress.setText("주소");
        regInfoAddressDetail.setText("상세주소");
    }

    void infoChangeTrue() {
        regInfoID.setFocusableInTouchMode(true);
        regInfoID.setFocusable(true);
        regInfoPW1.setFocusableInTouchMode(true);
        regInfoPW1.setFocusable(true);
        regInfoPW2.setFocusableInTouchMode(true);
        regInfoPW2.setFocusable(true);
        regInfoName.setFocusableInTouchMode(true);
        regInfoName.setFocusable(true);
        regInfoAddress.setFocusableInTouchMode(true);
        regInfoAddress.setFocusable(true);
        regInfoAddressDetail.setFocusableInTouchMode(true);
        regInfoAddressDetail.setFocusable(true);
    }

    void infoChangeFalse() {
        regInfoID.setFocusableInTouchMode(false);
        regInfoID.setFocusable(false);
        regInfoPW1.setFocusableInTouchMode(false);
        regInfoPW1.setFocusable(false);
        regInfoPW2.setFocusableInTouchMode(false);
        regInfoPW2.setFocusable(false);
        regInfoName.setFocusableInTouchMode(false);
        regInfoName.setFocusable(false);
        regInfoAddress.setFocusableInTouchMode(false);
        regInfoAddress.setFocusable(false);
        regInfoAddressDetail.setFocusableInTouchMode(false);
        regInfoAddressDetail.setFocusable(false);
    }
}
