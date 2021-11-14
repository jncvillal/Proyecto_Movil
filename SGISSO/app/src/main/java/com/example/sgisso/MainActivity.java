package com.example.sgisso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void IrIncidentes(View view){
        Intent inc = new Intent( this,IncidentesActivity.class );

        startActivity( inc );
    }

    public void IrCondicionesSeguras(View view){
        Intent inc = new Intent( this,CondicionesSegurasActivity.class );

        startActivity( inc );
    }
}