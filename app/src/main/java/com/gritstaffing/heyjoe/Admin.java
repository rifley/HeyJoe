package com.gritstaffing.heyjoe;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin extends Activity implements View.OnClickListener{
    private EditText mUserEmail;
    private EditText mUserPassword;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mUserEmail = (EditText) findViewById(R.id.email);
        mUserPassword = (EditText) findViewById(R.id.password);
        mButton = (Button) findViewById(R.id.verifyButton);
        mButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == mButton) {
            FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
            DatabaseReference mRef = mDatabase.getReference();
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(mUserEmail.getText().toString(), mUserPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                mUserPassword.setVisibility(View.INVISIBLE);
                                mUserEmail.setVisibility(View.INVISIBLE);
                                mButton.setVisibility(View.INVISIBLE);
                            } else {
                                Log.i("Log-in Failure", task.getException().getMessage());
                            }
                        }
                    });

        }
    }
}
