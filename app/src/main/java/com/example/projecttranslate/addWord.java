package com.example.projecttranslate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.UUID;

public class addWord extends AppCompatActivity {
    private EditText edtASpanish,edtAOtomi;
    private Button btnATranslate;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addword);
        InitializeFB();
        edtASpanish=findViewById(R.id.edtASpanish);
        edtAOtomi=findViewById(R.id.edtAOtomi);
        btnATranslate=findViewById(R.id.btnATranslate);
        btnATranslate.setOnClickListener(corkyListener);
    }
    private View.OnClickListener corkyListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (v.getId() == R.id.btnATranslate)  //option false E-O
                {

                    addBD(v);

            }
        }
    };
    public void InitializeFB(){
       /* FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);*/
        mDatabase = firebaseDatabase.getInstance().getReference();
    }
    public void addBD(View v){
        //mDatabase.child("revisiones");
        long leftLimit = 1L;
        long rightLimit = 10000000L;
        long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        if (validacion())
        {
            revision revision=new revision(edtASpanish.getText().toString(),edtAOtomi.getText().toString(),UUID.randomUUID().toString());
            mDatabase.child("revisions").child(revision.getUid()).setValue(revision);
            Snackbar.make(v, "Agregada con éxito, la traduccioón pasará a revisión.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

        clearEdits();
       /* HashMap<Long,revision> newWord=new HashMap<>();
        System.out.println("siii");
        //Double number=(Math.random()*10000 +1);
        //Long num=Long.parseLong(String.valueOf(number));
        System.out.println("siii  2");
        newWord.put(generatedLong, new revision(edtASpanish.getText().toString(),edtAOtomi.getText().toString(),generatedLong,0L));
        System.out.println("siii   3");
        mDatabase.setValue(newWord);*/
       /* DatabaseReference postsRef = ref.child("posts");

        DatabaseReference newPostRef = postsRef.push();
        newPostRef.setValueAsync(new Post("gracehop", "Announcing COBOL, a New Programming Language"));

// We can also chain the two calls together
        postsRef.push().setValueAsync(new Post("alanisawesome", "The Turing Machine"));*/
    }
    private void clearEdits() {
        edtAOtomi.setText("");
        edtASpanish.setText("");
    }

    private boolean validacion() {
        String otomi = edtAOtomi.getText().toString();
        String spani = edtASpanish.getText().toString();

        if (otomi.equals("")){
            edtAOtomi.setError("Requerido");
            return false;
        }
        else if (spani.equals("")){
            edtASpanish.setError("Requerido");
            return false;
        }
        return true;
    }
}
