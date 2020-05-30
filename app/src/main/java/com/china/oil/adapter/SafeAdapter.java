package com.china.oil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.china.oil.R;
import com.china.oil.entity.SafeVideo;

import java.util.ArrayList;
import java.util.List;

public class SafeAdapter extends BaseAdapter {

    private Context context;
    private List<SafeVideo> mList = new ArrayList<>();

    public SafeAdapter(Context mContext, List<SafeVideo> list){
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
            mHolder.safe_iv = convertView.findViewById(R.id.safe_iv);

            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        String url = mList.get(position).getImgUrl();

        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.adv)
                .error(R.mipmap.adv)
                .diskCacheStrategy(DiskCacheStrategy.NONE);;

//        Glide.with(context)
//                .load(url)
//                .apply(options)
//                .into(mHolder.safe_iv);

        Glide.with(context)
                .load("http://guolin.tech/book.png")
                .apply(options)
                .into(mHolder.safe_iv);

        return convertView;
    }

    public static class ViewHolder{
        TextView safe_tv;
        ImageView safe_iv;
    }
}
