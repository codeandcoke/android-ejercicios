package com.sfaci.mihorario;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.sfaci.mihorario.MainActivity.asignaturas;

public class QueTocaAhoraActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que_toca_ahora);

        TextView tvLoQueToca = findViewById(R.id.tvLoQueToca);

        int diaHoy = new GregorianCalendar().get(Calendar.DAY_OF_WEEK);
        int horaAhora = new GregorianCalendar().get(Calendar.HOUR);
        for (Asignatura asignatura : asignaturas) {
            if (DateUtils.diaOrdinal(asignatura.getDia()) == diaHoy) {
                if (Integer.parseInt(asignatura.getHora()) == horaAhora) {
                    tvLoQueToca.setText("Te toca ir a clase de " + asignatura.getNombre());
                    return;
                }

            }
        }

        tvLoQueToca.setText("Tiempo Libre!!!!!!!!! " + diaHoy + " " + horaAhora);
    }
}
