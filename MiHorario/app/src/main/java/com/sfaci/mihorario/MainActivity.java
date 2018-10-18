package com.sfaci.mihorario;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {

    public static ArrayList<Asignatura> asignaturas =
            new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btAnadirClase = findViewById(R.id.btAnadirClase);
        btAnadirClase.setOnClickListener(this);
        Button btVerHorario = findViewById(R.id.btVerHorario);
        btVerHorario.setOnClickListener(this);
        Button btQueTocaAhora = findViewById(R.id.btQueTocaAhora);
        btQueTocaAhora.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = null;

        switch (v.getId()) {
            case R.id.btAnadirClase:
                intent = new Intent(this, NuevaClaseActivity.class);
                startActivity(intent);
                break;
            case R.id.btVerHorario:
                intent = new Intent(this, VerHorarioActivity.class);
                startActivity(intent);
                break;
            case R.id.btQueTocaAhora:
                intent = new Intent(this, QueTocaAhoraActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
