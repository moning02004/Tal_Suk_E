package com.cookandroid.talsuke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

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
                finish();
            }
        });

        regInfoCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void infoChangeTrue() {
        regInfoID.setFocusableInTouchMode(true);
        regInfoPW1.setFocusableInTouchMode(true);
        regInfoPW2.setFocusableInTouchMode(true);
        regInfoName.setFocusableInTouchMode(true);
        regInfoAddress.setFocusableInTouchMode(true);
        regInfoAddressDetail.setFocusableInTouchMode(true);
    }

    void infoChangeFalse() {
        regInfoID.setFocusableInTouchMode(false);
        regInfoPW1.setFocusableInTouchMode(false);
        regInfoPW2.setFocusableInTouchMode(false);
        regInfoName.setFocusableInTouchMode(false);
        regInfoAddress.setFocusableInTouchMode(false);
        regInfoAddressDetail.setFocusableInTouchMode(false);
    }
}
