package com.example.projecttranslate;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

public class dialog {
    /**
     * Crea un diálogo con personalizado para comportarse
     * como formulario de login
     *
     * @return Diálogo
     */
    private returnDatos interfaz;
    public  interface returnDatos{
        void returnDatos(String data);
    }
    public dialog(Context context, returnDatos returnData) {
        interfaz=returnData;
        final Dialog dialog=new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));        dialog.setContentView(R.layout.dialog_signin);


        final EditText editPass=(EditText) dialog.findViewById(R.id.editPass);
        final Button btnSignIn=(Button) dialog.findViewById(R.id.btnSignIn);
        final Button btnCancel=(Button) dialog.findViewById(R.id.btnCancel);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaz.returnDatos(editPass.getText().toString());
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
