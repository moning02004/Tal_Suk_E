package com.cookandroid.talsuke.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.cookandroid.talsuke.Model.StatMonth;
import com.cookandroid.talsuke.R;

import java.util.ArrayList;

public class ExpandAdapter extends BaseExpandableListAdapter {
    private Context context;
    private int groupLayout = 0;
    private int chlidLayout = 0;
    private ArrayList<StatMonth> monthList;
    private LayoutInflater myinf = null;

    public ExpandAdapter(Context context,int groupLay,int chlidLay, ArrayList<StatMonth> monthList){
        this.monthList = monthList;
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

        TextView day = convertView.findViewById(R.id.stat_mon_date);
        TextView weight = convertView.findViewById(R.id.stat_mon_weight);
        TextView fee = convertView.findViewById(R.id.stat_mon_fee);
        day.setBackgroundColor(Color.GREEN);
        weight.setBackgroundColor(Color.GREEN);
        fee.setBackgroundColor(Color.GREEN);

        day.setText(monthList.get(groupPosition).getMonth());
        weight.setText(monthList.get(groupPosition).getWeight());
        fee.setText(monthList.get(groupPosition).getFee());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if(convertView == null){
            convertView = myinf.inflate(this.chlidLayout, parent, false);
        }
        TextView dayDay = (TextView)convertView.findViewById(R.id.stat_day_date);
        TextView dayWeight = (TextView)convertView.findViewById(R.id.stat_day_weight);
        TextView dayFee = (TextView)convertView.findViewById(R.id.stat_day_fee);

        dayDay.setText(monthList.get(groupPosition).getDayList().get(childPosition).getDay());
        dayWeight.setText(monthList.get(groupPosition).getDayList().get(childPosition).getWeight());
        dayFee.setText(monthList.get(groupPosition).getDayList().get(childPosition).getFee());
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
        return monthList.get(groupPosition).getDayList().get(childPosition).getDay();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        return monthList.get(groupPosition).getDayList().size();
    }

    @Override
    public StatMonth getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return monthList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return monthList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

}