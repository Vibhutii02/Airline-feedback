package com.example.airlinefeedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.example.airlinefeedback.bean.AirlineInfo;
import com.example.airlinefeedback.bean.UserInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SelectAirline extends AppCompatActivity {

    private ImageButton AirIndia;
    private ImageButton Indigo;
    private ImageButton Vistara;
    private ImageButton GoAir;
    private ImageButton SpiceJet;
    private ImageButton AirAsia;
    private Button btnLogout;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_airline);

        AirIndia = (ImageButton) findViewById(R.id.AirIndia);
        Indigo = (ImageButton) findViewById(R.id.Indigo);
        Vistara = (ImageButton) findViewById(R.id.Vistara);
        GoAir = (ImageButton) findViewById(R.id.GoAir);
        SpiceJet = (ImageButton) findViewById(R.id.SpiceJet);
        AirAsia = (ImageButton) findViewById(R.id.AirAsia);
        btnLogout = findViewById(R.id.btnLogout);
        firebaseAuth = FirebaseAuth.getInstance();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(SelectAirline.this,LoginPage.class));
            }
        });

        AirIndia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(SelectAirline.this,PNR.class);
                startActivity(a);
            }
        });

        Indigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(SelectAirline.this,PNR.class);
                startActivity(b);
            }
        });

        Vistara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(SelectAirline.this,PNR.class);
                startActivity(c);
            }
        });

        GoAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent d = new Intent(SelectAirline.this,PNR.class);
                startActivity(d);
            }
        });

        SpiceJet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent e = new Intent(SelectAirline.this,PNR.class);
                startActivity(e);
            }
        });

        AirAsia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent f = new Intent(SelectAirline.this,PNR.class);
                startActivity(f);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseAuth1 = firebaseAuth.getCurrentUser();
        if(firebaseAuth1==null){
            startActivity(new Intent(SelectAirline.this,LoginPage.class));
        }
    }
}