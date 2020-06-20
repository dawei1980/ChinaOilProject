package com.china.oil.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.china.oil.R;
import com.china.oil.activity.LoginActivity;
import com.china.oil.adapter.ExamAdapter;
import com.china.oil.adapter.SafeAdapter;
import com.china.oil.entity.ExamInfo;
import com.china.oil.entity.SafeVideo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExamFragment extends Fragment {
    private View view;

    @BindView(R.id.exam_lv)
    ListView exam_lv;
    @BindView(R.id.exam_input_et)
    EditText exam_input_et;

    List<ExamInfo> mList = new ArrayList<>();
    private ExamAdapter examAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_exam, null);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ButterKnife.bind(this, view);

        initView();
        return view;
    }

    private void initView(){

        String examInputText = exam_input_et.getText().toString().trim();

        mList.add(new ExamInfo("网络科技城市","网络科技城市","http://seopic.699pic.com/photo/50073/1554.jpg_wh1200.jpg","1天前"));
        mList.add(new ExamInfo("5G网络城市科技","5G网络城市科技","http://seopic.699pic.com/photo/50051/4834.jpg_wh1200.jpg","1天前"));
        mList.add(new ExamInfo("女性闺蜜网络购物","女性闺蜜网络购物","http://seopic.699pic.com/photo/50051/1164.jpg_wh1200.jpg","1天前"));
        mList.add(new ExamInfo("电脑芯片与人工智能","电脑芯片与人工智能","http://seopic.699pic.com/photo/50053/5578.jpg_wh1200.jpg","1天前"));

        examAdapter = new ExamAdapter(getActivity(),mList);
        exam_lv.setAdapter(examAdapter);
    }


    @OnClick({R.id.send_comment_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_comment_btn:
                Toast.makeText(getActivity(),"发送成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
