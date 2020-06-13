package com.china.oil.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;

import com.china.oil.R;
import com.china.oil.uils.ScreenUtil;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    private View view;
    @BindView(R.id.mLineChar)
    LineChart mLineChart;

    @BindView(R.id.mTargetPieChart_1)
    PieChart mTarPieChart1;

    @BindView(R.id.mHorizontalBarChart)
    HorizontalBarChart mHorizontalBarChart;
    private final String lable[]={"手机","电视机","笔记本电脑","台式电脑", "电冰箱","空调","洗衣机","油烟机","空气净化器","加湿器"};
    private float spaceForBar = 10f;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_home, null);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ButterKnife.bind(this, view);

        initLineChart();
        //设置数据
        setData(12, 1000);

        initTarPieChart();

        initHorizontalBarChart();
        return view;
    }

    /**
     * 折线图
     * */
    private void initLineChart(){
        // 线形图初始化
        ViewGroup.LayoutParams lp = mLineChart.getLayoutParams();
        lp.width = ScreenUtil.getWidth(getActivity());
        lp.height = ScreenUtil.getWidth(getActivity());
        mLineChart.setLayoutParams(lp);

        // 没有描述的文本
        mLineChart.getDescription().setEnabled(false);
        // 支持触控手势
        mLineChart.setTouchEnabled(true);
        mLineChart.setDragDecelerationFrictionCoef(0.9f);
        // 支持缩放和拖动
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);
        mLineChart.setDrawGridBackground(false);
        mLineChart.setHighlightPerDragEnabled(true);
        // 如果禁用,扩展可以在x轴和y轴分别完成
        mLineChart.setPinchZoom(true);
        // 设置背景颜色(白色)
        mLineChart.setBackgroundColor(Color.WHITE);

        //默认x动画
        mLineChart.animateX(2500);
//        mLineChart.setNoDataText("没有数据呢(⊙o⊙)");   //没有数据时显示在中央的字符串，参数是String对象
        mLineChart.setHighlightPerDragEnabled(false);

        //获得数据
        Legend l = mLineChart.getLegend();

        //修改
        l.setForm(Legend.LegendForm.LINE);
        l.setTextSize(11f);
        l.setTextColor(0xff43484b);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        //x轴
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(12f);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        //设置x轴显示的标签个数
        xAxis.setLabelCount(12);

        //左边y轴
        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMaximum(5000);
        leftAxis.setAxisMinimum(-3000);
        leftAxis.setDrawGridLines(false);
        leftAxis.setGranularityEnabled(false);

        mLineChart.getAxisRight().setEnabled(false);
    }

    /**
     * 环状图
     * */
    private void initTarPieChart(){
        mTarPieChart1.setTouchEnabled(false);
        mTarPieChart1.setDrawHoleEnabled(true); //显示中间的洞
        mTarPieChart1.setHoleColor(0xffffffff); //洞的颜色
        mTarPieChart1.setTransparentCircleColor(R.color.text_green);
        mTarPieChart1.setDrawSliceText(false);//不显示切片里面的字体，就是pie 块里面的字体
        mTarPieChart1.setDescription(null); //不显示描述
        mTarPieChart1.setHoleRadius(70f); //洞的大小
        mTarPieChart1.setTransparentCircleRadius(20f); //效果的大小
        mTarPieChart1.setDrawCenterText(false);//中心的文字也不要写了
        mTarPieChart1.setRotationAngle(108); //显示的角度 90+ X% * 360
        Legend legend = mTarPieChart1.getLegend();
        legend.setEnabled(false);//对pie 块的描述也不要显示

        mTarPieChart1.setData(getPieData(70, 30, getResources().getColor(R.color.text_green)));
        mTarPieChart1.highlightValues(null);
        mTarPieChart1.animateY(1500, Easing.EaseInOutQuad);
        mTarPieChart1.invalidate();
    }

    /**
     * 水平条形图
     * */
    private void initHorizontalBarChart(){
        // 水平条形图初始化
        ViewGroup.LayoutParams lp = mHorizontalBarChart.getLayoutParams();
        lp.width = ScreenUtil.getWidth(getActivity());
        lp.height = ScreenUtil.getWidth(getActivity());
        mHorizontalBarChart.setLayoutParams(lp);

        //设置相关属性
        mHorizontalBarChart.setTouchEnabled(false);
        mHorizontalBarChart.setDrawBarShadow(false);
        mHorizontalBarChart.setDrawValueAboveBar(true);
        mHorizontalBarChart.getDescription().setEnabled(false);
        mHorizontalBarChart.setPinchZoom(false);
        mHorizontalBarChart.setNoDataText("无数据");
        mHorizontalBarChart.setDrawGridBackground(false);

        //x轴
        XAxis xAxis = mHorizontalBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setAvoidFirstLastClipping(true);

        xAxis.setDrawLabels(true);
        xAxis.setGranularity(10f);

        //y轴
        YAxis yAxis = mHorizontalBarChart.getAxisLeft();
        yAxis.setDrawAxisLine(false);
        yAxis.setDrawGridLines(false);
        yAxis.setAxisMinimum(0f);
        yAxis.setEnabled(false);
        //y轴
        YAxis yr = mHorizontalBarChart.getAxisRight();
        yr.setEnabled(false);

        mHorizontalBarChart.setFitBars(true);
        mHorizontalBarChart.animateY(2500);
        Legend legend = mHorizontalBarChart.getLegend();
        legend.setEnabled(false);

        getHorizontalBarChartData();
    }

    /**
     * 获取HorizontalBarChart数据
     * */
    private void getHorizontalBarChartData(){
        XAxis xAxis = mHorizontalBarChart.getXAxis();
        xAxis.setLabelCount(10);
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return lable[(int) (value / spaceForBar)];
//            }
//
//            @Override
//            public int getDecimalDigits() {
//                return 0;
//            }
//        });

        float barWidth = 8f;
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < 10; i++) {
            float val = new Random().nextInt(1000);
            yVals1.add(new BarEntry(i * spaceForBar, val));
        }
        BarDataSet set1;
        if (mHorizontalBarChart.getData() != null &&
                mHorizontalBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mHorizontalBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mHorizontalBarChart.getData().notifyDataChanged();
            mHorizontalBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "DataSet 1");
            set1.setColor(0xffff7700);
            set1.setDrawValues(true);

            //显示为整数
//            set1.setValueFormatter(new IValueFormatter() {
//
//                @Override
//                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                    int valuedate = (int) value;
//                    return valuedate + "";
//                }
//            });
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(barWidth);
            mHorizontalBarChart.setData(data);
        }
    }

    //设置数据
    private void setData(int count, float range) {

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float mult = range / 2f;
            float val = (float) (new Random().nextInt(1500)) + 3000;
            yVals1.add(new Entry(i + 1, val));
        }

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float mult = range-3000;
            float val = (float) (new Random().nextInt(1500));
            yVals2.add(new Entry(i + 1, val));
        }

        ArrayList<Entry> yVals3 = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float mult = range-3000;
            float val = (float) (new Random().nextInt(1500)) - 3000;
            yVals3.add(new Entry(i + 1, val));
        }

        LineDataSet set1, set2, set3;

        if (mLineChart.getData() != null && mLineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mLineChart.getData().getDataSetByIndex(1);
            set3 = (LineDataSet) mLineChart.getData().getDataSetByIndex(2);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            set3.setValues(yVals3);
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        } else {
            // 创建一个数据集,并给它一个类型
            set1 = new LineDataSet(yVals1, "新增会员");

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.isDrawValuesEnabled();
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setCircleColor(Color.BLUE);
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
//            set1.setHighLightColor(Color.rgb(244, 117, 117));  // 设置点击某个点时，横竖两条线的颜色
            set1.setDrawCircleHole(false);

            //创建一个数据集,并给它一个类型
            set2 = new LineDataSet(yVals2, "活跃会员");
            set2.isDrawValuesEnabled();
            set2.setAxisDependency(YAxis.AxisDependency.LEFT);
            set2.setColor(Color.GREEN);
            set2.setCircleColor(Color.RED);
            set2.setLineWidth(2f);
            set2.setCircleRadius(3f);
            set2.setFillAlpha(65);
            set2.setFillColor(Color.RED);
            set2.setDrawCircleHole(false);
//            set2.setHighLightColor(Color.rgb(244, 117, 117));

            set3 = new LineDataSet(yVals3, "流失会员");
            set3.setAxisDependency(YAxis.AxisDependency.LEFT);
            set3.setColor(Color.YELLOW);
            set1.isDrawValuesEnabled();
            set3.setCircleColor(Color.RED);
            set3.setLineWidth(2f);
            set3.setCircleRadius(3f);
            set3.setFillAlpha(65);
            set3.setFillColor(ColorTemplate.colorWithAlpha(Color.YELLOW, 200));
            set3.setDrawCircleHole(false);
//            set3.setHighLightColor(Color.rgb(244, 117, 117));

            // 创建一个数据集的数据对象
            LineData data = new LineData(set1, set2, set3);
            data.setValueTextColor(Color.BLACK);
            data.setValueTextSize(9f);

            //设置数据
            mLineChart.setData(data);
        }
    }

    /**
     * 组合pie chart 的数据
     *
     * @param completeNum   完成的
     * @param remainNum     剩下的
     * @param completeColor 颜色
     * @return
     */
    private PieData getPieData(int completeNum, int remainNum, int completeColor) {
        //X轴数据
        ArrayList<String> xValues = new ArrayList<String>();
        xValues.add("1");
        xValues.add("2");
        xValues.add("3");

        //Y轴数据 -- start ************************************* //
        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();
        yValues.add(new PieEntry(completeNum, 0));
        yValues.add(new PieEntry(remainNum, 1));
        yValues.add(new PieEntry((completeNum + remainNum) / 9f, 2));
        PieDataSet dataSet = new PieDataSet(yValues, "Election Results");
        dataSet.setSliceSpace(0f);
        dataSet.setSelectionShift(5f);
        dataSet.setDrawValues(false);
        //Y轴数据 -- end ************************************* //

        //颜色值
        ArrayList<Integer> colors = new ArrayList<Integer>();

        colors.add(completeColor);
        colors.add(0xffe9e9e9);
        colors.add(0xffffffff);
        dataSet.setColors(colors);

        //设置数据开始画
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        return data;
    }
}
