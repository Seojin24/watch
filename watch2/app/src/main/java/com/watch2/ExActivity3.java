package com.watch2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class ExActivity3 extends Activity {

    //private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex3);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {

                int p = Integer.parseInt(getIntent().getStringExtra("p"));

                TextView tv = (TextView)stub.findViewById(R.id.textView2);
                tv.setText(p+"P 가\n적립되었습니다!");
                Button bt = (Button)stub.findViewById(R.id.button3);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        finish();
                        overridePendingTransition(0, 0);

                    }
                });

            }
        });
    }


}

