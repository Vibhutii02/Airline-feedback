package com.example.airlinefeedback;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.airlinefeedback.bean.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");
    static final Pattern PHONE = Pattern.compile("^[6-9]\\d{9}$");
    private Button btRegister;
    private EditText etName;
    private EditText etPhoneNo;
    private EditText etPassword;
    private EditText etReType;
    private EditText etSignupemail;
    private EditText etEmail;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etName = (EditText) findViewById(R.id.etName);
        etPhoneNo = (EditText) findViewById(R.id.etPhoneNo);
        etSignupemail = (EditText) findViewById(R.id.etSignupemail);
        etPassword = (EditText) findViewById(R.id.etSignUpPass);
        etReType = (EditText) findViewById(R.id.etRetype);
        etEmail = (EditText) findViewById(R.id.etSignupemail);
        btRegister = (Button) findViewById(R.id.btRegister);
        firebaseAuth = FirebaseAuth.getInstance();
        btRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                boolean nameFlag = validateInputName(etName);
                boolean phoneFlag = validatePhoneNo(etPhoneNo);
                boolean passwordFlag = validateInputPasswordAndRetypePassword(etPassword,etReType);

                if(Boolean.TRUE.equals(nameFlag) && Boolean.TRUE.equals(phoneFlag) && Boolean.TRUE.equals(passwordFlag)){
                    createUser();
                }
            }
        });
    }

    private void createUser() {

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                             startActivity(new Intent(SignUp.this, LoginPage.class));

                            Toast.makeText(SignUp.this, "Sign up successful",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                             Toast.makeText(SignUp.this, "Sign up unsuccessful",
                                    Toast.LENGTH_SHORT).show();
                         }
                    }
                });
    }

    private boolean validateInputName(EditText etName) {
        String name = etName.getText().toString();

        if (name.isEmpty() && name.length() == 0) {
            etName.setError("Field should not be empty!");
            return false;
        } else if (etName.length() < 8) {
            etName.setError("Name should be minimum 8 characters!");
            return false;
        }
        return true;
    }

    private boolean validatePhoneNo(EditText etPhoneNo) {

        String phoneInput = etPhoneNo.getText().toString().trim();


        if (phoneInput.isEmpty() && phoneInput.length()==0) {
            etPhoneNo.setError("Please enter mobile number");
            return false;
        } else if(!PHONE.matcher(phoneInput).matches()){
            etPhoneNo.setError("Please enter a valid phone number!");
            return false;
        }
        return true;
    }

    private boolean validateInputPasswordAndRetypePassword(EditText etEnter, EditText etReType) {
        String passwordInput = etEnter.getText().toString().trim();
        String passwordRetypeInput = etReType.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            etEnter.setError("Field should not be empty!");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            etEnter.setError("Password is too weak!");
            return false;
        }else if(!passwordInput.isEmpty() && passwordRetypeInput.isEmpty()){
            etReType.setError("Please enter Type Password!");
            return false;
        }else if(passwordInput.isEmpty() && !passwordRetypeInput.isEmpty()){
            etReType.setError("Please fill this field!");
            return false;
        }else if(!passwordInput.isEmpty() && !passwordRetypeInput.isEmpty() && !passwordRetypeInput.equals(passwordInput)){
            etReType.setError("Passwords are not matching!");
            return false;
        }
        return true;
    }

    private boolean validateInputemail(EditText etSignupemail) {
        String email = etSignupemail.getText().toString();

        if(email.isEmpty() && email.length()==0){
            etSignupemail.setError("Field should not be empty!");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etSignupemail.setError("Please enter a valid email address!");
            return false;
        }
        return true;
    }
}
 