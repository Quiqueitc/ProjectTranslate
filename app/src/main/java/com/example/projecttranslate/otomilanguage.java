package com.example.projecttranslate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class otomilanguage extends AppCompatActivity {
        Button btnTranslateOE;
        EditText editWordO;
        TextView txtTranslationE;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spanishlanguage);
        btnTranslateOE = (Button) findViewById(R.id.btnTranslateEO);
        btnTranslateOE.setOnClickListener(corkyListener);
        editWordO=(EditText) findViewById(R.id.editWordE);
        txtTranslationE=(TextView) findViewById(R.id.txtTranslationO);

    }
    private View.OnClickListener corkyListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (v.getId() == R.id.btnTranslateOE)  //option false E-O
            {
               /* String word= readTranslate(editWordO.getText().toString(),true);
                txtTranslationE.setText((word==null)?"No encontrada":word);*/
                Toast.makeText(otomilanguage.this, "esp-oto", Toast.LENGTH_SHORT).show();
            }
        }
    };

}
