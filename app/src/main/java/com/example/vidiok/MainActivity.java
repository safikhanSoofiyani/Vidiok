package com.example.vidiok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    ImageButton enter;
    ImageButton nope;
    //ArrayList<String> vidlist;
    ArrayList<Integer> order;
    Random rd;
    String finalo;
    public static int count ;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enter = findViewById(R.id.button);
        nope = findViewById(R.id.button2);
        count=0;
        String orders = "";

        rd = new Random();
        order = new ArrayList<>(5);
        int count1=0;
        while(count1<5)
        {
            int temp = rd.nextInt(5);
            if(! order.contains(temp)){
                order.add(temp);
                count1++;
            }
        }
        for(int i:order)
        {
            orders = orders + Integer.toString(i)+",";
        }
        finalo = orders.substring(0,orders.length()-1);





        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //enter the place where first video is displayed
                //Toast.makeText(MainActivity.this,finalo,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,video1.class);
                intent.putExtra( "data",finalo);
                startActivity(intent);
                Log.d(TAG,"Clicked button play");

            }
        });

        nope.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //exit the app
                Log.d(TAG,"Clicked button quit");
                finish();
                System.exit(0);
            }
        }));
    }



}
