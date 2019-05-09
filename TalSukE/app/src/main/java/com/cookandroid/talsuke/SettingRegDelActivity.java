package com.cookandroid.talsuke;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingRegDelActivity extends AppCompatActivity {

    Button regDelOK;
    Button regDelCancel;
    EditText regDelPW;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_reg_del);

        regDelOK = (Button) findViewById(R.id.reg_del_ok);
        regDelCancel = (Button) findViewById(R.id.reg_del_cancel);
        regDelPW = (EditText) findViewById(R.id.reg_del_pw);

        regDelOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (regDelPW.toString().replace(" ", "").equals("")) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SettingRegDelActivity.this);
                    builder.setTitle("정말 탈퇴하시겠습니까?");
                    ((AlertDialog.Builder) builder).setMessage("회원정보는 삭제됩니다.");
                    ((AlertDialog.Builder) builder).setPositiveButton("삭제하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //비밀번호가 같은지 확인하고 같다면 회원을 삭제함.
                            finish();
                        }
                    });
                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.show();
                }

            }
        });

        regDelCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
