package com.centroafuera.mistareas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.centroafuera.mistareas.MainActivity.Estado.VER_HECHAS;
import static com.centroafuera.mistareas.MainActivity.Estado.VER_PENDIENTES;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Tarea> tareas;
    private TareaAdapter adaptador;
    public enum Estado {
        VER_HECHAS, VER_PENDIENTES
    }
    private Estado estado;
    private final int FOTO_TAREA = 1;

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
        ImageView ivImagen = findViewById(R.id.ivImagen);
        ivImagen.setOnClickListener(this);

        tareas = new ArrayList<>();
        ListView lvTareas = findViewById(R.id.lvTareas);
        adaptador = new TareaAdapter(this, tareas);
        lvTareas.setAdapter(adaptador);
        estado = VER_PENDIENTES;

        registerForContextMenu(lvTareas);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Database db = new Database(this);
        tareas.clear();
        tareas.addAll(db.getTareas());
        adaptador.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btAnadir:
                EditText etTarea = findViewById(R.id.etTarea);
                ImageView ivImagen = findViewById(R.id.ivImagen);
                String nombre = etTarea.getText().toString();
                if (nombre.equals("")) {
                    Toast.makeText(this, R.string.mensaje_texto, Toast.LENGTH_SHORT).show();
                    return;
                }
                Bitmap imagen = ((BitmapDrawable) ivImagen.getDrawable()).getBitmap();

                Tarea tarea = new Tarea(nombre);
                tarea.setImagen(imagen);
                Database db = new Database(this);
                db.nuevaTarea(tarea);

                if (estado == VER_PENDIENTES) {
                    tareas.clear();
                    tareas.addAll(db.getTareasPendientes());
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
                tareas.clear();
                db = new Database(this);
                tareas.addAll(db.getTareasHechas());
                adaptador.notifyDataSetChanged();
                estado = VER_HECHAS;
                cambiarEstadoBotones();
                break;
            case R.id.btPendientes:
                // Actualiza la lista de tareas vistas
                tareas.clear();
                db = new Database(this);
                tareas.addAll(db.getTareasPendientes());
                adaptador.notifyDataSetChanged();
                estado = VER_PENDIENTES;
                cambiarEstadoBotones();
                break;
            case R.id.ivImagen:
                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, FOTO_TAREA);
                }
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
        final int posicion = menuInfo.position;

        switch (item.getItemId()) {
            case R.id.itemEliminar:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("¿Estás seguro?")
                        .setTitle("Eliminar tarea")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Tarea tareaEliminada = tareas.remove(posicion);
                                Database db = new Database(getApplicationContext());
                                db.eliminarTarea(tareaEliminada);
                                adaptador.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
                return true;
            case R.id.itemHecho:
                Tarea tarea = tareas.get(posicion);
                tarea.hacer();
                Database db = new Database(this);
                db.modificarTarea(tarea);
                adaptador.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == FOTO_TAREA) && (resultCode == RESULT_OK)
                && (data != null)) {
            // Obtiene el Uri de la imagen seleccionada por el usuario
            Uri imagenSeleccionada = data.getData();
            String[] ruta = {MediaStore.Images.Media.DATA };

            // Realiza una consulta a la galería de imágenes solicitando la imagen seleccionada
            Cursor cursor = getContentResolver().query(imagenSeleccionada, ruta, null, null, null);
            cursor.moveToFirst();

            // Obtiene la ruta a la imagen
            int indice = cursor.getColumnIndex(ruta[0]);
            String picturePath = cursor.getString(indice);
            cursor.close();

            // Carga la imagen en una vista ImageView que se encuentra en
            // en layout de la Activity actual
            ImageView imageView = findViewById(R.id.ivImagen);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}
