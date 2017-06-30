package com.itplus.demosqlite;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 24/04/2017.
 */

public class ContactAdapter extends BaseAdapter {

    private Activity mActivity;
    private List<Contact> listContact = new ArrayList<Contact>();


    public ContactAdapter(Activity activity, List<Contact> listCountry){
        mActivity = activity;
        listContact.addAll(listCountry);
    }

    public void clearAll(){
        if(listContact!=null && listContact.size()>0){
            listContact.clear();
        }
    }
    public void addObject(List<Contact> lst){
        if(listContact!=null){
            listContact.addAll(lst);
        }
    }

    @Override
    public int getCount() {
        return listContact.size();
    }

    @Override
    public Object getItem(int position) {
        return listContact.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(mActivity).inflate(R.layout.item_contact_layout, null);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);

        tvName.setText(listContact.get(position).getName());

        return convertView;
    }
}
