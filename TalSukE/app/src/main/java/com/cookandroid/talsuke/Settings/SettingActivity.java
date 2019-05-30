package com.cookandroid.talsuke.Settings;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cookandroid.talsuke.Main.AdminHomeActivity;
import com.cookandroid.talsuke.R;
import com.cookandroid.talsuke.Main.UserHomeActivity;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        this.setTitle("Setting");
    }

    void logout(View v) {
        SharedPreferences.Editor editor = getSharedPreferences("SESSION", MODE_PRIVATE).edit();
        editor.remove("username");
        editor.remove("fee");
        editor.apply();
        finish();
    }

    void regDelete(View v) {
        Intent intent = new Intent(getApplicationContext(), SettingRegDelActivity.class);
        startActivityForResult(intent, 404);
    }

    void regInfo(View v) {
        Intent intent = new Intent(getApplicationContext(), SettingRegInfoActivity.class);
        startActivity(intent);
    }

    void regback(View v) {
        Intent intent = new Intent(getApplicationContext(), UserHomeActivity.class);
        startActivity(intent);
    }

    void regWF(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
        builder.setCancelable(true);
        builder.setTitle("단위당 무게 수정");
        builder.setMessage("단위당 무게를 수정합니다.");
        final EditText WFet = new EditText(SettingActivity.this);
        WFet.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        builder.setView(WFet);

        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_CANCELED) {
            if (requestCode == 404) {
                finish();
            }
        }
    }
}
