package com.cookandroid.talsuke;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.cookandroid.talsuke.Adapter.ExpandAdapter;
import com.cookandroid.talsuke.Model.StatDay;
import com.cookandroid.talsuke.Model.StatMonth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MeActivity extends AppCompatActivity {
    ArrayList<StatMonth> monthList;
    ExpandableListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        this.setTitle("내집통계");

        listview = findViewById(R.id.me_month_board);
        this.monthList = new ArrayList<StatMonth>();
        try {
            JSONObject userInfo = new JSONObject();
            userInfo.put("username", getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", ""));

            @SuppressLint("StaticFieldLeak") JsonConnection jsonConnection = new JsonConnection(Constant.STAT_ME_URL) {
                @Override
                protected void onPostExecute(JSONObject jsonObject) {
                    System.out.println("AAAAAAAAAAAAFFFFFFFFFFFFFEEEEEEEEEEEEEWWWWWWWWWWWWWWWEEEEEEEEEEEEEEEEEFFFFFFFFFFFF" + jsonObject);
                    StatMonth month;
                    try {

                        JSONArray yearArray = jsonObject.getJSONArray("year");
                        for (int y = 0; y < yearArray.length(); y++) {
                            JSONObject year = (JSONObject) yearArray.get(y);
                            for (int m = 0; m < year.getJSONArray("month").length(); m++) {
                                JSONObject monthArray = (JSONObject) year.getJSONArray("month").get(m);
                                month = new StatMonth(
                                        monthArray.getString("num") + "월",
                                        monthArray.getString("weight") + "g",
                                        monthArray.getString("fee") + "원"
                                );

                                for (int d = 0; d < monthArray.getJSONArray("day").length(); d++) {
                                    JSONObject dayArray = (JSONObject) monthArray.getJSONArray("day").get(d);
                                    month.addDay(
                                            new StatDay(
                                                    dayArray.getString("num") + "일",
                                                    dayArray.getString("weight") + "g",
                                                    dayArray.getString("fee") + "원"
                                            )
                                    );
                                }
                                monthList.add(month);
                            }
                        }

                        ExpandAdapter adapter = new ExpandAdapter(getApplicationContext(),R.layout.stat_month_layout,R.layout.stat_day_layout,monthList);
                        listview.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            jsonConnection.execute(userInfo);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


    }
}
