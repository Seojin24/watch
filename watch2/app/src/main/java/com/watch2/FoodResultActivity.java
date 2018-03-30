package com.watch2;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FoodResultActivity extends Activity {

    //private TextView mTextView;
    String r = "";
    int posi = 0;
    DBAdapter db;
    private int[][] f1 = {{250,500,750,1000,1250,1500},
            {325,650,975,1300,1625,1950},
            {250,500,750,1000,1250,1500},
            {250,500,750,1000,1250,1500},
            {350,700,1050,1400,1750,2100},
            {285,570,855,1140,1425,1710}};

    private int[][] f2 = {{450,900,1350,1800,2250,2700},
            {550,1100,1650,2200,2750,3300},
            {400,800,1200,1600,2000,2400},
            {900,1800,2700,3600,4500,5400},
            {150,300,450,600,750,900},
            {500,1000,1500,2000,2500,3000}};

    private int[][] f3 = {{450,900,1350,1800,2250,2700},
            {400,800,1200,1600,2000,2400},
            {375,750,1125,1500,1875,2250},
                    {225,450,675,900,1125,1350},
                            {410,820,1230,1640,2050,2460}};

    private int[][] f4 = {
            {275,550,825,1100,1375,1650},
            {175,350,525,700,875,1050},
            {325,650,975,1300,1625,1950},
            {390,780,1170,1560,1950,2340},
            {300,600,900,1200,1500,1800},
            {310,620,930,1240,1550,1860}};

    private int[][] f5 = {
            {175,350,525,700,875,1050},
            {200,400,600,800,1000,1200},
            {150,300,450,600,750,900},
            {75,150,225,300,375,450},
            {250,500,750,1000,1250,1500},
            {200,400,600,800,1000,1200}};

    private int[][] f6 = {{300,600,900,1200,1500,1800},
            {250,500,750,1000,1250,1500},
            {250,500,750,1000,1250,1500},
            {500,1000,1500,2000,2500,3000},
            {120,240,360,480,600,720},
            {80,160,240,320,400,480},
            {300,600,900,1200,1500,1800}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodresult);
        db = new DBAdapter(FoodResultActivity.this);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {

                r = getIntent().getStringExtra("which");

                final NumberPicker mNp = (NumberPicker) stub.findViewById(R.id.np);

                mNp.setMinValue(0);
                mNp.setMaxValue(5);

                if(r.split(",")[0].equals("1")){
                    String[] valueSet = {"0.5인분","1인분","1.5인분","2인분","2.5인분","3인분"};
                    mNp.setDisplayedValues(valueSet);
                    mNp.setValue(1);
                    posi = 1;
                }else if(r.split(",")[0].equals("2")){
                    String[] valueSet = {"1조각/1개","2조각/2개","3조각/3개","4조각/4개","5조각/5개","6조각/6개"};
                    mNp.setDisplayedValues(valueSet);
                    mNp.setValue(0);
                    posi = 0;
                }else if(r.split(",")[0].equals("3")){
                    String[] valueSet = {"0.5인분","1인분","1.5인분","2인분","2.5인분","3인분"};
                    mNp.setDisplayedValues(valueSet);
                    mNp.setValue(1);
                    posi = 1;
                }else if(r.split(",")[0].equals("4")){
                    String[] valueSet = {"0.5인분","1인분","1.5인분","2인분","2.5인분","3인분"};
                    mNp.setDisplayedValues(valueSet);
                    mNp.setValue(1);
                    posi = 1;
                }else if(r.split(",")[0].equals("5")){
                    String[] valueSet = {"0.5인분","1인분","1.5인분","2인분","2.5인분","3인분"};
                    mNp.setDisplayedValues(valueSet);
                    mNp.setValue(1);
                    posi = 1;
                }else if(r.split(",")[0].equals("6")){
                    String[] valueSet = {"1개","2개","3개","4개","5개","6개"};
                    mNp.setDisplayedValues(valueSet);
                    mNp.setValue(0);
                    posi = 0;
                }


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

                        String r2 = "";
                        if(r.split(",")[0].equals("1")){
                            r2 = f1[Integer.parseInt(r.split(",")[1])-1][posi]+"";
                        }else if(r.split(",")[0].equals("2")){
                            r2 = f2[Integer.parseInt(r.split(",")[1])-1][posi]+"";
                        }else if(r.split(",")[0].equals("3")){
                            r2 = f3[Integer.parseInt(r.split(",")[1])-1][posi]+"";
                        }else if(r.split(",")[0].equals("4")){
                            r2 = f4[Integer.parseInt(r.split(",")[1])-1][posi]+"";
                        }else if(r.split(",")[0].equals("5")){
                            r2 = f5[Integer.parseInt(r.split(",")[1])-1][posi]+"";
                        }else if(r.split(",")[0].equals("6")){
                            r2 = f6[Integer.parseInt(r.split(",")[1])-1][posi]+"";
                        }

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        String currentDateandTime = sdf.format(new Date());
                        Date date = null;
                        try {
                            date = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(currentDateandTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String whi = "1";
                        SimpleDateFormat format = new SimpleDateFormat("HH", Locale.US);
                        int hour = Integer.parseInt(format.format(new Date()));
                        if(hour<10){
                            whi = "1";
                        }else if(hour<16){
                            whi = "2";
                        }else{
                            whi = "3";
                        }
                        long cu = date.getTime();
                        db.insertT("1",whi,Integer.parseInt(r2),Long.toString(cu));

                        Toast.makeText(FoodResultActivity.this,"섭취량 "+r2+"kcal가 적립되었습니다.",Toast.LENGTH_LONG).show();
                        finish();
                        overridePendingTransition(0, 0);

                    }
                });


            }
        });
    }

}

