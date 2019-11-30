package com.example.projecttranslate;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class administration extends AppCompatActivity {
    Button btnClose;
    private MyListAdapter myListAdapter;
    private ArrayList<revision> data = new ArrayList<revision>();
    private DatabaseReference mDatabase;
    private ListView listWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administration);
        btnClose = (Button) findViewById(R.id.btnSignOut);
        btnClose.setOnClickListener(corkyListener);
        Toast.makeText(this, "Bienvenido administrador", Toast.LENGTH_SHORT).show();

        readRevisions();
        View v=new View(this);
        if (data==null)
            Snackbar.make(v,"NO hay traducciones por revisar.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        listWord= (ListView) findViewById(R.id.listWord);
        //generateListContent();

        myListAdapter=new MyListAdapter(this,data);
        listWord.setAdapter(myListAdapter);


        listWord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(administration.this, "List item was clicked at " + position, Toast.LENGTH_SHORT).show();
                revision revisionD=data.get(position);
                AddTranslation(revisionD);
            }
        });


    }

    public void readRevisions()
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        if (isNetworkConnected(administration.this) == true) {

            mDatabase.child("revisions").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    data.clear();
                    for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                        revision revs = objSnaptshot.getValue(revision.class);
                        data.add(revs);
                        Log.e("info",revs.getUid()+"  e  "+revs.getEspanol()+"   o  "+revs.getOtomi());
                        /*arrayAdapterPersona = new ArrayAdapter<Persona>(MainActivity.this, android.R.layout.simple_list_item_1, listPerson);
                        listV_personas.setAdapter(arrayAdapterPersona);*/
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            /*mDatabase.child("revisions").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange( DataSnapshot dataSnapshot) {
                    data.clear();
                    for(final DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Log.e("Data  ",snapshot.getValue()+"");
                        mDatabase.child("revisions").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                revision rev = snapshot.getValue(revision.class);
                                revision tda=new revision(rev.getEspanol(),rev.getOtomi(),rev.getId_revision(),rev.getEstatus());
                                data.add(tda);
                                Log.e("data: ",snapshot.getValue()+"");
                            }
                            @Override
                            public void onCancelled( DatabaseError databaseError) {

                            }
                        });
                    }

                    //txtTranslationO.setText("No encontrada");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });*/
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(administration.this);
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
    private View.OnClickListener corkyListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (v.getId() == R.id.btnSignOut)  //option false E-O
            {
                finish();
               /* String word= readTranslate(editWordO.getText().toString(),true);
                txtTranslationE.setText((word==null)?"No encontrada":word);*/
               // Toast.makeText(administration.this, "esp-oto", Toast.LENGTH_SHORT).show();
            }
        }
    };
    public void AddTranslation(revision revisionD){



    }
    public void DeleteRevision(){

    }

}
