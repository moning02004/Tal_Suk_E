package com.cookandroid.talsuke.Statistaics;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

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
import java.util.HashMap;
import java.util.Map;

public class MeActivity extends AppCompatActivity {
    private ExpandableListView listview;
    private Spinner yearSpinner;
    private Map<String, ArrayList<StatMonth>> yearList;

    private String currentYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        this.setTitle("내집 통계");

        listview = findViewById(R.id.me_month_board);
        yearSpinner = findViewById(R.id.me_year_list);
        this.yearList = new HashMap<String, ArrayList<StatMonth>>();

        try {
            JSONObject userInfo = new JSONObject();
            userInfo.put("username", getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", ""));

            @SuppressLint("StaticFieldLeak") JsonConnection jsonConnection = new JsonConnection(Constant.STAT_ME_URL) {
                @Override
                protected void onPostExecute(JSONObject jsonObject) {
                    if (jsonObject == null) return;
                    try {
                        JSONArray yearArray = jsonObject.getJSONArray("year");
                        for (int y = 0; y < yearArray.length(); y++) {
                            ArrayList<StatMonth> monthList = new ArrayList<>();
                            JSONObject yearValue = (JSONObject) yearArray.get(y);
                            for (int m = 0; m < yearValue.getJSONArray("month").length(); m++) {

                                JSONObject monthValue = (JSONObject) yearValue.getJSONArray("month").get(m);
                                StatMonth month = new StatMonth(
                                        monthValue.getString("num") + "월",
                                        monthValue.getString("weight") + "g",
                                        monthValue.getString("fee") + "원"
                                );

                                for (int d = 0; d < monthValue.getJSONArray("day").length(); d++) {
                                    JSONObject dayValue = (JSONObject) monthValue.getJSONArray("day").get(d);
                                    month.addDay(
                                            new StatDay(
                                                    dayValue.getString("num") + "일",
                                                    dayValue.getString("weight") + "g",
                                                    dayValue.getString("fee") + "원"
                                            )
                                    );
                                }
                                monthList.add(month);
                            }
                            yearList.put(yearValue.getString("num"), monthList);
                        }
                        ArrayAdapter spinnerAdapter = new ArrayAdapter(getApplicationContext(), R.layout.year_list_layout, new ArrayList<>(yearList.keySet()));
                        yearSpinner.setAdapter(spinnerAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            jsonConnection.execute(userInfo);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentYear = (String) yearSpinner.getItemAtPosition(position);
                MonthAdapter adapter = new MonthAdapter(getApplicationContext(),R.layout.stat_month_layout,R.layout.stat_day_layout,yearList.get(currentYear));
                listview.setAdapter(adapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    void find(View v){
    }
}
