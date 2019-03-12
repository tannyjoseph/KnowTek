package com.example.knowtek;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RiderActivity extends AppCompatActivity {


    Button riderSignup;

    EditText email, pass;

    ProgressDialog pro;

    FirebaseAuth auth;

    FirebaseAuth.AuthStateListener authStateListener;

    DatabaseReference driverDatabase, riderDatabase;
    StorageReference mStorageref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider);

        riderSignup = (Button)findViewById(R.id.signupRider);

        pro = new ProgressDialog(this);

        email = (EditText) findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.password);


        auth = FirebaseAuth.getInstance();


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = auth.getCurrentUser();




                }


            };

        auth.addAuthStateListener(authStateListener);

        driverDatabase = FirebaseDatabase.getInstance().getReference().child("Drivers");
        riderDatabase = FirebaseDatabase.getInstance().getReference().child("Riders");

        mStorageref = FirebaseStorage.getInstance().getReference();


        riderSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pro.setTitle("Logging in");
                pro.setMessage("Please wait..");
                pro.show();

                final String em, pa;

                em = email.getText().toString().trim();
                pa = pass.getText().toString().trim();

                if (!TextUtils.isEmpty(em) && !TextUtils.isEmpty(pa)) {

                    auth.createUserWithEmailAndPassword(em, pa)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        pro.dismiss();

                                        Intent home = new Intent(RiderActivity.this, RiderHomeActivity.class);
                                        home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(home);

                                        riderDatabase.child("Email").setValue(em);


                                    } else {
                                        Log.i("info", "nuh uh");
                                    }

                                }

                            });

                }

            }



        });
    }
}
