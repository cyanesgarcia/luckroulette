package com.example.lidia.luckroulette;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity implements Animation.AnimationListener{
    boolean blnButtonRotation =true;
    int intNumber= 8;
    long lngDegrees=0;
    SharedPreferences sharedPreferences;

    ImageView iv_wheel;

    String color= " ";
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.intNumber = this.sharedPreferences.getInt("INT_NUMBER", 8);
        iv_wheel= (ImageView) findViewById(R.id.imageView2);

    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        String number= String.valueOf((int)(((double)this.intNumber)
                -Math.floor(((double)this.lngDegrees)/(360.0d/(double)this.intNumber))));
        if (number.equals("1")){
            color="Red";
        }else if(number.equals("2")){
            color="Orange";
        }else if(number.equals("3")){
            color="Yellow";
        }else if(number.equals("4")){
            color="Light Green";
        }else if(number.equals("5")){
            color="Dark Green";
        }else if(number.equals("6")){
            color="Light Blue";
        }else if(number.equals("7")){
            color="Dark Blue";
        }else if(number.equals("8")){
            color="Purple";
        }
        Toast toast=Toast.makeText(this, color,0);
        toast.setGravity(49,0,0);
        toast.show();
        this.blnButtonRotation=true;
        button.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void onClickButtonRotation(View view)
    {
        int ran= new Random().nextInt(360)+3600;
        RotateAnimation rotateAnimation= new RotateAnimation((float)this.lngDegrees, (float)(this.lngDegrees+ ((long)ran)),1,0.5f,1,0.5f);

        this.lngDegrees=(this.lngDegrees + ((long)ran))%360;
        rotateAnimation.setDuration((long)ran);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setAnimationListener(this);
        iv_wheel.startAnimation(rotateAnimation);
    }
}

