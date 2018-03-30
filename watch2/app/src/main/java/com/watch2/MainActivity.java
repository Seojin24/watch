package com.watch2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.input.WearableButtons;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    //private TextView mTextView;
    Button bt,bt2,bt3,bt4;
    int i = 0;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                TextView mTextView = (TextView) stub.findViewById(R.id.textView10);
                bt = (Button) stub.findViewById(R.id.button1);
                bt2 = (Button) stub.findViewById(R.id.button2);
                bt3 = (Button) stub.findViewById(R.id.button4);
                bt4 = (Button) stub.findViewById(R.id.button5);
                iv = (ImageView) stub.findViewById(R.id.imageView2);
                mTextView.setText("LV.1 "+getPref("name"));
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"식단입력",Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(MainActivity.this,MainActivity1.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                    }
                });
                bt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"그래프",Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(MainActivity.this,GraphActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                    }
                });

                bt3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"운동량입력",Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(MainActivity.this,ExActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                    }
                });

                bt4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"스토어 준비중입니다..",Toast.LENGTH_SHORT).show();
                    }
                });

                double bmi = Double.parseDouble(getPref("BMI"));
                Toast.makeText(MainActivity.this,"BMI = "+bmi,Toast.LENGTH_SHORT).show();
                if(bmi<18){
                    iv.setImageResource(R.drawable.v1);
                }else if(bmi<20){
                    iv.setImageResource(R.drawable.v2);
                }else if(bmi<25){
                    iv.setImageResource(R.drawable.v3);
                }else if(bmi<30){
                    iv.setImageResource(R.drawable.v4);
                }else{
                    iv.setImageResource(R.drawable.v5);
                }

                iv.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        Toast.makeText(MainActivity.this,"내정보",Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(MainActivity.this,StartActivity7.class);
                        startActivityForResult(in,1);
                        overridePendingTransition(0, 0);
                        return false;
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {

            double bmi = Double.parseDouble(getPref("BMI"));
            Toast.makeText(MainActivity.this,"BMI = "+bmi,Toast.LENGTH_SHORT).show();
            if(bmi<18){
                iv.setImageResource(R.drawable.v1);
            }else if(bmi<20){
                iv.setImageResource(R.drawable.v2);
            }else if(bmi<25){
                iv.setImageResource(R.drawable.v3);
            }else if(bmi<30){
                iv.setImageResource(R.drawable.v4);
            }else{
                iv.setImageResource(R.drawable.v5);
            }


        }
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
