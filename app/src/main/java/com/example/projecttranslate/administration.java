package com.example.projecttranslate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class administration extends AppCompatActivity {
    Button btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administration);
        btnClose = (Button) findViewById(R.id.btnSignOut);
        btnClose.setOnClickListener(corkyListener);
        Toast.makeText(this, "Bienvenido administrador", Toast.LENGTH_SHORT).show();
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

}
