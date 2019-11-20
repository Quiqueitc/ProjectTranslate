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

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends BaseAdapter {
    private Context context;

    public MyListAdapter(Context context, ArrayList<revision> listItems) {
        this.context = context;
        this.listItems = listItems;
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
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Rechazada  esp"+item.getEspanol()+"  oto "+item.getOtomi(), Toast.LENGTH_SHORT).show();
            }
        });
        txtSpanish.setText(item.getEspanol());
        txtOtomi.setText(item.getOtomi());

        return convertView;
    }
}