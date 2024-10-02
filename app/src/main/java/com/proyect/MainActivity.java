package com.proyect;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Creamos una toolbar para el main de la aplicación
        Toolbar toolbar = (Toolbar)findViewById(R.id.tb_main);

        //Le indicamos a la aplicación que use la toolbar que hemos creado
        setSupportActionBar(toolbar);
    }

    /*
    * Realizamos un override de los métodos necesarios para crear el menú
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //le decimos al menú que la crearse, use el layout de la carpeta menu

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

}