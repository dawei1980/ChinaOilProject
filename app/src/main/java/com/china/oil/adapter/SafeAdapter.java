package com.china.oil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.china.oil.R;
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
        ViewHolder mHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_safe, null);

            mHolder = new ViewHolder();

            mHolder.safe_tv = convertView.findViewById(R.id.safe_tv);

            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }

    public static class ViewHolder{
        TextView safe_tv;
    }
}
