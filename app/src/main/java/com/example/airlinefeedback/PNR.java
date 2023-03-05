package com.example.airlinefeedback;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.airlinefeedback.bean.AirlineInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PNR extends AppCompatActivity {

    private Button ProceedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pnr);
        ProceedButton = (Button) findViewById(R.id.ProceedButton);
        ProceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent p = new Intent(PNR.this,Questions.class);
                startActivity(p);
            }
        });
    }
}