package com.cookandroid.talsuke.Main;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cookandroid.talsuke.Infomation.*;
import com.cookandroid.talsuke.R;
import com.cookandroid.talsuke.Settings.SettingActivity;
import com.cookandroid.talsuke.Statistaics.MeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class UserHomeActivity extends AppCompatActivity {
    int totalWeight;
    int currentWeight;
    double current_Fee;
    ImageView imot;
    TextView fee;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("탈숙이");
        imot = (ImageView)findViewById(R.id.home_imot);
        fee = (TextView)findViewById(R.id.home_fee);

        totalWeight = 0;

        try{
            JSONObject info = new JSONObject();
            info.put("username", getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", ""));
            @SuppressLint("StaticFieldLeak") JsonConnection JsonConnection = new JsonConnection(Constant.STAT_ME_URL) {
                protected void onPostExecute(JSONObject jsonObject){
                    System.out.println(jsonObject);
                    if(jsonObject == null) return;
                    try{
                        JSONArray yearArray = jsonObject.getJSONArray("year");
                        for(int y=0; y<yearArray.length(); y++){
                            JSONObject year = (JSONObject)yearArray.get(y);
                            for(int m=0; m<year.getJSONArray("month").length(); m++){
                                JSONObject monthArray = (JSONObject)year.getJSONArray("month").get(m);
                                totalWeight = Integer.parseInt(monthArray.getString("weight"));
                                for(int d=0; d<monthArray.getJSONArray("day").length(); d++){
                                    JSONObject dayArray = (JSONObject)monthArray.getJSONArray("day").get(d);
                                    String weight = dayArray.getString("weight");
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            JsonConnection.execute(info);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getWeight();
        changeImot();

    }

    void changeImot() {
        if(totalWeight==0) imot.setImageResource(R.drawable.status0);
        else if(totalWeight>0 && totalWeight<=12000) imot.setImageResource(R.drawable.status1);
        else if(totalWeight>12000 && totalWeight<=24000) imot.setImageResource(R.drawable.status2);
        else if(totalWeight>24000 && totalWeight<=36000) imot.setImageResource(R.drawable.status3);
        else if(totalWeight>36000 && totalWeight<=48000) imot.setImageResource(R.drawable.status4);
        else imot.setImageResource(R.drawable.status5);

    }

    void getWeight() {
        update(getCurrentFocus());
    }

    void update(View v) {
        changeImot();
        try {
            @SuppressLint("StaticFieldLeak") JsonGetConnection jsonConnection = new JsonGetConnection(Constant.ARDUINO_URL){
                @Override
                protected void onPostExecute(JSONObject jsonObject) {
                    try {
                        JSONObject temp = (JSONObject) jsonObject.getJSONArray("feeds").get(0);
                        currentWeight = Integer.parseInt(temp.getString("field1"));
                        int tempFee = Integer.parseInt(getSharedPreferences("SESSION", MODE_PRIVATE).getString("fee", "0"));
                        current_Fee = (Integer.parseInt(temp.getString("field1")) * tempFee * 0.001);
                        fee.setText("무게 : " + temp.getString("field1") + "g  요금 : " + current_Fee +"원" );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            jsonConnection.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void discharge(View v) {
        try {
            JSONObject dayWeight = new JSONObject();
            dayWeight.put("username", getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", ""));
            dayWeight.put("weight", currentWeight);
            dayWeight.put("fee", current_Fee);

            @SuppressLint("StaticFieldLeak") JsonConnection jsonConnection = new JsonConnection(Constant.STATISTICS_ADD){
                @Override
                protected void onPostExecute(JSONObject jsonObject) {
                    Toast.makeText(getApplicationContext(), "성공적으로 저장되었습니다.", Toast.LENGTH_LONG).show();
                }
            };
            jsonConnection.execute(dayWeight);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    void me(View v) {
        Intent intent = new Intent(getApplicationContext(), MeActivity.class);
        startActivity(intent);
    }

    void info(View v) {
        Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
        startActivity(intent);
    }

    void setting(View v) {
        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
        startActivityForResult(intent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_CANCELED) {
            switch (requestCode){
                case 1000:
                    String username = getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", "");
                    if (username != null && username.equals("")) {
                        Toast.makeText(getApplicationContext(), "로그아웃 되었습니다.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    break;
            }
        }
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UserHomeActivity.this);
        builder.setCancelable(true);
        builder.setTitle("종료 확인");
        builder.setMessage("종료하시겠어요?");
        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
