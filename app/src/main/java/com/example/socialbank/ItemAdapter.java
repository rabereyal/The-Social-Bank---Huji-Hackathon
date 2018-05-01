package com.example.socialbank;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

public class ItemAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> descriptions = new ArrayList<String>();

    public ItemAdapter(Context c, ArrayList<String> n, ArrayList<String> d) {
        names = n;
        descriptions = d;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.item_in_list, null);
        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);
        TextView descriptionTextView = (TextView) v.findViewById(R.id.descriptionTextView);

        String name = names.get(position);
        String desc = descriptions.get(position);

        nameTextView.setText(name);
        descriptionTextView.setText(desc);

        return v;
    }
}