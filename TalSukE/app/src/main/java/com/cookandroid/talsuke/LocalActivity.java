package com.cookandroid.talsuke;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.cookandroid.talsuke.Adapter.ExpandAdapter;
import com.cookandroid.talsuke.Adapter.ExpandYearAdapter;
import com.cookandroid.talsuke.Model.StatMonth;
import com.cookandroid.talsuke.Model.StatYear;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class LocalActivity extends AppCompatActivity {
    private ArrayList<StatYear> yearList;
    private ExpandableListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        this.setTitle("지역통계");

        listview = findViewById(R.id.local_board);

        try {
            JSONObject userInfo = new JSONObject();
            userInfo.put("username", getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", ""));
            @SuppressLint("StaticFieldLeak") JsonConnection jsonConnection = new JsonConnection(Constant.STAT_LOCAL_URL) {
                @Override
                protected void onPostExecute(JSONObject jsonObject) {
                    System.out.println(jsonObject);
                    if (jsonObject == null) return;
                    StatMonth month;
                    StatYear year;
                    yearList = new ArrayList<StatYear>();
                    try {
                        year = new StatYear(jsonObject.getString("num"));
                        JSONArray monthArray = jsonObject.getJSONArray("month");
                        for (int i=0; i < monthArray.length(); i++) {
                            year.addMonth( new StatMonth(
                                    monthArray.getJSONObject(i).getString("num") + "월",
                                    monthArray.getJSONObject(i).getString("weight") + "g",
                                    monthArray.getJSONObject(i).getString("fee") + "원"
                            ));
                        }
                        yearList.add(year);
                        ExpandYearAdapter adapter = new ExpandYearAdapter(getApplicationContext(),R.layout.stat_year_layout,R.layout.stat_month_layout, yearList);
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
