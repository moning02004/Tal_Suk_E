package com.cookandroid.talsuke.Statistaics;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.cookandroid.talsuke.Adapter.MonthAdapter;
import com.cookandroid.talsuke.Main.Constant;
import com.cookandroid.talsuke.Main.JsonConnection;
import com.cookandroid.talsuke.Model.StatDay;
import com.cookandroid.talsuke.Model.StatMonth;
import com.cookandroid.talsuke.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MeActivity extends AppCompatActivity {
    private ArrayList<StatMonth> monthList;
    private ExpandableListView listview;
    private Spinner year_list;
    private ArrayList<String> yearList;

    private String currentYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        this.setTitle("내집통계");

        listview = findViewById(R.id.me_month_board);
        year_list = findViewById(R.id.me_year_list);
        this.monthList = new ArrayList<>();
        this.yearList = new ArrayList<>();

        try {
            JSONObject userInfo = new JSONObject();
            userInfo.put("username", getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", ""));

            @SuppressLint("StaticFieldLeak") JsonConnection jsonConnection = new JsonConnection(Constant.STAT_ME_URL) {
                @Override
                protected void onPostExecute(JSONObject jsonObject) {
                    if (jsonObject == null) return;
                    StatMonth month;
                    try {
                        JSONArray yearArray = jsonObject.getJSONArray("year");
                        for (int y = 0; y < yearArray.length(); y++) {
                            JSONObject year = (JSONObject) yearArray.get(y);
                            yearList.add(year.getString("num"));
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

                        ArrayAdapter spinnerAdapter = new ArrayAdapter(getApplicationContext(), R.layout.year_list_layout, yearList);
                        MonthAdapter adapter = new MonthAdapter(getApplicationContext(),R.layout.stat_month_layout,R.layout.stat_day_layout,monthList);
                        listview.setAdapter(adapter);
                        year_list.setAdapter(spinnerAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            jsonConnection.execute(userInfo);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        year_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentYear = (String) year_list.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    void find(View v){
        System.out.println(yearList);
    }
}
