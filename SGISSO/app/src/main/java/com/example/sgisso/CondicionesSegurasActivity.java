package com.example.sgisso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

public class CondicionesSegurasActivity extends AppCompatActivity {

    private EditText txtObservador, txtResponsable, txtDescripcion;
    private Spinner spnTipoRiesgo,spnNivelRiesgo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condiciones_seguras);

        String tipoRiesgo[] = { "Físicos","Químicos","biológicos","ergonómicos","psicosociales","mecánicos","ambientales" };
        String nivelRiesgo[] = { "Alto","Medio","Bajo", "Minimo" };


        txtObservador = (EditText) findViewById( R.id.Observador );
        txtResponsable = (EditText) findViewById( R.id.Responsable );
        txtDescripcion = (EditText) findViewById( R.id.DescripcionCInsegura );

        spnTipoRiesgo = (Spinner) findViewById( R.id.TipoRiesgo );
        spnNivelRiesgo = (Spinner) findViewById( R.id.NivelRiesgo );

        ArrayAdapter <String> adpTipoRiesgo = new ArrayAdapter <String>( this, android.R.layout.simple_spinner_item,tipoRiesgo );
        ArrayAdapter <String> adpNivelRiesgo = new ArrayAdapter <String>( this, android.R.layout.simple_spinner_item,nivelRiesgo );

        spnTipoRiesgo.setAdapter( adpTipoRiesgo );
        spnNivelRiesgo.setAdapter( adpNivelRiesgo );
    }

    public void Registrar( View view ){
        SQLCondicionesInseguras adminSQL = new SQLCondicionesInseguras( this,"local",null,1 );
        SQLiteDatabase db = adminSQL.getWritableDatabase();

        String observador = txtObservador.getText().toString();
        String responsable = txtResponsable.getText().toString();
        String descripcion = txtDescripcion.getText().toString();
        String tipoRiesgo = spnTipoRiesgo.getSelectedItem().toString();
        String nivelRiesgo = spnNivelRiesgo.getSelectedItem().toString();

        if( !observador.isEmpty() && !responsable.isEmpty() && !descripcion.isEmpty() && !tipoRiesgo.isEmpty() && !nivelRiesgo.isEmpty() ){
            ContentValues registro = new ContentValues();

            registro.put( "observador",observador );
            registro.put( "responsable",responsable );
            registro.put( "descripcion",descripcion );
            registro.put( "tipoRiesgo",tipoRiesgo );
            registro.put( "nivelRiesgo",nivelRiesgo );

            db.insert( "incidentes",null,registro );
            db.close();

            txtObservador.setText( "" );
            txtResponsable.setText( "" );
            txtDescripcion.setText( "" );

            Toast.makeText( this,"Registro almacenado",Toast.LENGTH_SHORT ).show();;
        }
        else
            Toast.makeText( this,"Llenar todos los campos",Toast.LENGTH_LONG ).show();
    }
}