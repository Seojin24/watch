package com.watch2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;

public class FoodActivity3 extends Activity {

    //private TextView mTextView;
    Button back,bt1,bt2,bt3,bt4,bt5,bt6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food2_3);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                //mTextView = (TextView) stub.findViewById(R.id.text);
                back = (Button) stub.findViewById(R.id.back);
                bt1 = (Button) stub.findViewById(R.id.button1);
                bt2 = (Button) stub.findViewById(R.id.button2);
                bt3 = (Button) stub.findViewById(R.id.button3);
                bt4 = (Button) stub.findViewById(R.id.button4);
                bt5 = (Button) stub.findViewById(R.id.button5);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(FoodActivity3.this,MainActivity1.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        finish();
                    }
                });

                bt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(FoodActivity3.this,FoodResultActivity.class);
                        in.putExtra("which","3,1");
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        finish();
                    }
                });
                bt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(FoodActivity3.this,FoodResultActivity.class);
                        in.putExtra("which","3,2");
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        finish();
                    }
                });
                bt3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(FoodActivity3.this,FoodResultActivity.class);
                        in.putExtra("which","3,3");
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        finish();
                    }
                });
                bt4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(FoodActivity3.this,FoodResultActivity.class);
                        in.putExtra("which","3,4");
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        finish();
                    }
                });
                bt5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(FoodActivity3.this,FoodResultActivity.class);
                        in.putExtra("which","3,5");
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        finish();
                    }
                });


            }
        });
    }
}
