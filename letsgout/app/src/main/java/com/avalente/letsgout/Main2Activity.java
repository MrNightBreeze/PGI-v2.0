package com.avalente.letsgout;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {
    TextView txtStatus;
    LoginButton login_button;
    CallbackManager callbackManager;

    public static final String USER_ID = "userid";

    public static String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.setApplicationId("2419283054962531");
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        initializeControls();
        loginWithFB();
    }


    private void initializeControls() {
        callbackManager = CallbackManager.Factory.create();
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        login_button = (LoginButton) findViewById(R.id.login_button);

    }


    private void loginWithFB() {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //txtStatus.setText("Login Success\n"+loginResult.getAccessToken());
                String id = loginResult.getAccessToken().getUserId();

                userid = id;
                // chama um intent e redireciona para a MainActivity e passa o id do user
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                txtStatus.setText("Login Cancelled.");
            }

            @Override
            public void onError(FacebookException error) {
                txtStatus.setText("Login Error: "+error.getMessage());
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }



}
