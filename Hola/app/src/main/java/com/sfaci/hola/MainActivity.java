package com.sfaci.hola;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
                          implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btOk = (Button) findViewById(R.id.btOk);
        btOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btOk:
                EditText etTexto = (EditText) findViewById(R.id.etTexto);
                String mensaje = etTexto.getText().toString();
                Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
        }
    }
}
