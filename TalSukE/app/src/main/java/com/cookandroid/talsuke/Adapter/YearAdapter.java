package com.cookandroid.talsuke.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.cookandroid.talsuke.Model.StatMonth;
import com.cookandroid.talsuke.Model.StatYear;
import com.cookandroid.talsuke.R;

import java.util.ArrayList;

public class YearAdapter extends BaseExpandableListAdapter {
    private Context context;
    private int groupLayout = 0;
    private int chlidLayout = 0;
    private ArrayList<StatYear> yearList;
    private LayoutInflater myinf = null;

    public YearAdapter(Context context, int groupLay, int chlidLay, ArrayList<StatYear> yearList){
        this.yearList = yearList;
        this.groupLayout = groupLay;
        this.chlidLayout = chlidLay;
        this.context = context;
        this.myinf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if(convertView == null){
            convertView = myinf.inflate(this.groupLayout, parent, false);
        }

        TextView year = convertView.findViewById(R.id.stat_year);
        year.setBackgroundColor(Color.GREEN);
        year.setText(yearList.get(groupPosition).getYear());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if(convertView == null){
            convertView = myinf.inflate(this.chlidLayout, parent, false);
        }
        TextView month = (TextView)convertView.findViewById(R.id.stat_mon_date);
        TextView weight = (TextView)convertView.findViewById(R.id.stat_mon_weight);
        TextView fee = (TextView)convertView.findViewById(R.id.stat_mon_fee);

        month.setText(yearList.get(groupPosition).getMonthList().get(childPosition).getMonth());
        weight.setText(yearList.get(groupPosition).getMonthList().get(childPosition).getWeight());
        fee.setText(yearList.get(groupPosition).getMonthList().get(childPosition).getFee());
        return convertView;
    }
    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return yearList.get(groupPosition).getMonthList().get(childPosition).getMonth();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        return yearList.get(groupPosition).getMonthList().size();
    }

    @Override
    public StatYear getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return yearList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return yearList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

}
