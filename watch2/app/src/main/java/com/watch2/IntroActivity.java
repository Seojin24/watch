package com.watch2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends Activity {

    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
              //  mTextView = (TextView) stub.findViewById(R.id.text);
                bt = (Button)stub.findViewById(R.id.button2);
                //savePref("gender"," ");
                //new DBAdapter(IntroActivity.this).deleteall();
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(getPref("gender").equals(" ")){
                        Intent in = new Intent(IntroActivity.this,StartActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        finish();
                        }else{
                            Intent in = new Intent(IntroActivity.this,MainActivity.class);
                            startActivity(in);
                            overridePendingTransition(0, 0);
                            finish();
                        }
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
