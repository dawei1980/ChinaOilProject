package com.china.oil.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.china.oil.entity.SafeVideo;

import java.util.ArrayList;
import java.util.List;

public class SafeAdapter extends BaseAdapter {

    private Context context;
    private List<SafeVideo> mList = new ArrayList<>();

    public SafeAdapter(Context mContext, ArrayList<SafeVideo> list){
        this.context = mContext;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public static class ViewHolder{

    }
}
