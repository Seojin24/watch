package com.watch2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class ExActivity4 extends Activity {

    //private TextView mTextView;
    double[] met = {3,3.3,3.5,3.6,4,5.5,7,8,10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex4);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {


                final int w = Integer.parseInt(getIntent().getStringExtra("w"));

                final NumberPicker mNp = (NumberPicker) stub.findViewById(R.id.np);
                int minValue = 10;
                int maxValue = 300;
                int step = 10;

                String[] valueSet = new String[30];

                for (int i = 0; i < 30; i++) {
                    valueSet[i] = String.valueOf(step*(i+1));
                }

                mNp.setMinValue(0);
                mNp.setMaxValue(29);
                mNp.setDisplayedValues(valueSet);

                        Button bt = (Button)stub.findViewById(R.id.button3);
                        bt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int min = ((mNp.getValue()+1)*10);
                                int wei = Integer.parseInt(getPref("weight"));
                                int kal = (int)met[w]*wei*min*5/1000;

                                Intent in = new Intent(ExActivity4.this,ExActivity2.class);
                                in.putExtra("w",kal+"");
                                startActivity(in);
                                overridePendingTransition(0, 0);
                                finish();

                            }
                        });

            }
        });
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

