package com.example.iotfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Sensor1 extends AppCompatActivity {
    private Button atras_sensores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor1);

        atras_sensores= findViewById(R.id.atras_sensor);
        atras_sensores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sensor1.this, Principal.class);
                startActivity(intent);

            }
        });

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("temp");
            final TextView textSensorSimulado1=findViewById(R.id.sensor_temp);

        DatabaseReference myRef2 = database.getReference("hum");
        final TextView textSensorSimulado2=findViewById(R.id.sensor_hum);
//        myRef.setValue("Hello, World!");
            // Read from the database
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    int value = dataSnapshot.getValue(Integer.class);
                    textSensorSimulado1.setText(""+value);

                }


                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                }
            });

            myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int value = dataSnapshot.getValue(Integer.class);
                textSensorSimulado2.setText(""+value);

            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

    }
}