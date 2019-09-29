package com.example.vidiok;

import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class video1 extends YouTubeBaseActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    View swipeView;
    GestureDetector mGestureDetector;
    ArrayList<Integer> order;
    String[] vidlist;
    StringTokenizer str;
    String curr;
    int currIndex;
    private static final String TAG = "video1";
    public String finalo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video1);
        Log.d(TAG,"Entered Video1");
        //receiving intent content
       finalo = getIntent().getStringExtra("data");
        //extracting the array list from the string
        str = new StringTokenizer(finalo,",");
        order = new ArrayList<>();
        while(str.hasMoreTokens())
            order.add(Integer.parseInt(str.nextToken()));
        vidlist = new String[]{"S_dfq9rFWAE","07d2dXHYb94","4fp1-aT2fTM","LJlYX7PZ9UU","DXUAyRRkI6k"};

        currIndex = order.get(MainActivity.count);
        curr = vidlist[currIndex];

        //Linking the components in the screen
        youTubePlayerView = findViewById(R.id.ytpl);
        swipeView = findViewById(R.id.swipeview);

        //setting the onTouchlistener with the view
        swipeView.setOnTouchListener(this);

        //initializing the GestureDetector  object
        mGestureDetector = new GestureDetector(this,this);

        //upon initializing the youtube video
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(curr);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        youTubePlayerView.initialize(playerConfig.getApiKey(),onInitializedListener);

    }
    /*@Override
    public void onBackPressed()
    {
        Intent intent = new Intent(video1.this,MainActivity.class);
        startActivity(intent);
        finish();
    }*/


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        mGestureDetector.onTouchEvent(motionEvent);

        return true ;
    }



    //Since it GestureDetector is an interface we need to implement all of its methods
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        //will be triggered every time we touch the screen
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent motionEvent, float v, float v1) {

        float Xdiff = motionEvent.getX() - downEvent.getX();
        float Ydiff = motionEvent.getY() - downEvent.getY();
        if(Math.abs(Xdiff) > Math.abs(Ydiff)) {

            //Can be either left swipe or right swipe because the difference between the x is more
            // than that of y

            if (motionEvent.getX() > downEvent.getX()) {

                //This is for left to right gesture handling
                Log.d(TAG,"Swipe right");
                //Toast.makeText(video1.this, "Left to right", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(video1.this,Profile.class);
                intent.putExtra("data",currIndex);
                startActivity(intent);


            } else if (motionEvent.getX() < downEvent.getX()) {

                //This is for right to left gesture handling
                Log.d(TAG,"Swipe left");
                Toast.makeText(video1.this, "You've Successfully Subscribed to the Creator of this video", Toast.LENGTH_LONG).show();
            }
        }
        else{

            //Can be either up swipe or right swipe because the difference between the y is more
            //than that of x

            if(motionEvent.getY() > downEvent.getY()){
                //This is for up to down swipe
                //Toast.makeText(video1.this,"Up to down",Toast.LENGTH_LONG).show();
                Log.d(TAG,"Swipe up");
                MainActivity.count--;
                if(MainActivity.count < 0){
                    Toast.makeText(video1.this,"You,re at the first video",Toast.LENGTH_LONG).show();
                    MainActivity.count=0;
                    Log.d(TAG,"this is the first video");
                }
                else
                {
                    Log.d(TAG,"Going to previous video");
                    Intent intent = new Intent(video1.this,video1.class);
                    intent.putExtra("data",finalo);
                    startActivity(intent);
                    finish();

                }
            }
            else if(motionEvent.getY() < downEvent.getY()){
                //This is for down to up swipe
                //Toast.makeText(video1.this,"Down to up",Toast.LENGTH_LONG).show();
                Log.d(TAG,"Swipe down");
                MainActivity.count++;
                if(MainActivity.count<5) {
                    Log.d(TAG,"Going to next video");
                    Intent intent = new Intent(video1.this, video1.class);
                    intent.putExtra("data",finalo);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(video1.this,"No more videos to show",Toast.LENGTH_LONG).show();
                    Log.d(TAG,"this is the last video");
                    MainActivity.count=4;
                }

            }
        }

        return true;
    }
}
