package com.example.projecttranslate;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
        signInValite(data);
    }
    public  void signInValite(final String pass)  //palabra a traducir y si option es true es te O-E si es false es de E-O
    {

        mDatabase = FirebaseDatabase.getInstance().getReference();
        if (isNetworkConnected(MainActivity.this) == true) {
            mDatabase.child("administradores").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange( DataSnapshot dataSnapshot) {
                    for(final DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Log.e("Data  ",snapshot.getValue()+"");
                        mDatabase.child("administradores").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                administrators ad = snapshot.getValue(administrators.class);
                                String contrasena = ad.getContrasena();
                                Long id = ad.getId_admin();
                                Log.e("pass:   ", contrasena + "    OTOMI:   " + id + "   search: " + pass);
                                if (pass.equalsIgnoreCase(contrasena)) {
                                    Intent intent2 = new Intent (getApplicationContext(), administration.class);
                                    startActivity(intent2);
                                }
                            }
                            @Override
                            public void onCancelled( DatabaseError databaseError) {
                            }
                        });
                    }
                    Toast.makeText(context, "Error en contraseña", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Conexión incorrecta")
                    .setTitle("Verifique conexión");
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
    private boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null || !info.isConnected() || !info.isAvailable()) {
            return false;
        }
        return true;
    }
}
