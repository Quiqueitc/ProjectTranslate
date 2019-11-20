package com.example.projecttranslate;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        Button btnSpanish = (Button) findViewById(R.id.btnSpanish);


        Button btnOtomi = (Button) findViewById(R.id.btnOtomi);
        //btnOtomi.setOnClickListener(this);

        Button btnLoginIn = (Button) findViewById(R.id.btnLoginIn);
        Button btnClose = (Button) findViewById(R.id.btnClose);
        //btnLoginIn.setOnClickListener(this);
    }

    private void leerDatos(int id_traduccion){

    }

    public void onClick (View v)
    {

        // Para tener control se   condiciona la identificacion del boton al que se le dio click     // de estaforma solo se ejecuta      el codigo correspondiente a ese boton
        if (v.getId() == R.id.btnSpanish) {
            setContentView(R.layout.spanishlanguage);
            Toast.makeText(this, "Clic in spansh", Toast.LENGTH_SHORT).show();
        }
        if (v.getId() == R.id.btnOtomi) {
            setContentView(R.layout.otomilanguage);
            Toast.makeText(this, "Clic in otomi", Toast.LENGTH_SHORT).show();
        }
        if (v.getId() == R.id.btnLoginIn) {
            setContentView(R.layout.administration);
            Toast.makeText(this, "Clic in LOgin", Toast.LENGTH_SHORT).show();
        }
        if (v.getId() == R.id.btnClose) {

            Toast.makeText(this, "BYE", Toast.LENGTH_SHORT).show();
        }

    }




}
