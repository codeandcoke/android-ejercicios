package com.sfaci.mihorario;

import android.app.Activity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.sfaci.mihorario.MainActivity.asignaturas;

public class QueTocaAhoraActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que_toca_ahora);

        int diaHoy = new GregorianCalendar().get(Calendar.DAY_OF_WEEK);

        for (Asignatura asignatura : asignaturas) {
            if (DateUtils.diaOrdinal(asignatura.getDia()) == diaHoy) {

            }
        }
    }
}
