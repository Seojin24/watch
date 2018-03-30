package com.watch2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class GraphActivity extends Activity {

    //private TextView mTextView;
    DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                db = new DBAdapter(GraphActivity.this);
                //mTextView = (TextView) stub.findViewById(R.id.text);
                final BarChart chart = (BarChart) stub.findViewById(R.id.chart);

                BarData data = new BarData(getXAxisValues(), getDataSet());
                chart.setData(data);
                chart.setDrawValueAboveBar(false);
                chart.setDrawValuesForWholeStack(false);
                chart.setDescription("주간 그래프");
                chart.animateXY(2000, 2000);
                chart.invalidate();
                Button bt = (Button)stub.findViewById(R.id.button1);
                Button bt2 = (Button)stub.findViewById(R.id.button2);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BarData data = new BarData(getXAxisValues(), getDataSet());
                        chart.setData(data);
                        chart.setDescription("주간 그래프");
                        chart.animateXY(2000, 2000);
                        chart.setDrawValueAboveBar(false);
                        chart.setDrawValuesForWholeStack(false);
                        chart.invalidate();
                    }
                });

                bt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BarData data = new BarData(getXAxisValues2(), getDataSet2());
                        chart.setData(data);
                        chart.setDescription("일간 그래프");
                        chart.animateXY(2000, 2000);
                        chart.setDrawValueAboveBar(false);
                        chart.setDrawValuesForWholeStack(false);
                        chart.invalidate();
                    }
                });

            }
        });
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDateandTime = sdf.format(new Date());
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(currentDateandTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long cu = date.getTime();

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry((float)db.getday(Long.toString(cu-(6*86400000)),"1"), 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry((float)db.getday(Long.toString(cu-(5*86400000)),"1"), 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry((float)db.getday(Long.toString(cu-(4*86400000)),"1"), 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry((float)db.getday(Long.toString(cu-(3*86400000)),"1"), 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry((float)db.getday(Long.toString(cu-(2*86400000)),"1"), 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry((float)db.getday(Long.toString(cu-86400000),"1"), 5); // Jun
        valueSet1.add(v1e6);
        BarEntry v1e7 = new BarEntry((float)db.getday(Long.toString(cu),"1"), 6); // Jun
        valueSet1.add(v1e7);

        int sin = (int)(21.6*(Integer.parseInt(getPref("weight"))-Double.parseDouble(getPref("BMI"))))+370;
        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(((float)db.getday(Long.toString(cu-(6*86400000)),"2")+sin), 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(((float)db.getday(Long.toString(cu-(5*86400000)),"2")+sin), 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(((float)db.getday(Long.toString(cu-(4*86400000)),"2")+sin), 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(((float)db.getday(Long.toString(cu-(3*86400000)),"2")+sin), 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(((float)db.getday(Long.toString(cu-(2*86400000)),"2")+sin), 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(((float)db.getday(Long.toString(cu-(1*86400000)),"2")+sin), 5); // Jun
        valueSet2.add(v2e6);
        BarEntry v2e7 = new BarEntry(((float)db.getday(Long.toString(cu),"2")+sin), 6); // Jun
        valueSet2.add(v2e7);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "섭취량");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "소모량");
        barDataSet2.setColor(Color.rgb(155, 0, 0));

        barDataSet1.setDrawValues(false);
        barDataSet2.setDrawValues(false);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("6일전");
        xAxis.add("5일전");
        xAxis.add("4일전");
        xAxis.add("3일전");
        xAxis.add("2일전");
        xAxis.add("어제");
        xAxis.add("오늘");
        return xAxis;
    }



    private ArrayList<BarDataSet> getDataSet2() {
        ArrayList<BarDataSet> dataSets = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDateandTime = sdf.format(new Date());
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(currentDateandTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long cu = date.getTime();

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry((float)db.getday2(Long.toString(cu),"1","1"), 0);
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry((float)db.getday2(Long.toString(cu),"1","2"), 1);
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry((float)db.getday2(Long.toString(cu),"1","3"), 2);
        valueSet1.add(v1e3);


        int sin = (int)(21.6*(Integer.parseInt(getPref("weight"))-Double.parseDouble(getPref("BMI"))))+370;
        sin = sin/3;
        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(((float)db.getday2(Long.toString(cu),"2","1")+sin), 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(((float)db.getday2(Long.toString(cu),"2","2")+sin), 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(((float)db.getday2(Long.toString(cu),"2","3")+sin), 2);
        valueSet2.add(v2e3);


        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "섭취량");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "소모량");
        barDataSet2.setColor(Color.rgb(155, 0, 0));
        barDataSet1.setDrawValues(false);
        barDataSet2.setDrawValues(false);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues2() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("아침");
        xAxis.add("점심");
        xAxis.add("저녁");
        return xAxis;
    }
    private String getPref(String where){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        return pref.getString(where, " ");

    }

    private void savePref(String where, String what){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(where,what);
        editor.commit();
    }
}

