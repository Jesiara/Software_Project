package com.bawp.heartmonitor_software_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertActivity extends AppCompatActivity {

    private Button saveButton;
    private EditText date, time, systolic, diastolic, heartRate, comment;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        String mail = getIntent().getStringExtra("email");

        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        systolic = findViewById(R.id.systolic);
        diastolic = findViewById(R.id.diastolic);
        heartRate = findViewById(R.id.heartRate);
        comment = findViewById(R.id.Comment);
        saveButton = findViewById(R.id.saveButton);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Details");//.child(mail);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processInsert();
            }
        });
    }

    private void processInsert() {
        String dat = date.getText().toString().trim();
        String t = time.getText().toString().trim();
        String s = systolic.getText().toString().trim();
        String d = diastolic.getText().toString().trim();
        String hr = heartRate.getText().toString().trim();
        String c = comment.getText().toString().trim();

        if (TextUtils.isEmpty(dat)) {
            date.setError("Required");
            return;
        }

        if (TextUtils.isEmpty(t)) {
            time.setError("Required");
            return;
        }

        int x;
        if (TextUtils.isEmpty(s)) {
            systolic.setError("Required");
            return;
        } else {
            x = Integer.parseInt(s);
            if (x < 60 || x > 200) {
                systolic.setError("Invalid Data!");
                return;
            }
        }

        int y;
        if (TextUtils.isEmpty(d)) {
            diastolic.setError("Required");
            return;
        } else {
            y = Integer.parseInt(d);
            if (y < 20 || y > 150) {
                diastolic.setError("Invalid Data!");
                return;
            }
        }

        int z;
        if (TextUtils.isEmpty(hr)) {
            heartRate.setError("Required");
            return;
        } else {
            z = Integer.parseInt(hr);
            if (z < 40 || z > 180) {
                heartRate.setError("Invalid Data!");
                return;
            }
        }

        String id = databaseReference.push().getKey();

        Userdets userdets = new Userdets(dat, t, s, d, hr, c);

        if (id != null) {
            databaseReference.child(id).setValue(userdets)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(InsertActivity.this, "Details added!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(InsertActivity.this, HomeActivity.class));
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(InsertActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
