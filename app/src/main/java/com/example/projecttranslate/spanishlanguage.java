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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class spanishlanguage extends AppCompatActivity {
    Button btnTranslateEO;
    EditText editWordE;
    TextView txtTranslationO;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spanishlanguage);
        btnTranslateEO = (Button) findViewById(R.id.btnTranslateEO);
        btnTranslateEO.setOnClickListener(corkyListener);
        editWordE=(EditText) findViewById(R.id.editWordE);
        txtTranslationO=(TextView) findViewById(R.id.txtTranslationO);
    }
     private View.OnClickListener corkyListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (v.getId() == R.id.btnTranslateEO)  //option false E-O
            {
            //    Toast.makeText(spanishlanguage.this, "si clic esp-oto", Toast.LENGTH_SHORT).show();
                readTranslate(editWordE.getText().toString());
            }
        }
    };
    String translation = null;
    public  void readTranslate(final String word)  //palabra a traducir y si option es true es te O-E si es false es de E-O
    {
        //final String[] fina = {null};
        final String w=word.replaceAll("\\s","");//quitar los espacios en blanco
        mDatabase = FirebaseDatabase.getInstance().getReference();
        if (isNetworkConnected(spanishlanguage.this) == true) {
            mDatabase.child("traduccion").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange( DataSnapshot dataSnapshot) {
                    for(final DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Log.e("Data  ",snapshot.getValue()+"");
                        mDatabase.child("traduccion").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                translate tran = snapshot.getValue(translate.class);
                                String espanol = tran.getEspanol();
                                String otomi = tran.getOtomi();
                                Log.e("ESPAÑOL:   ", espanol + "    OTOMI:   " + otomi + "   buscalda: " + w);
                                if (w.equalsIgnoreCase(espanol)) {
                                    translation = otomi;
                                    txtTranslationO.setText(translation);
                                    Log.e("no", translation);
                                }
                            }
                            @Override
                            public void onCancelled( DatabaseError databaseError) {

                            }
                        });
                    }
                    txtTranslationO.setText("No encontrada");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(spanishlanguage.this);
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