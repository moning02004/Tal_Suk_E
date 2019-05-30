package com.cookandroid.talsuke.Infomation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.cookandroid.talsuke.Adapter.InfoAdapter;
import com.cookandroid.talsuke.Adapter.InfoUserAdapter;
import com.cookandroid.talsuke.Main.Constant;
import com.cookandroid.talsuke.Main.JsonConnection;
import com.cookandroid.talsuke.Model.InfoItem;
import com.cookandroid.talsuke.Model.InfoUserItem;
import com.cookandroid.talsuke.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class UserInfoActivity extends AppCompatActivity {
    private ArrayList<InfoUserItem> data;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        this.setTitle("회원 정보");

        listView = findViewById(R.id.info_user_board);

        try {
            JSONObject info = new JSONObject();
            info.put("username", getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", ""));

            @SuppressLint("StaticFieldLeak") JsonConnection JsonConnection = new JsonConnection(Constant.USER_ALL) {
                protected void onPostExecute(JSONObject jsonObject) {
                    System.out.println(jsonObject);
                    if (jsonObject == null) return;

                    data = new ArrayList<InfoUserItem>();
                    try {
                        JSONArray userInfoList = jsonObject.getJSONArray("user_info");
                        for (int i=0; i<userInfoList.length(); i++) {
                            data.add(new InfoUserItem(
                                    ((JSONObject) userInfoList.get(i)).getString("username"),
                                    ((JSONObject) userInfoList.get(i)).getString("name"),
                                    ((JSONObject) userInfoList.get(i)).getString("phone")
                            ));
                        }
                        InfoUserAdapter adapter = new InfoUserAdapter(getApplicationContext(), R.layout.activity_user_info_item, data);
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
                Intent intent = new Intent(getApplicationContext(), UserInfoClickedActivity.class);
                intent.putExtra("user_info_ID", data.get(i).getUserID());
                intent.putExtra("user_info_name", data.get(i).getUserName());
                intent.putExtra("user_info_phone", data.get(i).getUserPhone());
                startActivity(intent);
            }
        });
    }
}
