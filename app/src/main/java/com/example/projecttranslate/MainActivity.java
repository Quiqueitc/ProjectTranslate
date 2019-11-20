package com.example.projecttranslate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements dialog.returnDatos {
    Context context;
    String data;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DatabaseReference mDatabase;
    private Button btnSpanish,btnOtomi,btnLoginIn;
    private TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        btnSpanish = (Button) findViewById(R.id.btnSpanish);
        btnOtomi = (Button) findViewById(R.id.btnOtomi);
        btnLoginIn = (Button) findViewById(R.id.btnLoginIn);

        btnLoginIn.setOnClickListener(corkyListener);
        btnSpanish.setOnClickListener(corkyListener);
        btnOtomi.setOnClickListener(corkyListener);
        titulo=(TextView)findViewById(R.id.titulo);
        context=this;
    }

    private void leerDatos(int id_traduccion){

    }

    private View.OnClickListener corkyListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (v.getId() == R.id.btnSpanish)  //option false E-O
            {
                Intent intent = new Intent (v.getContext(), spanishlanguage.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "clic spanish", Toast.LENGTH_SHORT).show();

            }
            if (v.getId() == R.id.btnOtomi)  //option false E-O
            {
                Intent intent1 = new Intent (v.getContext(), otomilanguage.class);
                startActivity(intent1);
                Toast.makeText(MainActivity.this, "clic otomi", Toast.LENGTH_SHORT).show();
            }
            if (v.getId() == R.id.btnLoginIn)  //option false E-O
            {
                new dialog(context,MainActivity.this);
            }
        }
    };

    @Override
    public void returnDatos(String data) {
        this.data=data;
        titulo.setText(data);
        Toast.makeText(context, "yaaa:  "+data, Toast.LENGTH_LONG).show();
                /*Intent intent2 = new Intent (v.getContext(), administration.class);
                startActivity(intent2);
                Toast.makeText(MainActivity.this, "clic otomi", Toast.LENGTH_SHORT).show();*/
        Toast.makeText(context, "datas    "+data, Toast.LENGTH_LONG).show();
    }

    /*public void onClick (View v)
    {

        // Para tener control se   condiciona la identificacion del boton al que se le dio click     // de estaforma solo se ejecuta      el codigo correspondiente a ese boton
        /*if (v.getId() == R.id.btnSpanish) {

        }*
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
      /*  if (v.getId() == R.id.btnTranslateEO)  //option false E-O
        {
            editWordE=(EditText) btnSpanish.findViewById(R.id.editWordE);
            editWordO=(EditText) findViewById(R.id.editWordO);
            System.out.println("text  "+editWordE.getText().toString()+"   el otro    "+editWordO.getText().toString());
            String word= readTranslate(editWordE.getText().toString(),false);
            txtTranslationO.setText((word==null)?"No encontrada":word);
            Toast.makeText(this, "esp-oto", Toast.LENGTH_SHORT).show();
        }*
        if (v.getId() == R.id.btnTranslateOE) {

            Toast.makeText(this, "oto-esp", Toast.LENGTH_SHORT).show();
        }

    }*/




}
