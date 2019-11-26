package com.example.projecttranslate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class addWord extends AppCompatActivity {
    private EditText edtASpanish,edtAOtomi;
    private Button btnATranslate;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addword);
        edtASpanish=findViewById(R.id.edtASpanish);
        edtAOtomi=findViewById(R.id.edtAOtomi);
        btnATranslate=findViewById(R.id.btnATranslate);
        btnATranslate.setOnClickListener(corkyListener);
    }
    private View.OnClickListener corkyListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (v.getId() == R.id.btnATranslate)  //option false E-O
                {
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    addBD();

            }
        }
    };
    public void addBD(){
        //mDatabase.child("revisiones");
        long leftLimit = 1L;
        long rightLimit = 10000000L;
        long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

        mDatabase.child("revisiones").push().setValue(new revision(edtASpanish.getText().toString(),edtAOtomi.getText().toString(),generatedLong,0L));
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
}
