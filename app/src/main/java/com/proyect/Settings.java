package com.proyect;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity
{
    //Declaro el listview como variable de clase
    ListView lv_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        //inicializo el listview
        lv_settings = (ListView)findViewById(R.id.lv_settings);

        //Cogemos el array que hemos creado en strings y lo metemos en una array local
        String [] settings_arr = getResources().getStringArray(R.array.array_config);

        //Creamos un adaptador de String pasándole el contexto, un layout y el array
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, settings_arr);

        //Setteamos el listview con nuestro adaptador
        lv_settings.setAdapter(adapter);

        //Añadimos este método para asegurarnos que cuando se pulse atrás nos cierre esta activity,
        //para que no consuma recursos inncesariamente
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true)
        {
            @Override
            public void handleOnBackPressed()
            {
                finish();
            }
        });
    }




}