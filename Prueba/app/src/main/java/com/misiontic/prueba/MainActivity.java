package com.misiontic.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.misiontic.prueba.model.Persona;

public class MainActivity extends AppCompatActivity {

    // Se crean los objetos
    EditText nombre, email, contra;
    ListView lv_personas;

    // Errore y arriba referenciado
    FirebaseData firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Se relaciona el objeto con lo grafico
        nombre= findViewById(R.id.txt_nombre);
        email= findViewById(R.id.txt_correo);
        contra= findViewById(R.id.txt_contraseña);

        lv_personas= findViewById(R.id.lv_datosPersonas);
        inicializarFirebase();

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Traemos las variables con las que vamos a trabajar
        String name= nombre.getText().toString();
        String corre= email.getText().toString();
        String past= contra.getText().toString();
        switch (item.getItemId()) {
            case R.id.icon_add:{
                if (name.equals("")||corre.equals("")||past.equals("")){
                    validacion();
                    break;
                }
                else{
                    Persona p= new Persona();

                    Toast.makeText(this, "Agregar", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                    break;
            }
            case R.id.icon_save:{
                Toast.makeText(this, "Guardar", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.icon_delete:{
                Toast.makeText(this, "Eliminar", Toast.LENGTH_LONG).show();
                break;
            }
            default:break;
        }
        return true;
    }

    private void limpiarCajas() {
            nombre.setText("");
            email.setText("");
            contra.setText("");
    }

    private void validacion() {
        String name= nombre.getText().toString();
        String corre= email.getText().toString();
        String past= contra.getText().toString();

        if (name.equals("")) {
            nombre.setError("Requiered");
        }
        else if (corre.equals("")) {
            email.setError("Required");
        }
        else if (past.equals("")) {
            contra.setError("Required");
        }
    }
}


