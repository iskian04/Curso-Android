package com.example.iski_.appsemana;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
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
                Intent intent = new Intent(getApplicationContext(),NavagitionActivity.class);
                startActivity(intent);
               // texto.setText(loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),"Cancelaste el Login",Toast.LENGTH_LONG);
                //texto.setText("USer Cancel Request");
            }

            @Override
            public void onError(FacebookException error) {

                Toast.makeText(getApplicationContext(),"Error Conexion",Toast.LENGTH_LONG);
                //texto.setText("Error De conexion");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

        private boolean estalogueado(){

            return AccessToken.getCurrentAccessToken() != null; // para crear un token para seguridad de la contraseña y valida si el usuario esta logueado
        }
}
