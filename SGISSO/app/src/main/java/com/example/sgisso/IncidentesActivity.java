package com.example.sgisso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class IncidentesActivity extends AppCompatActivity {

    private EditText txtNombre, txtApellido, txtCreador, txtDescipcionIncidente;
    private Spinner spnProceso, spnTipoIncidente, spnTipoTrabajador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidentes);

        String tipoIncidentes[] = { "Muy grave","Grave","Leve","Sin concecuencias" };
        String tipoTrabajadores[] = { "Vinculado","Contratistas","Termino fijo","Por obra" };
        String procesos[] = { "Mantenimiento","Proyectos" };

        txtNombre = (EditText) findViewById( R.id.Nombre );
        txtApellido = (EditText) findViewById( R.id.Apellido );
        txtCreador = (EditText) findViewById( R.id.Creador );
        txtDescipcionIncidente = (EditText) findViewById( R.id.DescripcionIncidente );

        spnProceso = (Spinner) findViewById( R.id.Proceso );
        spnTipoIncidente = (Spinner) findViewById( R.id.TipoIncidente );
        spnTipoTrabajador = (Spinner) findViewById( R.id.TipoTrabajador);

        ArrayAdapter <String> adpProcesos = new ArrayAdapter <String>( this, android.R.layout.simple_spinner_item,procesos );
        ArrayAdapter <String> adpTipoIncidentes = new ArrayAdapter <String>( this, android.R.layout.simple_spinner_item,tipoIncidentes );
        ArrayAdapter <String> adpTipoTrabajadores = new ArrayAdapter <String>( this, android.R.layout.simple_spinner_item,tipoTrabajadores );

        spnProceso.setAdapter( adpProcesos );
        spnTipoIncidente.setAdapter( adpTipoIncidentes );
        spnTipoTrabajador.setAdapter( adpTipoTrabajadores );
    }

    public void Registrar( View view ){
        SQLIncidentes adminSQL = new SQLIncidentes( this,"local",null,1 );
        SQLiteDatabase db = adminSQL.getWritableDatabase();

        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();
        String creador = txtCreador.getText().toString();
        String descripcion = txtDescipcionIncidente.getText().toString();
        String proceso = spnProceso.getSelectedItem().toString();
        String tipoIncidente = spnTipoIncidente.getSelectedItem().toString();
        String tipoTrabajador = spnTipoTrabajador.getSelectedItem().toString();

        if( !nombre.isEmpty() &&
            !apellido.isEmpty() &&
            !creador.isEmpty() &&
            !descripcion.isEmpty() &&
            !proceso.isEmpty() &&
            !tipoIncidente.isEmpty() &&
            tipoTrabajador.isEmpty() ){

            ContentValues registro = new ContentValues();

            registro.put( "nombre",nombre );
            registro.put( "apellido",apellido );
            registro.put( "creador",creador );
            registro.put( "descripcion",descripcion );
            registro.put( "proceso",proceso );
            registro.put( "tipoIncidente",tipoIncidente );
            registro.put( "tipoTrabajador",tipoTrabajador );

            db.insert( "incidentes",null,registro );
            db.close();

            txtNombre.setText( "" );
            txtApellido.setText( "" );
            txtCreador.setText( "" );
            txtDescipcionIncidente.setText( "" );

            Toast.makeText( this,"Registro almacenado",Toast.LENGTH_SHORT ).show();;
        }
        else
            Toast.makeText( this,"Llenar todos los campos",Toast.LENGTH_LONG ).show();
    }
}