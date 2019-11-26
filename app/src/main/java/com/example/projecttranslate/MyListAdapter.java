package com.example.projecttranslate;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyListAdapter extends BaseAdapter {
    private Context context;
    private DatabaseReference mDatabase;

    public MyListAdapter(Context context, ArrayList<revision> listItems) {
        this.context = context;
        this.listItems = listItems;
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private ArrayList<revision>listItems;

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final revision item=(revision)getItem(position);

        convertView=LayoutInflater.from(context).inflate(R.layout.list_item,null);
        TextView txtSpanish=(TextView)convertView.findViewById(R.id.txtSpanish);
        TextView txtOtomi=(TextView)convertView.findViewById(R.id.txtOtomi);
        Button btnAdd=(Button) convertView.findViewById(R.id.btnAdd);
        Button btnDelete=(Button) convertView.findViewById(R.id.btnDelete);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Aceptada   esp"+item.getEspanol()+"  oto "+item.getOtomi(), Toast.LENGTH_SHORT).show();
                long leftLimit = 1L;
                long rightLimit = 10000000L;
                long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

                mDatabase.child("traduccion").push().setValue(new translate(item.getEspanol(),item.getOtomi(),generatedLong));
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Rechazada  esp"+item.getEspanol()+"  oto "+item.getOtomi(), Toast.LENGTH_SHORT).show();
              /*  mDatabase.child("traduccion");
                HashMap<Long,translate> newWord=new HashMap<>();
                Long number=Long.parseLong(String.valueOf((Math.random()*10000) +1));
                newWord.put(number, new translate(revisionD.getEspanol(),revisionD.getOtomi(),number));*/

                //newWord.put("gracehop", new translate("December 9, 1906", "Grace Hopper"));

            }
        });
        txtSpanish.setText(item.getEspanol());
        txtOtomi.setText(item.getOtomi());

        return convertView;
    }
}