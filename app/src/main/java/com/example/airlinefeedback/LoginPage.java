package com.example.airlinefeedback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.airlinefeedback.bean.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.BreakIterator;
import java.util.regex.Pattern;

public class LoginPage extends AppCompatActivity {

    private Button btLogin;
    TextView tvSignUp;
    TextView tvForgotPass;
    private EditText etEmail;
    private EditText etPassword;
    static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");


    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btLogin = (Button) findViewById(R.id.btLogin);
        tvForgotPass = (TextView) findViewById(R.id.tvForgotPass);
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        firebaseAuth = FirebaseAuth.getInstance();
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent g=new Intent(LoginPage.this,SignUp.class);
                startActivity(g);
            }
        });

        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent z=new Intent(LoginPage.this,Forgot_pass.class);
                startActivity(z);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean emailFlag = validateInputEmail(etEmail);
                boolean passwordFlag = validateInputPassword(etPassword);
                if(Boolean.TRUE.equals(emailFlag) && Boolean.TRUE.equals(passwordFlag)){
                    loginUser();
                }
            }
        });
    }

    private void loginUser() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(LoginPage.this,SelectAirline.class));
                }else{
                    Toast.makeText(LoginPage.this, "Login Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateInputPassword(EditText etPassword) {
        String passwordInput = etPassword.getText().toString().trim();
        if(passwordInput.isEmpty()){
            etPassword.setError("Field should not be empty!");
            return false;
        }
        else if(!PASSWORD_PATTERN.matcher(passwordInput).matches()){
            etPassword.setError("Password is too weak!");
            return false;
        }
        return true;
    }

    private boolean validateInputEmail(EditText etEmail) {
        String email = etEmail.getText().toString();

        if(email.isEmpty() && email.length()==0){
            etEmail.setError("Field should not be empty!");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Please enter a valid email address!");
            return false;
        }
        return true;
    }
}





