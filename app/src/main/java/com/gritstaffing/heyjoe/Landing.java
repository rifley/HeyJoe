package com.gritstaffing.heyjoe;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Landing extends Activity implements View.OnClickListener{
    private FirebaseDatabase database;
    private DatabaseReference users;
    private Button mSoonButton;
    private Button mNowButton;
    private EditText mUserName;
    private TextView mAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference users = database.getReference("users");

        mSoonButton = (Button) findViewById(R.id.soonButton);
        mNowButton = (Button) findViewById(R.id.nowButton);
        mUserName = (EditText) findViewById(R.id.userName);
        mAdmin = (TextView) findViewById(R.id.admin);
        mSoonButton.setOnClickListener(this);
        mNowButton.setOnClickListener(this);
        mAdmin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mSoonButton) {
            FirebaseDatabase databaseNow = FirebaseDatabase.getInstance();
            DatabaseReference mRef = databaseNow.getReference("soon");
            mRef.push().setValue(mUserName.getText().toString());
        } else if (v == mNowButton) {
            FirebaseDatabase databaseNow = FirebaseDatabase.getInstance();
            DatabaseReference mRef = databaseNow.getReference("now");
            mRef.push().setValue(mUserName.getText().toString());
        } else if (v == mAdmin) {
            Intent intent = new Intent(this, Admin.class);
            startActivity(intent);
        }
    }
}



//    Toast toast = Toast.makeText(getApplicationContext(), "Hey Buddy", Toast.LENGTH_SHORT);
//            toast.show();