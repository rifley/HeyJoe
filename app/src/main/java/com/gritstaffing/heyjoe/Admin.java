package com.gritstaffing.heyjoe;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin extends Activity implements View.OnClickListener{
    private EditText mUserEmail;
    private EditText mUserPassword;
    private Button mButton;
    private ProgressDialog mAuthProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        createAuthProgressDialog();


        mUserEmail = (EditText) findViewById(R.id.email);
        mUserPassword = (EditText) findViewById(R.id.password);
        mButton = (Button) findViewById(R.id.verifyButton);
        mButton.setOnClickListener(this);


    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Heya JoJo");
        mAuthProgressDialog.setMessage("Authenticating...");
        mAuthProgressDialog.setCancelable(false);
    }


    @Override
    public void onClick(View v) {
        if(v == mButton) {
            mAuthProgressDialog.show();
            FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
            DatabaseReference mRef = mDatabase.getReference();

            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            String mEmail = String.valueOf(mUserEmail.getText());
            String mPassword = String.valueOf(mUserPassword.getText());


            mAuth.signInWithEmailAndPassword(mUserEmail.getText().toString(), mUserPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {

                                mAuthProgressDialog.dismiss();
                                goToUserData();

                            } else {

                            }
                        }
                    });

        }
    }

    public void goToUserData() {
        Intent intent = new Intent(this, adminView.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Current User", String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getEmail()));
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            Intent intent = new Intent(this, adminView.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            this.finish();
        } else {
            Log.e("Admin", "User Not Found");
        }
    }
}
