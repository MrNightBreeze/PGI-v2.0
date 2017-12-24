package com.avalente.letsgout;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.avalente.letsgout.entidades.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaginaEvento extends AppCompatActivity {

    public TextView txtTitulo;
    public TextView txtDescricao;
    public TextView txtLocal;
    public TextView txtData;
    public Toolbar toolbar;

    public Button btnInteresse;
    public Button btnChat;

    public String id;
    DatabaseReference evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_evento);

        txtTitulo = (TextView) findViewById(R.id.textViewTitulo);
        txtDescricao = (TextView) findViewById(R.id.textViewDescricao);
        txtLocal = (TextView) findViewById(R.id.textViewLocal);
        txtData = (TextView) findViewById(R.id.textViewData);
        toolbar = (Toolbar) findViewById(R.id.evento_toolbar);
        btnInteresse = (Button) findViewById(R.id.buttonInteressado);
        btnChat = (Button) findViewById(R.id.buttonChat);

        Intent intent = getIntent();

        id = intent.getStringExtra(Atividades.EVENTO_ID);
        String titulo = intent.getStringExtra(Atividades.EVENTO_TITULO);
        String descricao = intent.getStringExtra(Atividades.EVENTO_DESCRICAO);
        String local = intent.getStringExtra(Atividades.EVENTO_LOCAL);
        String data = intent.getStringExtra(Atividades.EVENTO_DATA);
        String categoria = intent.getStringExtra(Atividades.EVENTO_CATEGORIA);

        evento = FirebaseDatabase.getInstance().getReference("eventos").child(id);

        btnInteresse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(Main2Activity.userid,Main2Activity.first_name,false,false,false,false,false);
                evento.child(Main2Activity.userid).setValue(user);
            }
        });

        txtTitulo.setText(titulo);
        txtDescricao.setText(descricao);
        txtLocal.setText(local);
        txtData.setText(data);
        toolbar.setTitle(categoria);



    }

    public void chatButton(View v){
        Intent intent = new Intent(this, Chat.class);
        System.out.println(Main2Activity.first_name);
        System.out.println(Main2Activity.userid);
        ///System.out.println(evento.child(id).getParent());
        DatabaseReference s = evento.child(id).getParent();

        intent.putExtra("EVENT_URL", String.valueOf(s));
        //System.out.println(String.valueOf(s));
        startActivity(intent);
    }

}
