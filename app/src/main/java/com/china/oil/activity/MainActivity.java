package com.china.oil.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.china.oil.R;
import com.china.oil.fragment.ApprovalFragment;
import com.china.oil.fragment.ExamFragment;
import com.china.oil.fragment.HomeFragment;
import com.china.oil.fragment.MineFragment;
import com.china.oil.fragment.SafeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;

    // 定义FragmentManager对象管理器
    private FragmentManager fragmentManager;

    @BindView(R.id.safe_iv)
    ImageView safe_iv;
    @BindView(R.id.exam_iv)
    ImageView exam_iv;
    @BindView(R.id.mine_iv)
    ImageView mine_iv;
    @BindView(R.id.home_iv)
    ImageView home_iv;
    @BindView(R.id.approval_iv)
    ImageView approval_iv;

    @BindView(R.id.safe_tv)
    TextView safe_tv;
    @BindView(R.id.exam_tv)
    TextView exam_tv;
    @BindView(R.id.home_tv)
    TextView home_tv;
    @BindView(R.id.approval_tv)
    TextView approval_tv;
    @BindView(R.id.mine_tv)
    TextView mine_tv;

    private SafeFragment safeFragment;
    private ExamFragment examFragment;
    private ApprovalFragment approvalFragment;
    private HomeFragment homeFragment;
    private MineFragment mineFragment;

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();

        setChioceItem(0);   // 初始化页面加载时显示第一个选项卡
    }

    @OnClick({R.id.safe_layout,
            R.id.exam_layout,
            R.id.home_layout,
            R.id.approval_layout,
            R.id.mine_layout})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.safe_layout:
                setChioceItem(1);
                break;
            case R.id.exam_layout:
                setChioceItem(2);
                break;
            case R.id.home_layout:
                setChioceItem(0);
                break;
            case R.id.approval_layout:
                setChioceItem(3);
                break;
            case R.id.mine_layout:
                setChioceItem(4);
                break;
        }
    }

    private void setChioceItem(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        clearChoice(); // 清空, 重置选项, 隐藏所有Fragment
        hideFragments(fragmentTransaction);

        switch (index) {
            case 0:
                home_iv.setBackgroundResource(R.mipmap.home_press);
                home_tv.setTextColor(getResources().getColor(R.color.orange_text));

                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.fragment_content, homeFragment);
                } else {
                    fragmentTransaction.show(homeFragment);
                }

                break;

            case 1:
                safe_iv.setBackgroundResource(R.mipmap.safe_press);
                safe_tv.setTextColor(getResources().getColor(R.color.orange_text));

                // 如果fg1为空，则创建一个并添加到界面上
                if (safeFragment == null) {
                    safeFragment = new SafeFragment();
                    fragmentTransaction.add(R.id.fragment_content, safeFragment);
                } else {
                    // 如果不为空，则直接将它显示出来
                    fragmentTransaction.show(safeFragment);
                }
                break;

            case 2:
                exam_iv.setBackgroundResource(R.mipmap.exam_press);
                exam_tv.setTextColor(getResources().getColor(R.color.orange_text));

                // 如果fg1为空，则创建一个并添加到界面上
                if (examFragment == null) {
                    examFragment = new ExamFragment();
                    fragmentTransaction.add(R.id.fragment_content, examFragment);
                } else {
                    // 如果不为空，则直接将它显示出来
                    fragmentTransaction.show(examFragment);
                }
                break;

            case 3:
                approval_iv.setBackgroundResource(R.mipmap.approval_press);
                approval_tv.setTextColor(getResources().getColor(R.color.orange_text));

                if (approvalFragment == null) {
                    approvalFragment = new ApprovalFragment();
                    fragmentTransaction.add(R.id.fragment_content, approvalFragment);
                } else {
                    fragmentTransaction.show(approvalFragment);
                }
                break;
            case 4:
                mine_iv.setBackgroundResource(R.mipmap.mine_press);
                mine_tv.setTextColor(getResources().getColor(R.color.orange_text));

                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.fragment_content, mineFragment);
                } else {
                    fragmentTransaction.show(mineFragment);
                }
                break;
        }
        fragmentTransaction.commit();   // 提交
    }

    /**
     * 当选中其中一个选项卡时，其他选项卡重置为默认
     */
    private void clearChoice() {
        safe_iv.setBackgroundResource(R.mipmap.safe);
        safe_tv.setTextColor(getResources().getColor(R.color.gray_text));

        exam_iv.setBackgroundResource(R.mipmap.exam);
        exam_tv.setTextColor(getResources().getColor(R.color.gray_text));

        home_iv.setBackgroundResource(R.mipmap.home);
        home_tv.setTextColor(getResources().getColor(R.color.gray_text));

        approval_iv.setBackgroundResource(R.mipmap.approval);
        approval_tv.setTextColor(getResources().getColor(R.color.gray_text));

        mine_iv.setBackgroundResource(R.mipmap.mine);
        mine_tv.setTextColor(getResources().getColor(R.color.gray_text));
    }

    /**
     * 隐藏Fragment
     *
     * @param fragmentTransaction
     */
    private void hideFragments(FragmentTransaction fragmentTransaction) {

        if (safeFragment != null) {
            fragmentTransaction.hide(safeFragment);
        }

        if (examFragment != null) {
            fragmentTransaction.hide(examFragment);
        }

        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }

        if (approvalFragment != null) {
            fragmentTransaction.hide(approvalFragment);
        }

        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(home_iv != null){
            home_iv = null;
        }
        if(safe_iv != null){
            safe_iv = null;
        }
        if(mine_iv != null){
            mine_iv = null;
        }
        if(approval_iv != null){
            approval_iv = null;
        }
        if(exam_iv != null){
            exam_iv = null;
        }

        if(home_tv != null){
            home_tv = null;
        }
        if(safe_tv != null){
            safe_tv = null;
        }
        if(mine_tv != null){
            mine_tv = null;
        }
        if(approval_tv != null){
            approval_tv = null;
        }
        if(exam_tv != null){
            exam_tv = null;
        }


        if(homeFragment != null){
            homeFragment = null;
        }
        if(examFragment != null){
            examFragment = null;
        }
        if(safeFragment != null){
            safeFragment = null;
        }
        if(approvalFragment != null){
            approvalFragment = null;
        }

        if(mineFragment != null){
            mineFragment = null;
        }
    }

}
