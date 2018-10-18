package com.sfaci.mihorario;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.sfaci.mihorario.MainActivity.asignaturas;

public class VerHorarioActivity extends Activity implements AdapterView.OnItemSelectedListener {

    ArrayAdapter<String> diasAdapter;
    ArrayAdapter<Asignatura> asignaturasAdapter;
    String[] dias = {"Lunes", "Martes", "Miércoles",
            "Jueves", "Viernes"};
    ArrayList<Asignatura> asignaturasDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_horario);

        diasAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, dias);
        Spinner spDia = findViewById(R.id.spDia);
        spDia.setAdapter(diasAdapter);
        spDia.setOnItemSelectedListener(this);

        asignaturasDia = new ArrayList<>();
        asignaturasAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, asignaturasDia);
        ListView lvAsignaturas = findViewById(R.id.lvAsignaturas);
        lvAsignaturas.setAdapter(asignaturasAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        asignaturasDia.clear();

        String dia = dias[position];
        for (Asignatura asignatura : asignaturas) {
            if (asignatura.getDia().equals(dia)) {
                asignaturasDia.add(asignatura);
            }
        }

        asignaturasAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
