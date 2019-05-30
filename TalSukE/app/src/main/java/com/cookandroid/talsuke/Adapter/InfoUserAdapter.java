package com.cookandroid.talsuke.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cookandroid.talsuke.Model.InfoItem;
import com.cookandroid.talsuke.Model.InfoUserItem;
import com.cookandroid.talsuke.R;

import java.util.ArrayList;

public class InfoUserAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<InfoUserItem> data;
    private int layout;

    public InfoUserAdapter(Context context, int layout, ArrayList<InfoUserItem> data){
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
        InfoUserItem infoUserItem = data.get(i);

        TextView userInfoID = (TextView) view.findViewById(R.id.user_info_id);
        TextView userInfoName = (TextView) view.findViewById(R.id.user_info_name);
        TextView userInfoPhone = (TextView) view.findViewById(R.id.user_phone);

        userInfoID.setText(infoUserItem.getUserID());
        userInfoName.setText(infoUserItem.getUserName());
        userInfoPhone.setText(infoUserItem.getUserPhone());

        return view;
    }
}
