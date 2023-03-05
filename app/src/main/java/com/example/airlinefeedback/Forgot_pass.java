package com.example.airlinefeedback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;

public class Forgot_pass extends AppCompatActivity {

    private EditText EnterNo;
    Button ButtonGetOTP;
    private ProgressBar sendingOTPProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        EnterNo = findViewById(R.id.EnterNo);
        ButtonGetOTP = findViewById(R.id.ButtonGetOTP);
        sendingOTPProgress = findViewById(R.id.progressBarSendingOTP);

        ButtonGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!EnterNo.getText().toString().trim().isEmpty()) {
                    if ((EnterNo.getText().toString().trim().length() == 10)) {

                        sendingOTPProgress.setVisibility(View.VISIBLE);
                        ButtonGetOTP.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + EnterNo.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                Forgot_pass.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        sendingOTPProgress.setVisibility(View.GONE);
                                        ButtonGetOTP.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        sendingOTPProgress.setVisibility(View.GONE);
                                        ButtonGetOTP.setVisibility(View.VISIBLE);
                                        Toast.makeText(Forgot_pass.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        sendingOTPProgress.setVisibility(View.GONE);
                                        ButtonGetOTP.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent(getApplicationContext(), VerifyOTP.class);
                                        intent.putExtra("mobile", EnterNo.getText().toString());
                                        intent.putExtra("backendOTP", backendOTP);
                                        startActivity(intent);
                                    }
                                }
                        );


                    } else {
                        Toast.makeText(Forgot_pass.this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Forgot_pass.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
