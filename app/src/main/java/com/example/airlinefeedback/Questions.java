package com.example.airlinefeedback;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.airlinefeedback.bean.UserInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

public class Questions extends AppCompatActivity {

    private Spinner spinner;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    private Spinner spinner5;
    private Spinner spinner6;
    private RatingBar ratingbar;
    private Button submit;
    private EditText etText8;
   private int checkOp1 = 0;
    private int checkOp2 = 0;
    private int checkOp3 = 0;
    private int checkOp4 = 0;
    private int checkOp5 = 0;
    private int checkOp6 = 0;
    private int checkOp7 = 0;

    String[] options = {"-- Select --","Excellent", "Good", "Average", "Needs improvement"};
    String[] options2 = {"-- Select --","Excellent", "Good", "Average", "Needs improvement"};
    String[] options3 = {"-- Select --","Before time", "On time", "Delayed by less than 30 mins", "Delayed by more than 30 mins"};
    String[] options4 = {"-- Select --","Excellent", "Good", "Average", "Needs improvement"};
    String[] options5 = {"-- Select --","Excellent", "Good", "Average", "Needs improvement"};
    String[] options6 = {"-- Select --","Excellent", "Good", "Average", "Needs improvement"};
    String[] options7 = {"-- Select --","Fast", "Slow", "Very slow"};
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        ratingbar = (RatingBar) findViewById(R.id.ratingbar);
        etText8 = (EditText) findViewById(R.id.etText8);
        submit = (Button) findViewById(R.id.submit);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Questions.this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(++checkOp1>1){
                    String options = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(Questions.this, options, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner1 = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Questions.this, android.R.layout.simple_spinner_item, options2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter2);
        spinner1.setSelection(0);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(++checkOp2>1){
                    String options2 = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(Questions.this, options2, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(Questions.this, android.R.layout.simple_spinner_item, options3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter3);
        spinner2.setSelection(0);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(++checkOp3>1){
                    String options3 = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(Questions.this, options3, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner3 = findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(Questions.this, android.R.layout.simple_spinner_item, options4);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter4);
        spinner3.setSelection(0);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(++checkOp4>1) {
                    String options4 = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(Questions.this, options4, Toast.LENGTH_SHORT).show();

                } }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner4 = findViewById(R.id.spinner4);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(Questions.this, android.R.layout.simple_spinner_item, options5);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter5);
        spinner4.setSelection(0);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(++checkOp5>1) {
                    String options5 = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(Questions.this, options5, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner5 = findViewById(R.id.spinner5);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(Questions.this, android.R.layout.simple_spinner_item, options6);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter6);
        spinner5.setSelection(0);
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(++checkOp6>1) {
                    String options6 = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(Questions.this, options6, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner6 = findViewById(R.id.spinner6);
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(Questions.this, android.R.layout.simple_spinner_item, options7);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter7);
        spinner6.setSelection(0);
        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(++checkOp7>1) {
                    String options7 = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(Questions.this, options7, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                Toast.makeText(Questions.this, String.valueOf(rating), Toast.LENGTH_SHORT).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (spinner.getSelectedItem().toString().equals("-- Select --")) {
                    Toast.makeText(Questions.this, "Please answer all questions!",
                            Toast.LENGTH_SHORT).show();
                }
                Intent v = new Intent(Questions.this, Final_screen.class);
                startActivity(v);
                }
            });
        }
    }






