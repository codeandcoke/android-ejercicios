package com.sfaci.listado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AnadirActivity extends AppCompatActivity
    implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);

        Button btAnadir = (Button) findViewById(R.id.btAnadir);
        btAnadir.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        EditText etMensaje = (EditText) findViewById(R.id.etMensaje);
        String mensaje = etMensaje.getText().toString();
        MenuActivity.listaMensajes.add(mensaje);

        etMensaje.setText("");
    }
}
