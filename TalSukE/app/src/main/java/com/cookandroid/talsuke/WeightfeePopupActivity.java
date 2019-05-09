package com.cookandroid.talsuke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.cookandroid.talsuke.Model.WeightFee;

public class WeightfeePopupActivity extends AppCompatActivity {
    EditText WFText;
    WeightFee WF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightfee_popup);
        WF.setWeightFee(5);
        WFText = (EditText)findViewById(R.id.WFText);
        WFText.setText(WF.getWeightFee());
    }

    void WFPopupClose(){
        finish();
    }
}
