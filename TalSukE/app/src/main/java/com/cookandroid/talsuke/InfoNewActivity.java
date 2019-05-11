package com.cookandroid.talsuke;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class InfoNewActivity extends AppCompatActivity {

    EditText editTitle;
    TextView editAuthor;
    EditText editContent;
    TextView testText;
    Button infoEditOk;
    Button infoEditCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_new);

        editTitle = (EditText) findViewById(R.id.info_edit_title);
        editContent = (EditText) findViewById(R.id.info_edit_content);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("뒤로 가시겠습니까?");
        ((AlertDialog.Builder) builder).setMessage("작성한 내용은 사라집니다.");
        ((AlertDialog.Builder) builder).setPositiveButton("뒤로가기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("남기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }

    void send(View v) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", editTitle.getText());
            jsonObject.put("content", editContent.getText());
            System.out.println(jsonObject);
            @SuppressLint("StaticFieldLeak")
            JsonConnection jsonConnection = new JsonConnection(Constant.INFO_NEW_URL) {
                @Override
                protected void onPostExecute(JSONObject jsonObject) {
                    try {
                        if (jsonObject.getString("message").equals("Success")){
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            jsonConnection.execute(jsonObject);

        } catch (IOException | JSONException e) {
            Toast.makeText(getApplicationContext(), "에러 발생", Toast.LENGTH_SHORT).show();
        }
    }
}

