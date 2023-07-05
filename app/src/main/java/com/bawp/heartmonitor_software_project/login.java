package com.bawp.heartmonitor_software_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private TextView backbutton,forgotpassword;
    private TextInputEditText email,password;
    private Button signin;


    //DatabaseReference reff;
    //private FirebaseAuth.AuthStateListener authStateListener;

    FirebaseAuth mAuth=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, sign_Up.class);
                startActivity(intent);
            }
        });
        email=findViewById(R.id.registeremail);
        password=findViewById(R.id.registerpassword);
        signin=findViewById(R.id.registerbutton);
        forgotpassword=findViewById(R.id.forgotpassword);
        //loader=new ProgressDialog(this);




        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail=email.getText().toString().trim();
                String pass=password.getText().toString().trim();

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
                }else {
                    //loader.setMessage("Logging....");
                    // loader.setCanceledOnTouchOutside(false);
                    // loader.show();


                    mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(login.this, "Login successful!", Toast.LENGTH_SHORT).show();
                                String newmail=mail.replace(".",",");

                                // reff= FirebaseDatabase.getInstance().getReference().child("Common").child(newmail);

                                 Intent intent = new Intent(login.this,HomeActivity.class);
                                 //arpa
                                 intent.putExtra("email",newmail);
                                 //arpa
                                 startActivity(intent);
                                //finish();
                            } else {
                                Toast.makeText(login.this,task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                            //loader.dismiss();

                        }
                    });
                }
            }
        });
    }
}