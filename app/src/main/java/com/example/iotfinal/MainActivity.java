package com.example.iotfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.iotfinal.model.Leds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private ImageView imgLed1;
    private ImageView imgLed2;
    private Button btnLedOn1;
    private Button btnLedOn2;
    private Button btnLedOff1;
    private Button btnLedOff2;
    private Leds leds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgLed1 = (ImageView)findViewById(R.id.imgLed1);
        imgLed2 = (ImageView)findViewById(R.id.imgLed2);
        btnLedOn1 = (Button)findViewById(R.id.btnLedOn1);
        btnLedOn2 = (Button)findViewById(R.id.btnLedOn2);
        btnLedOff1 = (Button)findViewById(R.id.btnLedOff1);
        btnLedOff2 = (Button)findViewById(R.id.btnLedOff2);

        // Conectar a la base de datos
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Utilizar la referencia "leds"
        final DatabaseReference myRef = database.getReference("leds");

        // Leer de la base de datos
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Capturando el valor de la referencia "saludo"
                leds = dataSnapshot.getValue(Leds.class);
                if(leds.led1.equals("1")){
                    imgLed1.setImageResource(R.drawable.led_on_blue);
                }else{
                    imgLed1.setImageResource(R.drawable.led_off);
                }

                if(leds.led2.equals("1")){
                    imgLed2.setImageResource(R.drawable.led_on_orange);
                }else{
                    imgLed2.setImageResource(R.drawable.led_off);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Enviar mensaje de error
            }
        });

        btnLedOn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leds.led1 = "1";
                myRef.setValue(leds);
            }
        });
        btnLedOff1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leds.led1 = "0";
                myRef.setValue(leds);
            }
        });
        btnLedOn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leds.led2 = "1";
                myRef.setValue(leds);
            }
        });
        btnLedOff2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leds.led2 = "0";
                myRef.setValue(leds);
            }
        });
    }
}
