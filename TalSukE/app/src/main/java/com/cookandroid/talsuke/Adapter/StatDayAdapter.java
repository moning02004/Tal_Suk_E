package com.cookandroid.talsuke.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cookandroid.talsuke.Model.StatDay;
import com.cookandroid.talsuke.Model.StatMonth;
import com.cookandroid.talsuke.R;

import java.util.ArrayList;


public class StatDayAdapter extends BaseAdapter {
    private ArrayList<StatDay> dayList = new ArrayList<StatDay>();

    @Override
    public int getCount() {return dayList.size();}

    @Override
    public Object getItem(int position) {return dayList.get(position);}

    @Override
    public long getItemId(int position) { return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.stat_day_layout, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView dayDay = convertView.findViewById(R.id.stat_day_date) ;
        TextView dayWeight = convertView.findViewById(R.id.stat_day_weight) ;
        TextView dayFee = convertView.findViewById(R.id.stat_day_fee) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        StatDay statDay = this.dayList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        dayDay.setText(statDay.getDay());
        dayWeight.setText(statDay.getWeight());
        dayFee.setText(statDay.getFee());

        return convertView;
    }

    public void addItem(StatDay day) {
        dayList.add(day);
    }
}
