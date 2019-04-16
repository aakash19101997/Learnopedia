package com.minorproject.minorproject.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.minorproject.minorproject.R;

import java.util.concurrent.TimeUnit;

public class SignInPhone extends Activity {
    EditText phoneNumber,codeReceived;
    Button verificationButton,buttonSignIn;
    FirebaseAuth mAuth;
    TextView textView,back;
    String codeSent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_phone);


        phoneNumber =(EditText) findViewById(R.id.phoneNumber);
        codeReceived=(EditText) findViewById(R.id.codeReceived);
        mAuth = FirebaseAuth.getInstance();
        verificationButton= (Button) findViewById(R.id.verificationButton);
        buttonSignIn=(Button) findViewById(R.id.buttonSignIn);

        textView=(TextView)findViewById(R.id.tv1);
        back = (TextView)findViewById(R.id.back);


        codeReceived.setVisibility(View.GONE);
        buttonSignIn.setVisibility(View.GONE);

        back.setVisibility(View.GONE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificationButton.setVisibility(View.VISIBLE);
                phoneNumber.setVisibility(View.VISIBLE);
                codeReceived.setVisibility(View.GONE);
                buttonSignIn.setVisibility(View.GONE);
                back.setVisibility(View.GONE);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInPhone.this,SignInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        verificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getVerificationCode();
                codeReceived.setVisibility(View.VISIBLE);
                buttonSignIn.setVisibility(View.VISIBLE);
                back.setVisibility(View.VISIBLE);
                verificationButton.setVisibility(View.GONE);
                phoneNumber.setVisibility(View.GONE);

            }
        });
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signIn();

            }
        });




    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to exit")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }
    private void getVerificationCode(){
        String number = phoneNumber.getText().toString();
        String code = codeReceived.getText().toString();

        if(number.isEmpty()){

            phoneNumber.setError("Phone Number is required");
            phoneNumber.requestFocus();
            return;
        }


        if(number.length()<10){
            phoneNumber.setError("Phone Number should be less than 10 ");
            phoneNumber.requestFocus();
            return;
        }


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks


        }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {


        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSent = s;


        }

    };

    private void signIn(){

        String code= codeReceived.getText().toString().trim();

        if(code.isEmpty()){
            codeReceived.setError("Code is required");
            codeReceived.requestFocus();
            return;
        }

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
        signInWithPhoneAuthCredential(credential);

    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignInPhone.this,NavActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(getApplicationContext(),"Wrong code Entered",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }


}
