package com.cookandroid.talsuke;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cookandroid.talsuke.Adapter.InfoAdapter;
import com.cookandroid.talsuke.Model.InfoItem;
import com.cookandroid.talsuke.Model.StatMonth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class InfoActivity extends AppCompatActivity {

    ArrayList<InfoItem> data;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        this.setTitle("정보 게시판");

        listView = (ListView) findViewById(R.id.info_board);


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA);
        Date currentTime = new Date();
        final String dTime = currentTime.toString();
        data = new ArrayList<InfoItem>();

        InfoItem item1 = new InfoItem("[공지사항] ", " 김준희", dTime, "여기에 내용");
        InfoItem item2 = new InfoItem("[공지사항] 날", " 기무준희", dTime, "여기에 내용조립다");

        data.add(item1);
        data.add(item2);


        try {
            JSONObject info = new JSONObject();
            info.put("username", getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", ""));
            System.out.println("try들어감");
            @SuppressLint("StaticFieldLeak") JsonConnection JsonConnection = new JsonConnection(Constant.STAT_ME_URL) {
                protected void onPostExecute(JSONObject jsonObject) {
                    System.out.println("protecte들어감");
                    System.out.println(jsonObject);
                    if (jsonObject == null) return;
                    InfoItem info;
                    data = new ArrayList<InfoItem>();
                    try {
                        JSONArray yearArray = jsonObject.getJSONArray("year");
                        for (int y = 0; y < yearArray.length(); y++) {
                            JSONObject year = (JSONObject) yearArray.get(y);
                            for (int m = 0; m < year.getJSONArray("month").length(); m++) {
                                JSONObject monthArray = (JSONObject) year.getJSONArray("month").get(m);
                                info = new InfoItem(
                                        monthArray.getString("num"),
                                        monthArray.getString("weight"),
                                        dTime,
                                        monthArray.getString("fee")
                                );
                                data.add(info);
                            }
                        }
                        InfoAdapter adapter = new InfoAdapter(getApplicationContext(), R.layout.activity_info_item, data);
                        listView.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            JsonConnection.execute(info);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View v, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), InfoClickedActivity.class);
                intent.putExtra("info_title", data.get(i).getTitle());
                intent.putExtra("info_author", data.get(i).getAuthor());
                intent.putExtra("info_date", data.get(i).getDate());
                intent.putExtra("info_content", data.get(i).getContent());
                startActivity(intent);
            }
        });
        /*
        try {
            JSONObject info = new JSONObject();
            @SuppressLint("StaticFieldLeak") JsonConnection jsonConnection = new JsonConnection(Constant.INFO_URL) {
                protected void onPostExecute(JSONObject jsonObject) {
                    if (jsonObject == null) {
                        return;
                    }
                    InfoItem data;
                    try {
                        JSONArray infoArray = jsonObject.getJSONArray("info");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            };
            jsonConnection.execute(info);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
*/
        //아이템 설정 -> JSON으로 변경


    }


    void info_btn(View v) {
        Intent intent = new Intent(getApplicationContext(), InfoEditActivity.class);
        startActivityForResult(intent, 1000);
    }
}

