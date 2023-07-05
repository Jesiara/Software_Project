package com.bawp.heartmonitor_software_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView detailsRV;
    private FloatingActionButton addFAB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<Userdets> userdetsArrayList;
    private HomeAdapter homeAdapter;
    private Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        detailsRV = findViewById(R.id.detailsRV);
        addFAB = findViewById(R.id.addDetails);



        String mail = getIntent().getStringExtra("email");
        if (mail == null) {
            Toast.makeText(this, "Error: Email not found", Toast.LENGTH_SHORT).show();
           // finish();
            return;
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        userdetsArrayList = new ArrayList<>();
        homeAdapter = new HomeAdapter(userdetsArrayList, this, mail );

        databaseReference = firebaseDatabase.getReference("Details");//.child(mail);

        detailsRV.setLayoutManager(new LinearLayoutManager(this));
        detailsRV.setAdapter(homeAdapter);

        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, InsertActivity.class);
                intent.putExtra("email", mail);
                startActivity(intent);
            }
        });

        getAllDetails();
    }

    private void getAllDetails() {
        userdetsArrayList.clear();

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, String previousChildName) {
                HashMap<String, Object> userDetailsMap = snapshot.getValue(new GenericTypeIndicator<HashMap<String, Object>>() {});
                if (userDetailsMap != null) {
                    String date = (String) userDetailsMap.get("date");
                    String time = (String) userDetailsMap.get("time");
                    String systolic = (String) userDetailsMap.get("systolic");
                    String diastolic = (String) userDetailsMap.get("diastolic");
                    String heartRate = (String) userDetailsMap.get("heartRate");
                    String comment = (String) userDetailsMap.get("comment");

                    Userdets user = new Userdets(date, time, systolic, diastolic, heartRate, comment);
                    userdetsArrayList.add(user);
                    homeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, String previousChildName) {
                // Handle the changed data
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                // Handle the removed data
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, String previousChildName) {
                // Handle the moved data
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error
            }
        });
    }


}
