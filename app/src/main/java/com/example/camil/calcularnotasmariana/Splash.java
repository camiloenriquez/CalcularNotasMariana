package com.example.camil.calcularnotasmariana;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

/**
 * sentencia para que la ventana Splash demore 3 segundos y luego se cierre pasando a la pagina principal.
 */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this,MainActivity.class);
                startActivity(intent);
            }
        },3000);


    }
}
