package com.example.knowtek;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class DriverLogin extends AppCompatActivity {
    ImageView car;

    TextView drive;

    RelativeLayout relay1, relay2;

    EditText email, pass;

    Button ridersignup, login, driversignup, riderlogin;

    FirebaseAuth auth;

    FirebaseAuth.AuthStateListener authStateListener;

    ProgressDialog pro;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        car = (ImageView) findViewById(R.id.car);

        drive = (TextView)findViewById(R.id.driveby);

        relay1 = (RelativeLayout) findViewById(R.id.rellay1);
        relay2 = (RelativeLayout) findViewById(R.id.relLay2);

        pro = new ProgressDialog(this);

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);

        ridersignup = (Button)findViewById(R.id.ridersignup);
        driversignup = (Button)findViewById(R.id.driversignup);
        riderlogin = (Button)findViewById(R.id.riderlogin);
        login = (Button) findViewById(R.id.driverloginB);

        auth = FirebaseAuth.getInstance();


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = auth.getCurrentUser();

                if(user != null){



                    Intent home = new Intent(DriverLogin.this, DriverActivity.class);
                    home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(home);



                }


            }

        };

        auth.addAuthStateListener(authStateListener);

        ridersignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DriverLogin.this, RiderActivity.class));
            }
        });

        driversignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DriverLogin.this, DriverActivity.class));
            }
        });

        riderlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DriverLogin.this, MainActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pro.setTitle("Logging in");
                pro.setMessage("Please wait..");
                pro.show();


                loginuser();
            }

            private void loginuser() {

                String em, pa;

                em = email.getText().toString().trim();
                pa = pass.getText().toString().trim();

                if (!TextUtils.isEmpty(em) && !TextUtils.isEmpty(pa)) {

                    auth.signInWithEmailAndPassword(em, pa)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        pro.dismiss();



                                        Intent home = new Intent(DriverLogin.this, HomePageActivity.class);
                                        home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(home);



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
