package com.proyect;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodayFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView tvGrettingsToday;
    private TextView tvDateToday;
    private SwipeRefreshLayout srlToday;

    //Creamos un ListView donde irán las tareas del día, así como el arraylist
    //donde irán todas las tareas listadas
    ListView lvToday;
    ArrayList<String> arrayToday;
    ArrayAdapter<String> adapter;

    public TodayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TodayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TodayFragment newInstance(String param1, String param2) {
        TodayFragment fragment = new TodayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        //Inicializamos textviews para poner un saludo y otro para mostrar el día de hoy

        tvGrettingsToday = view.findViewById(R.id.tv_greetings_today);
        tvDateToday = view.findViewById(R.id.tv_date_today);

        //Le damos formato a la fecha que aparecerá
        SimpleDateFormat format = new SimpleDateFormat("EEEE dd MMMM");

        //Cogemos la fecha de hoy
        Date today = new Date();

        //Mostramos el día de hoy en el textview formateado
        String strToday = getString(R.string.greetings_today) + " " + format.format(today);

        tvDateToday.setText(strToday);

        //El arrayToday pasa a ser el que enviamos en la main
        arrayToday = getArguments().getStringArrayList("arrayToday");

        /*for (int i = 1; i <= 20; i++) {
            arrayToday.add("Item " + i);
        }*/

        //inicializo el listview
        lvToday = (ListView)view.findViewById(R.id.lv_today);

        //Creamos un adaptador de String pasándole el contexto, un layout y el array today
        adapter = new ArrayAdapter<String>(view.getContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayToday);

        //Setteamos el listview con nuestro adaptador
        lvToday.setAdapter(adapter);

        //Indicamos el swipe refresh sea la decalrada en el layout
        srlToday = view.findViewById(R.id.srl_today);

        //Ahora le setteamos un escuchador de eventos cuando se refresca la lista
        srlToday.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                /*
                * Todo esto es código para probar cuando se refresca el ListView

                int start = arrayToday.size() + 1;

                for (int i = start; i < start + 5; i++) {
                    arrayToday.add("Nuevo Item " + i);
                }
                 */

                //Le indicamos al adaptardor que ha habido cambios
                //y le forzamosa actualizarse
                adapter.notifyDataSetChanged();

                //Hacemos que la flecha de refrescar desaparezca
                srlToday.setRefreshing(false);
            }

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today, container, false);
    }
}