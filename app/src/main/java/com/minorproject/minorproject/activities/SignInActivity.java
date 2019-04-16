package com.minorproject.minorproject.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.minorproject.minorproject.R;

public class SignInActivity extends Activity implements View.OnClickListener {

    FirebaseAuth mAuth;
    EditText editTextEmail;
    EditText editTextPassword;
    Button btnPhone;
    ProgressBar progressBar;



    private static final int GOOGLE_SIGN_IN_CODE = 212;
    GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        mAuth=FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        btnPhone=(Button) findViewById(R.id.btnPhone);
        editTextEmail=(EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);

        findViewById(R.id.textViewSignup).setOnClickListener(this);
        findViewById(R.id.buttonSignIn).setOnClickListener(this);

        findViewById(R.id.btnPhone).setOnClickListener(this);
        findViewById(R.id.textViewForgot).setOnClickListener(this);
        findViewById(R.id.textViewBack).setOnClickListener(this);

        findViewById(R.id.textViewForgotButton).setVisibility(View.GONE);
        findViewById(R.id.textViewBack).setVisibility(View.GONE);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).build();
        googleSignInClient = GoogleSignIn.getClient(this,gso);

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(SignInActivity.this,NavActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            googleSignInClient.signOut();
        }else{
            findViewById(R.id.google_sign_in).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =googleSignInClient.getSignInIntent();
                    startActivityForResult(intent,GOOGLE_SIGN_IN_CODE);
                }
            });
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==GOOGLE_SIGN_IN_CODE){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);

            } catch (ApiException e) {
                e.printStackTrace();
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        AuthCredential credential= GoogleAuthProvider.getCredential(account.getIdToken(),null);
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(SignInActivity.this,NavActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    googleSignInClient.signOut();
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void userLogin(){
        String email =editTextEmail.getText().toString().trim();
        String password =editTextPassword.getText().toString().trim();

        if(email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;

        }
        if(password.isEmpty()){
            editTextPassword.setError("Password is empty");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length()<6){
            editTextPassword.setError("Password should be minimum 6 ");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(SignInActivity.this,NavActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }

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

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.textViewBack:
                bck();
                break;
            case R.id.textViewSignup:
                Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.textViewForgot:
                forgotPassword();
                break;
            case R.id.buttonSignIn:
                userLogin();
                break;
            case R.id.btnPhone:
                Intent i = new Intent(SignInActivity.this,SignInPhone.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                break;

        }

    }

    private void forgotPassword() {

        editTextPassword.setVisibility(View.GONE);
        btnPhone.setVisibility(View.GONE);
        findViewById(R.id.textViewForgot).setVisibility(View.GONE);
        findViewById(R.id.textViewSignup).setVisibility(View.GONE);
        findViewById(R.id.buttonSignIn).setVisibility(View.GONE);
        findViewById(R.id.google_sign_in).setVisibility(View.GONE);
        findViewById(R.id.textViewBack).setVisibility(View.VISIBLE);
        findViewById(R.id.textViewForgotButton).setVisibility(View.VISIBLE);

        findViewById(R.id.textViewForgotButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();

                if(email.isEmpty()){
                    editTextEmail.setError("Email is required");
                    editTextEmail.requestFocus();
                    return;
                }

                mAuth.sendPasswordResetEmail(email);
                Toast.makeText(getApplicationContext(),"Check Your email for reset link",Toast.LENGTH_SHORT).show();
                editTextPassword.setVisibility(View.VISIBLE);
                btnPhone.setVisibility(View.VISIBLE);
                findViewById(R.id.textViewForgot).setVisibility(View.VISIBLE);
                findViewById(R.id.textViewSignup).setVisibility(View.VISIBLE);
                findViewById(R.id.buttonSignIn).setVisibility(View.VISIBLE);
                findViewById(R.id.google_sign_in).setVisibility(View.VISIBLE);
                findViewById(R.id.textViewBack).setVisibility(View.GONE);
                findViewById(R.id.textViewForgotButton).setVisibility(View.GONE);
            }
        });


    }

    private void bck(){
        editTextPassword.setVisibility(View.VISIBLE);
        btnPhone.setVisibility(View.VISIBLE);
        findViewById(R.id.textViewForgot).setVisibility(View.VISIBLE);
        findViewById(R.id.textViewSignup).setVisibility(View.VISIBLE);
        findViewById(R.id.buttonSignIn).setVisibility(View.VISIBLE);
        findViewById(R.id.google_sign_in).setVisibility(View.VISIBLE);
        findViewById(R.id.textViewBack).setVisibility(View.GONE);
        findViewById(R.id.textViewForgotButton).setVisibility(View.GONE);
    }
}
