package com.example.airlinefeedback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOTP extends AppCompatActivity {

    private EditText getOTP;
    private TextView showNumber;
    private  Button verify;
    private String getBackendOTP;
    private ProgressBar verifyOTPProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        showNumber = findViewById(R.id.txtShowNumber);
        getOTP = findViewById(R.id.etOTP);
        verify = findViewById(R.id.btnVerify);
        verifyOTPProgress = findViewById(R.id.verifyOTPProgressBar);
        showNumber.setText(String.format("+91  %s",getIntent().getStringExtra("mobile")));
        getBackendOTP = getIntent().getStringExtra("backendOTP");
        verify.setOnClickListener(view -> {
            if(!getOTP.getText().toString().trim().isEmpty()){
                String userOTP = getOTP.getText().toString();
                if(getBackendOTP!=null){
                    verifyOTPProgress.setVisibility(View.VISIBLE);
                    verify.setVisibility(View.INVISIBLE);

                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            getBackendOTP,userOTP
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    verifyOTPProgress.setVisibility(View.GONE);
                                    verify.setVisibility(View.VISIBLE);
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(getApplicationContext(), SelectAirline.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(VerifyOTP.this,"Please enter correct OTP!",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }else{
                    Toast.makeText(this, "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(VerifyOTP.this, "Verifying OTP", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(VerifyOTP.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
            }
        });

            findViewById(R.id.tvResendOTP).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + getIntent().getStringExtra("mobile"),
                            60,
                            TimeUnit.SECONDS,
                            VerifyOTP.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {

                                    Toast.makeText(VerifyOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String newbackendOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                    getBackendOTP=newbackendOTP;
                                    Toast.makeText(VerifyOTP.this,"OTP sent successfully!",Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                }
            });
    }
}