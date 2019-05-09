package com.cookandroid.talsuke;

import android.annotation.SuppressLint;
import static com.cookandroid.talsuke.Model.WeightFee.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cookandroid.talsuke.Model.WeightFee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private TextView connect;
    int totalWeight;
    int Weight;
    ImageView imot;
    TextView fee;
    WeightFee WF;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("탈숙이");
        imot = (ImageView)findViewById(R.id.home_imot);
        fee = (TextView)findViewById(R.id.home_fee);

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
                                    Weight = Integer.parseInt(weight);
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
        else if(totalWeight==48000)imot.setImageResource(R.drawable.status5);

    }

    void getWeight() {

    }

    @Override
    public void onBackPressed() {
        System.out.println(getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", ""));
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
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

    void update(View v) {

        fee.setText(" 무게 : " + Weight +  "g    요금 : " + WF.getWeightFee());
    }

    void dehydration(View v) {
        changeImot();
    }

    void me(View v) {
        Intent intent = new Intent(getApplicationContext(), MeActivity.class);
        startActivity(intent);
    }

    void local(View v) {
        Intent intent = new Intent(getApplicationContext(), LocalActivity.class);
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
}
