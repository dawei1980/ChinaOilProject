package com.china.oil.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.china.oil.R;
import com.china.oil.uils.DataStringUtil;
import com.china.oil.uils.PieChartUtil;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    private View view;

    @BindView(R.id.pie_chart)
    PieChart pieChart;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.back_btn)
    Button back_btn;
    @BindView(R.id.home_time)
    TextView home_time;
    @BindView(R.id.horizontal_bar_chart)
    HorizontalBarChart barChart;

    private HashMap dataMap;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_home, null);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ButterKnife.bind(this, view);

        initView();
        setPieChart();

        initBarChart();
        return view;
    }

    private void initView(){
        title_text.setText("图表展示");
        back_btn.setVisibility(View.GONE);
        home_time.setText(DataStringUtil.StringData());
    }

    private void setPieChart(){
        dataMap=new HashMap();
        dataMap.put("Ⅰ石油","8");
        dataMap.put("Ⅱ石油","12");
        dataMap.put("Ⅲ石油","31");
        dataMap.put("Ⅳ石油","24");
        dataMap.put("Ⅴ石油","10");
        dataMap.put("劣Ⅴ石油","15");

        PieChartUtil.getPitChart().setPieChart(pieChart,dataMap,"石油",true);

        //点击事件
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                PieEntry pieEntry=(PieEntry)e;
                pieChart.setCenterText(pieEntry.getLabel());
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void initBarChart() {
        barChart.getDescription().setEnabled(false); // 不显示描述
        barChart.getLegend().setEnabled(false); // 不显示图例
        barChart.setExtraOffsets(30, 30, 30, 30);  // 设置饼图的偏移量，类似于内边距 ，设置视图窗口大小
        setAxis();  // 设置坐标轴
        setData(); // 设置数据
    }

    private void setData() {
        List<BarEntry> entryList = new ArrayList<>();
        entryList.add(new BarEntry(0, 60.51f));
        entryList.add(new BarEntry(1, 26.28f));
        entryList.add(new BarEntry(2, 13.20f));

        BarDataSet barDataSet = new BarDataSet(entryList, "");
        barDataSet.setColors(Color.GREEN, Color.BLUE, Color.RED);
        barDataSet.setValueTextColor(Color.RED);
        barDataSet.setValueTextSize(15f);
        barDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                return v + "%";
            }
        });
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.4f); // 设置柱子的宽度
        barChart.setData(barData);
    }

    /**
     * 因为此处的柱状图为水平柱状图，所以x轴变y轴，y轴变x轴
     */
    private void setAxis() {
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(15f);
        xAxis.setLabelCount(3);
        xAxis.setGranularity(1f); // 防止放大图后，标签错乱
        final String label[] = {"昨日卖出石油总量", "今日卖出石油总量", "未来卖出石油总量"};
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                try {
                    return label[(int) v];
                } catch (Exception e) {
                    return "";
                }
            }
        });

        YAxis yAxis_right = barChart.getAxisRight();
        yAxis_right.setAxisMinimum(0f);
        yAxis_right.setAxisMaximum(100f);
        yAxis_right.setTextSize(15f);
        yAxis_right.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return v + "0%";
            }
        });

        // 不显示最顶部的轴
        YAxis yAxis_left = barChart.getAxisLeft();
        yAxis_left.setAxisMinimum(0f);
        yAxis_left.setAxisMaximum(100f);
        yAxis_left.setEnabled(false);
    }
}
