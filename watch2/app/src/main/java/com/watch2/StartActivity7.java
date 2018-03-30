package com.watch2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class StartActivity7 extends Activity {

    //private TextView mTextView;
    int i = 0;
    DBAdapter db;
    TextView tv,tv2,tv3,tv00;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start7);
            db = new DBAdapter(StartActivity7.this);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {

                final RadioButton rbt = (RadioButton)stub.findViewById(R.id.radioButton2);
                Button bt = (Button)stub.findViewById(R.id.button3);
                tv = (TextView)stub.findViewById(R.id.textView5);
                tv2 = (TextView)stub.findViewById(R.id.textView3);
                tv3 = (TextView)stub.findViewById(R.id.textView7);

                tv00 = (TextView)stub.findViewById(R.id.textView00);

                LinearLayout l1 = (LinearLayout)stub.findViewById(R.id.li1);
                LinearLayout l2 = (LinearLayout)stub.findViewById(R.id.li2);
                LinearLayout l3 = (LinearLayout)stub.findViewById(R.id.li3);

                rbt.setChecked(true);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(rbt.isChecked()){
                            savePref("gender","male");
                        }else{
                            savePref("gender","female");
                        }
                        savePref("BMI",""+(Float.parseFloat(getPref("weight"))/((Float.parseFloat(getPref("height"))/100)*(Float.parseFloat(getPref("height"))/100))));

                        overridePendingTransition(0, 0);
                        finish();
                    }
                });
                tv.setText(getPref("height")+"CM");
                tv2.setText(getPref("age")+"살");
                tv3.setText(getPref("weight")+"KG");
                tv00.setText(db.getp()+"P");

                l1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent in = new Intent(StartActivity7.this,StartActivity_1.class);
                        startActivityForResult(in,1);
                        overridePendingTransition(0, 0);
                    }
                });
                l2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent in = new Intent(StartActivity7.this,StartActivity_2.class);
                        startActivityForResult(in,1);
                        overridePendingTransition(0, 0);
                    }
                });
                l3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent in = new Intent(StartActivity7.this,StartActivity_4.class);
                        startActivityForResult(in,1);
                        overridePendingTransition(0, 0);
                    }
                });

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {

            tv.setText(getPref("height")+"CM");
            tv2.setText(getPref("age")+"살");
            tv3.setText(getPref("weight")+"KG");

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
