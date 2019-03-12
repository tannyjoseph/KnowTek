package com.example.knowtek;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DriverActivity extends AppCompatActivity {

    EditText name, number, password, confirm;
    EditText email;
    Button signup;

    FirebaseAuth auth;

    FirebaseAuth.AuthStateListener authStateListener;

    DatabaseReference driverDatabase, riderDatabase;
    StorageReference mStorageref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        number = (EditText)findViewById(R.id.number);
        password = (EditText) findViewById(R.id.password);
        confirm = (EditText) findViewById(R.id.confirm);

        signup = (Button) findViewById(R.id.signupDriver);

        auth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                //Check user

                FirebaseUser user = auth.getCurrentUser();



            }
        };

        auth.addAuthStateListener(authStateListener);


        driverDatabase = FirebaseDatabase.getInstance().getReference().child("Drivers");
        riderDatabase = FirebaseDatabase.getInstance().getReference().child("Riders");

        mStorageref = FirebaseStorage.getInstance().getReference();





        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(DriverActivity.this, HomePageActivity.class);
                home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(home);

            }
        });
    }
}
