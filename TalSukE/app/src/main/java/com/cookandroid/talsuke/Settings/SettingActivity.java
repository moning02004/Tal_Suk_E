package com.cookandroid.talsuke.Settings;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cookandroid.talsuke.R;
import com.cookandroid.talsuke.Main.UserHomeActivity;

public class SettingActivity extends AppCompatActivity {

    Button regWFBtn;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        this.setTitle("Setting");
        final EditText edittext = new EditText(this);
        regWFBtn = (Button)findViewById(R.id.reg_wf_btn);

        regWFBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(SettingActivity.this);
                dlg.setTitle("단위당 요금 설정");
                dlg.setMessage("kg당 요금을 설정해 주세요.");
                dlg.setView(edittext);
                dlg.setPositiveButton("입력",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),edittext.getText().toString() ,Toast.LENGTH_LONG).show();
                            }
                        });
                dlg.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                dlg.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        return;
    }

    void logout(View v) {
        SharedPreferences.Editor editor = getSharedPreferences("SESSION", MODE_PRIVATE).edit();
        editor.remove("username");
        editor.apply();
        finish();
    }

    void regDelete(View v) {
        Intent intent = new Intent(getApplicationContext(), SettingRegDelActivity.class);
        startActivity(intent);
    }

    void regInfo(View v) {
        Intent intent = new Intent(getApplicationContext(), SettingRegInfoActivity.class);
        startActivity(intent);
    }

    void regback(View v) {
        Intent intent = new Intent(getApplicationContext(), UserHomeActivity.class);
        startActivity(intent);
    }
<<<<<<< HEAD:TalSukE/app/src/main/java/com/cookandroid/talsuke/SettingActivity.java

    void regWF(View v){


    }
=======
>>>>>>> upstream/master:TalSukE/app/src/main/java/com/cookandroid/talsuke/Settings/SettingActivity.java
}
