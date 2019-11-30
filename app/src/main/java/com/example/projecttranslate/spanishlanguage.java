package com.example.projecttranslate;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOError;
import java.io.IOException;
import java.util.Locale;

public class spanishlanguage extends AppCompatActivity implements TextToSpeech.OnInitListener {
    Button btnTranslateEO;
    EditText editWordE;
    TextView txtTranslationO;
    FloatingActionButton fabSpanish,fabOtomi;
    private TextToSpeech textToSpeech;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spanishlanguage);

        btnTranslateEO = (Button) findViewById(R.id.btnTranslateEO);
        fabSpanish=findViewById(R.id.fabSpanish);
        fabOtomi=findViewById(R.id.fabOtomi);
        btnTranslateEO.setOnClickListener(corkyListener);
        editWordE=(EditText) findViewById(R.id.editWordE);
        txtTranslationO=(TextView) findViewById(R.id.txtTranslationO);
        textToSpeech = new TextToSpeech( this, (TextToSpeech.OnInitListener) this);

        InitializeFB();
        fabSpanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.setLanguage( new Locale( "spa", "MEX" ) );
                speak( editWordE.getText().toString() );

            }
        });
        fabOtomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.setLanguage( new Locale( "spa", "MEX" ) );
               // playSong("prueba");
                speak( txtTranslationO.getText().toString() );

            }
        });

    }
    public void InitializeFB(){
        //FirebaseApp.initializeApp();
        /*firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);*/
        mDatabase = firebaseDatabase.getInstance().getReference();
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
    boolean boo=false;
    public  void readTranslate(final String word)  //palabra a traducir y si option es true es te O-E si es false es de E-O
    {
        //final String[] fina = {null};
        final String w=word.replaceAll("\\s","");//quitar los espacios en blanco
        //mDatabase = FirebaseDatabase.getInstance().getReference();
        if (isNetworkConnected(spanishlanguage.this) == true) {
            mDatabase.child("translate").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange( DataSnapshot dataSnapshot) {

                    for(final DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Log.e("Data  ",snapshot.getValue()+"");
                        translate tran = snapshot.getValue(translate.class);
                        String espanol = tran.getEspanol();
                        String otomi = tran.getOtomi();
                        Log.e("ESPAÑOL:   ", espanol + "    OTOMI:   " + otomi + "   buscalda: " + w);
                        if (w.equalsIgnoreCase(espanol)) {
                            translation = otomi;
                            boo=true;
                            System.out.println("sisissisisisisisisi");
                            txtTranslationO.setText(translation);
                            Log.e("no", translation);
                        }
                    }
                    if (boo) {
                        txtTranslationO.setText(translation);
                        boo=false;
                    }
                    else
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
    private void speak( String str )
    {
        textToSpeech.speak( str, TextToSpeech.QUEUE_FLUSH, null );
        textToSpeech.setSpeechRate( 0.0f );
        textToSpeech.setPitch( 0.0f );
    }
    public void playSong(String name){
        MediaPlayer mediaPlayer=new MediaPlayer();
        try {
            mediaPlayer.setDataSource("gs://traductor-otomi-espanol-e1239.appspot.com/"+name+".mp3");
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
        }
        catch (IOException e){
            e.printStackTrace();

        }

    }

    @Override
    public void onInit(int status) {
        if ( status == TextToSpeech.LANG_MISSING_DATA | status == TextToSpeech.LANG_NOT_SUPPORTED )
        {
            Toast.makeText( this, "ERROR LANG_MISSING_DATA | LANG_NOT_SUPPORTED", Toast.LENGTH_SHORT ).show();
        }
    }
}