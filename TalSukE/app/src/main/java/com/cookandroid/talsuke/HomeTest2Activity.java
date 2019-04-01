package com.cookandroid.talsuke;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class HomeTest2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_test2);
        LineChart lineChart = (LineChart) findViewById(R.id.chart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(4f, 0));
        entries.add(new Entry(8f, 1));
        entries.add(new Entry(6f, 2));
        entries.add(new Entry(2f, 3));
        entries.add(new Entry(18f, 4));
        entries.add(new Entry(9f, 5));
        entries.add(new Entry(16f, 6));
        entries.add(new Entry(5f, 7));
        entries.add(new Entry(3f, 8));
        entries.add(new Entry(7f, 10));
        entries.add(new Entry(9f, 11));

        LineDataSet dataset = new LineDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        labels.add("August");
        labels.add("September");
        labels.add("October");
        labels.add("November");
        labels.add("December");

        LineData data = new LineData(dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        /*dataset.setDrawCubic(true); //선 둥글게 만들기
        dataset.setDrawFilled(true); //그래프 밑부분 색칠*/

        lineChart.setData(data);
        lineChart.animateY(5000);


        TextView text1 = (TextView) findViewById(R.id.textView4);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeTestActivity.class);
                startActivity(intent);
            }
        });
        TextView text2 = (TextView) findViewById(R.id.textView5);
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeTest2Activity.class);
                startActivity(intent);
            }
        });

        TextView text3 = (TextView)findViewById(R.id.textView6);
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeTest3Activity.class);
                startActivity(intent);
            }
        });

        TextView text4 = (TextView)findViewById(R.id.textView7);
        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeTest4Activity.class);
                startActivity(intent);
            }
        });
    }
}
