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
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.china.oil.R;
import com.china.oil.entity.ExamInfo;
import java.util.ArrayList;
import java.util.List;

public class ExamAdapter extends BaseAdapter {

    private Context context;
    private List<ExamInfo> mList = new ArrayList<>();

    public ExamAdapter(Context mContext, List<ExamInfo> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_exam, null);

            mHolder = new ViewHolder();

            mHolder.exam_name_tv = convertView.findViewById(R.id.exam_name_tv);
            mHolder.exam_content = convertView.findViewById(R.id.exam_content);
            mHolder.exam_time = convertView.findViewById(R.id.exam_time);
            mHolder.exam_header_iv = convertView.findViewById(R.id.exam_header_iv);

            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        String url = mList.get(position).getHeaderUrl();

        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.adv)
                .error(R.mipmap.adv)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .bitmapTransform(new CircleCrop());

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(mHolder.exam_header_iv);

        mHolder.exam_name_tv.setText(mList.get(position).getTitle());
        mHolder.exam_content.setText(mList.get(position).getContent());
        mHolder.exam_time.setText(mList.get(position).getTime());

        return convertView;
    }

    public static class ViewHolder{
        TextView exam_name_tv;
        TextView exam_content;
        TextView exam_time;
        ImageView exam_header_iv;
    }
}
