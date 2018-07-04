package com.example.dhairya.registrationcop290;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.widget.Toast;

public class Home extends AppCompatActivity {

//    private GestureDetectorCompat gestureDetectorCompat;
    private int min_distance = 350;
    private float downX, downY, upX, upY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);
//        gestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());
        Typeface tf1 = Typeface.createFromAsset(getAssets(), "fonts/cac_champagne.ttf");
        TextView tv1 = (TextView) findViewById(R.id.titleText);
        tv1.setTypeface(tf1);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), "fonts/Aller_Bd.ttf");
        TextView tv2 = (TextView) findViewById(R.id.courseTitle);
        tv2.setTypeface(tf2);
        Typeface tf3 = Typeface.createFromAsset(getAssets(), "fonts/Aller_Bd.ttf");
        TextView tv3 = (TextView) findViewById(R.id.sessionText);
        tv3.setTypeface(tf3);
        Typeface tf4 = Typeface.createFromAsset(getAssets(), "fonts/Aller_Rg.ttf");
        TextView tv4 = (TextView) findViewById(R.id.sessionText);
        tv4.setTypeface(tf4);

        final ViewGroup rootview = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        rootview.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        downX = event.getX();
                        downY = event.getY();
                        return true;
                    }
                    case MotionEvent.ACTION_UP: {
                        upX = event.getX();
                        upY = event.getY();

                        float deltaX = downX - upX;
                        float deltaY = downY - upY;

                        //HORIZONTAL SCROLL
                        if (Math.abs(deltaX) > Math.abs(deltaY)) {
                            if (Math.abs(deltaX) > min_distance) {
                                // left or right

                                if (deltaX > 0) {
                                    this.onRightToLeftSwipe();
                                    return true;
                                }
                            }
                        }

                        return false;
                    }
                }
                return false;
            }

            public void onRightToLeftSwipe() {
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
            }

        });
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        this.gestureDetectorCompat.onTouchEvent(event);
//        return super.onTouchEvent(event);
//    }

//    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
//        //handle 'swipe left' action only
//
//        @Override
//        public boolean onFling(MotionEvent event1, MotionEvent event2,
//                               float velocityX, float velocityY) {
//
//         /*
//         Toast.makeText(getBaseContext(),
//          event1.toString() + "\n\n" +event2.toString(),
//          Toast.LENGTH_SHORT).show();
//         */
//
//            if(event2.getX() + 350 < event1.getX()){
//
//                //switch another activity
//                Intent intent = new Intent(Home.this, MainActivity.class);
//                startActivity(intent);
//            }
//
//            return true;
//        }
//    }
//@Override
//protected void onPause()
//{
//    super.onPause();
//    //closing transition animations
//    overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
//}
}
