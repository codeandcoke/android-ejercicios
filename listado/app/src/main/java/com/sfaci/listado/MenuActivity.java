package com.sfaci.listado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity
    implements View.OnClickListener {

    public static ArrayList<String> listaMensajes
            = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btVer = (Button) findViewById(R.id.btVer);
        btVer.setOnClickListener(this);
        Button btAnadir = (Button) findViewById(R.id.btAnadir);
        btAnadir.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent;

        switch (view.getId()) {
            case R.id.btVer:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btAnadir:
                intent = new Intent(this, AnadirActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }
}
