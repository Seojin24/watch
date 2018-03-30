package com.watch2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class StartActivity_2 extends Activity {

    //private TextView mTextView;
    int i = 0;
    int posi = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start2);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                final NumberPicker mNp = (NumberPicker) stub.findViewById(R.id.np);
                mNp.setMaxValue(100);
                mNp.setMinValue(1);
                mNp.setValue(20);
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
                        savePref("age",posi+"");
                        finish();
                        overridePendingTransition(0, 0);
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
