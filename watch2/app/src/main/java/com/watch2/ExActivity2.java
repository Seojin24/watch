package com.watch2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ExActivity2 extends Activity {

    DBAdapter db;
    //private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex2);
        db = new DBAdapter(ExActivity2.this);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
           final int w = Integer.parseInt(getIntent().getStringExtra("w"));

                TextView tv = (TextView)stub.findViewById(R.id.textView3);
                tv.setText(w+"");

                Button bt = (Button)stub.findViewById(R.id.button3);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
                        db.insertT("2",whi,w,Long.toString(cu));
                        Intent in = new Intent(ExActivity2.this,ExActivity3.class);
                        in.putExtra("p",(w)+"");
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        finish();

                    }
                });
            }
        });
    }


}

