package com.example.iski_.appsemana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private TextView  texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

         loginButton = (LoginButton)findViewById(R.id.login_button);
         texto = (TextView) findViewById(R.id.texto);

        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                texto.setText(loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {
                texto.setText("USer Cancel Request");
            }

            @Override
            public void onError(FacebookException error) {
                texto.setText("Error De conexion");
            }
        });
    }

}
