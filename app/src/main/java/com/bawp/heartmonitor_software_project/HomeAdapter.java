package com.bawp.heartmonitor_software_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private ArrayList<Userdets> userdetsArrayList;
    private Context context;
    private String email;

    public HomeAdapter(ArrayList<Userdets> userdetsArrayList, Context context, String email) {
        this.userdetsArrayList = userdetsArrayList;
        this.context = context;
        this.email = email;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.details_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Userdets userdets = userdetsArrayList.get(position);
        holder.dat.setText("Date: "+userdets.getDate());
        holder.t.setText("Time: "+userdets.getTime());
        holder.s.setText("Systolic: "+userdets.getSystolic());
        holder.d.setText("Diastolic: "+userdets.getDiastolic());
        holder.hr.setText("Heartrate: "+userdets.getHeartRate());
        holder.c.setText("Comment: "+userdets.getComment());
    }

    @Override
    public int getItemCount() {
        return userdetsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dat, t, s, d, hr, c;
        Button deleteButton,editbutton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dat = itemView.findViewById(R.id.dets_dat);
            t = itemView.findViewById(R.id.dets_t);
            s = itemView.findViewById(R.id.dets_s);
            d = itemView.findViewById(R.id.dets_d);
            hr = itemView.findViewById(R.id.dets_hr);
            c = itemView.findViewById(R.id.dets_com);

            deleteButton = itemView.findViewById(R.id.btndel);
            editbutton = itemView.findViewById(R.id.btnedit);

            editbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Userdets user = userdetsArrayList.get(position);
                        String date = user.getDate();
                        String time = user.getTime();
                        String systolic = user.getSystolic();
                        String diastolic = user.getDiastolic();
                        String heartRate = user.getHeartRate();
                        String comment = user.getComment();

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Details");//.child(email);

                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    Userdets userdets = dataSnapshot.getValue(Userdets.class);
                                    if (userdets != null && userdets.isEqual(date, time, systolic, diastolic, heartRate, comment)) {
                                        String pushId = dataSnapshot.getKey();
                                        if (pushId != null) {
                                            // Pass the pushId to the UpdateActivity
                                            Intent intent = new Intent(context, Update.class);
                                            intent.putExtra("pushId", pushId);
                                            intent.putExtra("mail", email);

                                            context.startActivity(intent);
                                        }
                                        break;
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                // Handle the error
                            }
                        });
                    }
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Userdets user = userdetsArrayList.get(position);
                        String date = user.getDate();
                        String time = user.getTime();
                        String systolic = user.getSystolic();
                        String diastolic = user.getDiastolic();
                        String heartRate = user.getHeartRate();
                        String comment = user.getComment();

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Details");//.child(email);

                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    Userdets userdets = dataSnapshot.getValue(Userdets.class);
                                    if (userdets != null && userdets.isEqual(date, time, systolic, diastolic, heartRate, comment)) {
                                        String pushId = dataSnapshot.getKey();
                                        if (pushId != null) {
                                            databaseReference.child(pushId).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        userdetsArrayList.remove(position);
                                                        notifyItemRemoved(position);
                                                        notifyItemRangeChanged(position, userdetsArrayList.size());
                                                    } else {
                                                        Toast.makeText(context, "Failed to delete item", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                            break;
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                // Handle the error
                            }
                        });
                    }
                }
            });
        }
    }
}
