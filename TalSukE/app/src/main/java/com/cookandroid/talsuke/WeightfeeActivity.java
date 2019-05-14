package com.cookandroid.talsuke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WeightfeeActivity extends AppCompatActivity {
    EditText WFText;
    Button WFOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightfee);


        WFText = (EditText)findViewById(R.id.WFText);
        WFOK = (Button)findViewById(R.id.WFOK);

        WFOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void onBackPressed() {
        return;
    }

}
