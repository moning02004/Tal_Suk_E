package com.cookandroid.talsuke.Infomation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.cookandroid.talsuke.Adapter.InfoAdapter;
import com.cookandroid.talsuke.Main.Constant;
import com.cookandroid.talsuke.Main.JsonConnection;
import com.cookandroid.talsuke.Model.InfoItem;
import com.cookandroid.talsuke.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class InfoActivity extends AppCompatActivity {
    private ArrayList<InfoItem> data;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        this.setTitle("공지사항");

        String permission = getSharedPreferences("SESSION", MODE_PRIVATE).getString("permission", "");
        if (permission!= null && permission.equals("user")){
            Button new_btn = findViewById(R.id.info_new_btn);
            new_btn.setVisibility(View.GONE);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA);
        Date currentTime = new Date();
        final String dTime = currentTime.toString();
        data = new ArrayList<>();

        listView = findViewById(R.id.info_board);

        try {
            JSONObject info = new JSONObject();
            info.put("username", getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", ""));

            @SuppressLint("StaticFieldLeak") JsonConnection JsonConnection = new JsonConnection(Constant.INFO_INDEX_URL) {
                protected void onPostExecute(JSONObject jsonObject) {
                    System.out.println(jsonObject);
                    if (jsonObject == null) return;

                    data = new ArrayList<InfoItem>();
                    try {
                        JSONArray infoList = jsonObject.getJSONArray("info");
                        for (int i=0; i<infoList.length(); i++) {
                            data.add(new InfoItem(
                                    ((JSONObject) infoList.get(i)).getString("title"),
                                    ((JSONObject) infoList.get(i)).getString("created"),
                                    ((JSONObject) infoList.get(i)).getString("content")
                            ));
                        }
                        InfoAdapter adapter = new InfoAdapter(getApplicationContext(), R.layout.activity_info_item, data);
                        listView.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            JsonConnection.execute(info);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View v, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), InfoDetailActivity.class);
                intent.putExtra("info_title", data.get(i).getTitle());
                intent.putExtra("info_date", data.get(i).getCreated());
                intent.putExtra("info_content", data.get(i).getContent());
                startActivity(intent);
            }
        });
    }
    void new_btn(View v) {
        Intent intent = new Intent(getApplicationContext(), InfoNewActivity.class);
        startActivityForResult(intent, 1234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1234){
            this.recreate();
        }
    }
}
