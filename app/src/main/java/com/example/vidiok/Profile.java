package com.example.vidiok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.StringTokenizer;
import java.util.concurrent.TimeoutException;

public class Profile extends AppCompatActivity {

    TextView namet;
    TextView countryt;
    String[]  list;
    int currIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        namet = findViewById(R.id.name);
        countryt = findViewById(R.id.country);
        list = new String[] {"Andre Moskowitz,Russia","The Animation World,India","The Cat Community,France","Creative Ideas,USA","The Cat World,Italy"};
        currIndex = getIntent().getIntExtra("data",0);
        StringTokenizer str = new StringTokenizer(list[currIndex],",");
        String name = str.nextToken();
        String Country = str.nextToken();
        namet.setText(name);
        countryt.setText(Country);



    }
}
