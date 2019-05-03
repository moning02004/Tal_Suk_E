package com.cookandroid.talsuke.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cookandroid.talsuke.Model.StatYear;
import com.cookandroid.talsuke.R;

import java.util.ArrayList;


public class StatYearAdapter extends BaseAdapter {
    private ArrayList<StatYear> yearList = new ArrayList<StatYear>();

    @Override
    public int getCount() {return yearList.size();}

    @Override
    public Object getItem(int position) {return yearList.get(position);}

    @Override
    public long getItemId(int position) { return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.stat_year_layout, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView monDate = convertView.findViewById(R.id.stat_mon_date) ;
        TextView monWeight = convertView.findViewById(R.id.stat_mon_weight) ;
        TextView monFee = convertView.findViewById(R.id.stat_mon_fee) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        StatYear statYear = this.yearList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        monDate.setText(statYear.getYear());

        return convertView;
    }

    public void addItem(StatYear sYear) {
        yearList.add(sYear);
    }
}
