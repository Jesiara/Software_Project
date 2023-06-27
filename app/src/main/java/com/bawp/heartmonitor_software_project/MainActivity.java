package com.bawp.heartmonitor_software_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView logo;
    private TextView title,name1,name2;

    Animation topanimation,bottomanimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();

        logo=findViewById(R.id.logo);
        title=findViewById(R.id.title);
        name1=findViewById(R.id.developtxtid);
        name2=findViewById(R.id.developtxtid2);
        topanimation= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanimation=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        logo.setAnimation(topanimation);
        title.setAnimation(bottomanimation);
        name1.setAnimation(bottomanimation);
        name2.setAnimation(bottomanimation);

        int SPLASH_SCREEN=4300;
        new Handler().postDelayed(new Runnable() {
            /**
             * Intents from Splash Screen to LogInOrSignPage
             */
            @Override
            public void run() {
                Intent intent =new Intent(MainActivity.this, login.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}