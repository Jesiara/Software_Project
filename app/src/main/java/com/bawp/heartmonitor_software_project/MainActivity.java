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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private ImageView logo;
    private TextView title,name1,name2;

    Animation topanimation,bottomanimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        logo=findViewById(R.id.logo);
        title=findViewById(R.id.title);
        name1=findViewById(R.id.developtxtid);
        name2=findViewById(R.id.developtxtid2);
        topanimation= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanimation=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //jesi
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        logo.setAnimation(topanimation);
        title.setAnimation(bottomanimation);
        name1.setAnimation(bottomanimation);
        name2.setAnimation(bottomanimation);

        int SPLASH_SCREEN=4300;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //jesi
                //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (user != null) {
                    String mail;
                    mail=user.getEmail();
                    String newmail=mail.replace(".",",");

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("email",newmail);
                    startActivity(intent);
                    finish();
                } else {

                    Intent intent = new Intent(MainActivity.this, login.class);
                    startActivity(intent);
                    finish();

                }

                //jesi
            }
        },SPLASH_SCREEN);
    }
}