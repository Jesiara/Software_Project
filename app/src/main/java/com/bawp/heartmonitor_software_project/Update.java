package com.bawp.heartmonitor_software_project;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawp.heartmonitor_software_project.Userdets;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Update extends AppCompatActivity {
    private Button ssaveButton;
    private EditText date, time, systolic, diastolic, heartRate, comment;
    private TextView datt, tt, ss, dd, hrr, comm;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        String mail = getIntent().getStringExtra("mail");
        String id = getIntent().getStringExtra("pushId");

        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        systolic = findViewById(R.id.systolic);
        diastolic = findViewById(R.id.diastolic);
        heartRate = findViewById(R.id.heartRate);
        comment = findViewById(R.id.Comment);
        ssaveButton = findViewById(R.id.SsaveButton);

        datt = findViewById(R.id.dets_d);
        tt = findViewById(R.id.dets_t);
        ss = findViewById(R.id.dets_s);
        dd = findViewById(R.id.dets_hr);
        hrr = findViewById(R.id.dets_hr);
        comm = findViewById(R.id.dets_com);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Details");//.child(mail);

        if (id != null) {
            getSupportActionBar().setTitle("Edit Data");
            ssaveButton.setText("Update");
            loadDataForEditing(id);
        }

        ssaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processEdit();
            }
        });
    }

    private void loadDataForEditing(String id) {
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Userdets userdets = dataSnapshot.getValue(Userdets.class);
                    if (userdets != null) {
                        date.setText(userdets.getDate());
                        time.setText(userdets.getTime());
                        systolic.setText(userdets.getSystolic());
                        diastolic.setText(userdets.getDiastolic());
                        heartRate.setText(userdets.getHeartRate());
                        comment.setText(userdets.getComment());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Update.this, "Failed to load data for editing", Toast.LENGTH_SHORT).show();
                finish();
            }
        };

        databaseReference.child(id).addListenerForSingleValueEvent(valueEventListener);
    }

    private void processEdit() {
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

        String id = getIntent().getStringExtra("pushId");

        Userdets userdets = new Userdets(dat, t, s, d, hr, c);
        if (id != null) {
            DatabaseReference updatedRef = databaseReference.child(id); // Reference to the updated data

            updatedRef.setValue(userdets).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Update.this, "Updated", Toast.LENGTH_SHORT).show();

                        // Update the local UI with the updated data
                        datt.setText(userdets.getDate());
                        tt.setText(userdets.getTime());
                        ss.setText(userdets.getSystolic());
                        dd.setText(userdets.getDiastolic());
                        hrr.setText(userdets.getHeartRate());
                        comm.setText(userdets.getComment());

                        finish();
                    } else {
                        Toast.makeText(Update.this, "Failed to update details: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (valueEventListener != null) {
            databaseReference.removeEventListener(valueEventListener);
        }
    }
}