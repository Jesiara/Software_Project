package com.bawp.heartmonitor_software_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sign_Up extends AppCompatActivity {
    private TextView backbutton;
    private Button signup;
    private TextInputEditText username,age,email,password;


    DatabaseReference reff;
    //private ProgressDialog loader;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        backbutton=findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(sign_Up.this,login.class);
                startActivity(intent);
            }
        });


        signup=findViewById(R.id.registerbutton);
        username=findViewById(R.id.registerusername);
        email=findViewById(R.id.registeremail);
        password=findViewById(R.id.registerpassword);
        age=findViewById(R.id.registerage);



        Users studdent=new Users();

        reff= FirebaseDatabase.getInstance().getReference();

        mAuth=FirebaseAuth.getInstance();
        //reff = FirebaseDatabase.getInstance().getReference().child("users");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usernamee=username.getText().toString().trim();
                final String agee=age.getText().toString().trim();
                final String mail=email.getText().toString().trim();
                final String pass=password.getText().toString().trim();




                if(TextUtils.isEmpty(usernamee))
                {
                    username.setError("Enter your full name");
                    username.requestFocus();
                    return;
                }
                if(agee.isEmpty())
                {
                    age.setError("Enter your id");
                    age.requestFocus();
                    return;
                }
                if(mail.isEmpty())
                {
                    email.setError("Enter an email address");
                    email.requestFocus();
                    return;
                }
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches())
                {
                    email.setError("Enter a valid email address");
                    email.requestFocus();
                    return;
                }
                if(pass.isEmpty())
                {
                    password.setError("Enter a password");
                    password.requestFocus();
                    return;
                }
                if(pass.length()<6) {
                    password.setError("Enter valid password");
                    password.requestFocus();
                    return;
                }
                else{

                    mAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                String error = task.getException().toString();
                                Toast.makeText(sign_Up.this, "Error" + error, Toast.LENGTH_SHORT).show();

                            } else {

                                studdent.setUsername(usernamee);
                                studdent.setAge(agee);
                                studdent.setEmail(mail);
                                //String currentuserid = mAuth.getCurrentUser().getUid();

                                String newmail=mail.replace(".",",");


                                reff.child("Info").child(newmail).setValue(studdent);
                                Toast.makeText(sign_Up.this, "Registration Successful!Click sign in", Toast.LENGTH_SHORT).show();

                                //Intent intent = new Intent(donorregistrationactivity.this, loginactivity.class);
                                // startActivity(intent);

                                //loader.dismiss();
                                // finish();


                            }
                        }
                    });
                }
            }
        });
    }
}