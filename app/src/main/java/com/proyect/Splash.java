package com.proyect;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splash extends AppCompatActivity {

    //Declaro un ImageView y un AnimationDrawable como variables de clase

    public ImageView ivSplash;
    public AnimationDrawable animation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Métodos necesarios para las activities
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Se fuerza a la aplicación a mostrarse en vertical
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Inicializamos iv y le asigno un background
        ivSplash = findViewById(R.id.ivsplash);

        //Le asignamos como recurso de background la animación
        ivSplash.setBackgroundResource(R.drawable.animation_splash);


        //inicializamos el AnimationDrawable con el background de ivSplash y la inicio
        animation = (AnimationDrawable) ivSplash.getBackground();

        animation.start();

        //Creamos un manejador para que se inicie la activity main una vez termine la splash
        //Aquí hacemos que el hilo inicie la activity main y le decimos que espere 1600ms
        //para ello, que es el tiempo que tarda la animación en completarse

        new Handler().postDelayed(new Runnable()
        {

            @Override
            public void run()
            {
                //Paramos la animación
                animation.stop();

                //Creamos un intent que será la activity main pasandole el contexto de la splash
                //y la activity que va a ser
                Intent i = new Intent(Splash.this, MainActivity.class);

                //Iniciamos la activity
                startActivity(i);

                //Finalizamos la activity para que no vuelva a saltar
                finish();
            }
        }, 1600);

    }
}