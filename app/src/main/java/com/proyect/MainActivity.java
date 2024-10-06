package com.proyect;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/*
* Creamos MainActivity con implementación de View.OnClickListener para mejorar la gestión del
* método onClick
* */

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    //Declaramos tres variables de imagen que funcionarán como botones

    ImageView ivCalendar;
    ImageView ivToday;
    ImageView ivNotes;

    ArrayList<String> arrayToday;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //¡Método necesario para que los botones en pantalla no tapen la applicación!
        //¡no borrar!
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Creamos una toolbar para el main de la aplicación
        Toolbar toolbar = (Toolbar)findViewById(R.id.tb_main);

        //Le indicamos a la activity que use la toolbar que hemos creado
        setSupportActionBar(toolbar);

        //Inicializamos los imageview que funcionarán como botones
        ivCalendar = findViewById(R.id.btn_events);
        ivToday = findViewById(R.id.btn_today);
        ivNotes = findViewById(R.id.btn_notas);

        //les ponemos como recurso una imagen
        //(yosef) he cargado más en el proyecto pero no he tenidotiempo para cambiar el tamaño a todas
        //lo haré el proximo que pueda dedicarle tiempo
        ivCalendar.setBackgroundResource(R.drawable.btn_calendar_colored80x80);
        ivToday.setBackgroundResource(R.drawable.btn_today_nocolor80x80);
        ivNotes.setBackgroundResource(R.drawable.btn_notes_nocolor80x80);

        //Declaramos un fragment y su manager para ser el principal al abrir la app
        //que será el fragment de calendario
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CalendarFragment calendarFragment = new CalendarFragment();
        fragmentTransaction.add(R.id.ll_fragments_main, calendarFragment);
        fragmentTransaction.commit();

        //le ponemos a cada botón un escuchador de eventos
        ivCalendar.setOnClickListener(this);
        ivToday.setOnClickListener(this);
        ivNotes.setOnClickListener(this);

        arrayToday = new ArrayList<String>();
    }

    /*
     * Orverrideamos el método onClick para dar funcionalidad a los iv que funcionan como botones
     *
    */

    @Override
    public void onClick(View view)
    {
        //Creamos un fragment
        Fragment fragment;

        //Creamos un bundle para pasar datos desde la main
        Bundle bundle = new Bundle();

        //Pasamos al bundle un Arraylist de String
        bundle.putStringArrayList("arrayToday", arrayToday);

        //Dependiendo del botón que pulsemos inicializamos el fragment correspondiente
        //y se cambia el color de los botones
        if (view.getId()==R.id.btn_today)
        {
            fragment = new TodayFragment();

            ivCalendar.setBackgroundResource(R.drawable.btn_calendar_nocolor80x80);
            ivToday.setBackgroundResource(R.drawable.btn_today_colored80x80);
            ivNotes.setBackgroundResource(R.drawable.btn_notes_nocolor80x80);

        }
        else if (view.getId()==R.id.btn_notas)
        {
            fragment = new NotesFragment();

            ivCalendar.setBackgroundResource(R.drawable.btn_calendar_nocolor80x80);
            ivToday.setBackgroundResource(R.drawable.btn_today_nocolor80x80);
            ivNotes.setBackgroundResource(R.drawable.btn_notes_colored80x80);
        }
        else
        {
            fragment = new CalendarFragment();

            ivCalendar.setBackgroundResource(R.drawable.btn_calendar_colored80x80);
            ivToday.setBackgroundResource(R.drawable.btn_today_nocolor80x80);
            ivNotes.setBackgroundResource(R.drawable.btn_notes_nocolor80x80);
        }

        //Le pasamos como argumentos al fragment nuestro bundle
        fragment.setArguments(bundle);

        //Hacemos la transición del fagment actual al nuevo fragment
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.ll_fragments_main, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /*
     * Realizamos un override de los métodos necesarios para crear el menú
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //le decimos al menú que la crearse y use el layout de la carpeta menu

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    /*
     * Este método sirve para darle funcionalidad a los item del menú
     */

    /*--(Yosef): podemos incluir otros botones como exportar eventos,
     *formato imprimible y cosas así--*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //Declaramos un intent para iniciar otra activity al pulsar el item de menú
        Intent i;

        if(item.getItemId() == R.id.itemConfig)
        {
            //Inicializamos el intent e iniciamos la activity
            i = new Intent(getApplicationContext(), Settings.class);

            startActivity(i);

            //--OJO A LA MAIN NO LE PONGAIS EL MÉTODO finish()
        }

        return true;
    }



    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}