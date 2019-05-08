package com.cookandroid.talsuke.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cookandroid.talsuke.Model.InfoItem;
import com.cookandroid.talsuke.R;

import java.util.ArrayList;

public class InfoAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<InfoItem> data;
    private int layout;

    public InfoAdapter(Context context, int layout, ArrayList<InfoItem> data){
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(layout, viewGroup, false);
        }
        InfoItem infoItem = data.get(i);

        TextView title = (TextView) view.findViewById(R.id.info_title);
        TextView date = (TextView) view.findViewById(R.id.info_date);
        TextView content = (TextView) view.findViewById(R.id.info_content);

        title.setText(infoItem.getTitle());
        date.setText(infoItem.getCreated());
        content.setText(infoItem.getContent());

        return view;
    }
}
