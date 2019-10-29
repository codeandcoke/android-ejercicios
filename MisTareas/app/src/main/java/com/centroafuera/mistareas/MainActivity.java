package com.centroafuera.mistareas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.centroafuera.mistareas.MainActivity.Estado.VER_HECHAS;
import static com.centroafuera.mistareas.MainActivity.Estado.VER_PENDIENTES;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Tarea> tareas;
    private ArrayList<Tarea> tareasVistas;
    private ArrayAdapter<Tarea> adaptador;
    public enum Estado {
        VER_HECHAS, VER_PENDIENTES
    }
    private Estado estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btAnadir = findViewById(R.id.btAnadir);
        btAnadir.setOnClickListener(this);
        Button btHechas = findViewById(R.id.btHechas);
        btHechas.setOnClickListener(this);
        Button btPendientes = findViewById(R.id.btPendientes);
        btPendientes.setOnClickListener(this);

        tareas = new ArrayList<>();
        tareasVistas = new ArrayList<>();
        ListView lvTareas = findViewById(R.id.lvTareas);
        adaptador =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tareasVistas);
        lvTareas.setAdapter(adaptador);
        estado = VER_PENDIENTES;

        registerForContextMenu(lvTareas);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btAnadir:
                EditText etTarea = findViewById(R.id.etTarea);
                String nombre = etTarea.getText().toString();
                if (nombre.equals("")) {
                    Toast.makeText(this, R.string.mensaje_texto, Toast.LENGTH_SHORT).show();
                    return;
                }

                Tarea tarea = new Tarea(nombre);
                Database db = new Database(this);
                db.nuevaTarea(tarea);

                if (estado == VER_PENDIENTES) {
                    tareasVistas.add(tarea);
                    adaptador.notifyDataSetChanged();
                } else {
                    Toast.makeText(this, R.string.mTareaAnadidaPendientes,
                            Toast.LENGTH_SHORT).show();
                }
                etTarea.setText("");
                etTarea.requestFocus();
                break;
            case R.id.btHechas:
                // Actualiza la lista de tareas vistas
                tareasVistas.clear();
                for (Tarea laTarea : tareas) {
                    if (laTarea.estaHecha())
                        tareasVistas.add(laTarea);
                }
                adaptador.notifyDataSetChanged();
                estado = VER_HECHAS;
                cambiarEstadoBotones();
                break;
            case R.id.btPendientes:
                // Actualiza la lista de tareas vistas
                tareasVistas.clear();
                for (Tarea laTarea : tareas) {
                    if (!laTarea.estaHecha())
                        tareasVistas.add(laTarea);
                }
                adaptador.notifyDataSetChanged();
                estado = VER_PENDIENTES;
                cambiarEstadoBotones();
                break;
            default:
                break;
        }
    }

    private void cambiarEstadoBotones() {
        Button btHechas = findViewById(R.id.btHechas);
        Button btPendientes = findViewById(R.id.btPendientes);
        switch (estado) {
            case VER_HECHAS:
                btHechas.setBackgroundColor(Color.BLACK);
                btHechas.setTextColor(Color.WHITE);
                btPendientes.setBackgroundColor(Color.GRAY);
                btPendientes.setTextColor(Color.BLACK);
                break;
            case VER_PENDIENTES:
                btPendientes.setBackgroundColor(Color.BLACK);
                btPendientes.setTextColor(Color.WHITE);
                btHechas.setBackgroundColor(Color.GRAY);
                btHechas.setTextColor(Color.BLACK);
                break;
            default:
                break;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        switch (estado) {
            case VER_HECHAS:
                getMenuInflater().inflate(R.menu.menu_contextual_hechas, menu);
                break;
            case VER_PENDIENTES:
                getMenuInflater().inflate(R.menu.menu_contextual_pendientes, menu);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int posicion = menuInfo.position;

        switch (item.getItemId()) {
            case R.id.itemEliminar:
                Tarea tareaEliminada = tareasVistas.remove(posicion);
                tareas.remove(tareaEliminada);
                adaptador.notifyDataSetChanged();
                return true;
            case R.id.itemHecho:
                Tarea tarea = tareasVistas.get(posicion);
                tarea.hacer();
                tareasVistas.remove(posicion);
                adaptador.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
