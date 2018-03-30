package com.watch2;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class StartActivity6 extends Activity {

    Handler h;
    //private TextView mTextView;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start6);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {





                h= new Handler();
                h.postDelayed(mrun, 1000);
            }
        });
    }
    Runnable mrun = new Runnable(){
        @Override
        public void run(){

            Intent in = new Intent(StartActivity6.this,MainActivity.class);
            ActivityOptions options =
                    ActivityOptions.makeCustomAnimation(StartActivity6.this, R.anim.fadein, R.anim.fadeout);

            startActivity(in, options.toBundle());

            finish();
        }

    };

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
