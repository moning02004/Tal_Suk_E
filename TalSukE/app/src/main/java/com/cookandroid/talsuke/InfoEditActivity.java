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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InfoEditActivity extends AppCompatActivity {

    EditText editTitle;
    EditText editAuthor;
    EditText editContent;
    TextView testText;
    Button infoEditOk;
    Button infoEditCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_edit);

        editTitle = (EditText) findViewById(R.id.info_edit_title);
        editAuthor = (EditText) findViewById(R.id.info_edit_author);
        editContent = (EditText) findViewById(R.id.info_edit_content);
        infoEditOk = (Button) findViewById(R.id.info_edit_ok);
        infoEditCancel = (Button) findViewById(R.id.info_edit_cancel);
        testText = (TextView) findViewById(R.id.info_test);

        infoEditOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendArray();
            }
        });

        infoEditCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        show();
    }

    void show() {
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

    private void sendArray() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", editTitle.getText());
            jsonObject.put("content", editContent.getText());

            @SuppressLint("StaticFieldLeak")
            JsonConnection jsonConnection = new JsonConnection(Constant.INFO_NEW_URL) {
                @Override
                protected void onPostExecute(JSONObject jsonObject) {
                    System.out.println(jsonObject);
                }
            };
            jsonConnection.execute(jsonObject);

        } catch (IOException | JSONException e) {
            Toast.makeText(getApplicationContext(), "에러 발생", Toast.LENGTH_SHORT).show();
        }
    }
}

