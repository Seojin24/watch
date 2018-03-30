package com.watch2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class ExActivity extends Activity {

    //private TextView mTextView;
    int posi = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {


                final NumberPicker mNp = (NumberPicker) stub.findViewById(R.id.np);

                String[] valueSet = {"자전거 가볍게","걷기","집에서운동","조금빠르게걷기","자전거 중간","자전거 빠르게","조깅","체력운동","줄넘기"};


                mNp.setMinValue(0);
                mNp.setMaxValue(8);
                mNp.setDisplayedValues(valueSet);
                mNp.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        if(newVal!=0)
                            posi = newVal-1;
                        else
                            posi = picker.getMaxValue();
                    }
                });

                        Button bt = (Button)stub.findViewById(R.id.button3);
                        bt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent in = new Intent(ExActivity.this,ExActivity4.class);
                                in.putExtra("w",posi+"");
                                startActivity(in);
                                overridePendingTransition(0, 0);
                                finish();

                            }
                        });

            }
        });
    }


}

