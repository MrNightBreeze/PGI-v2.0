package com.avalente.letsgout;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toolbar;
import android.widget.AdapterView.OnItemClickListener;

import com.avalente.letsgout.entidades.Evento;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avalente on 14/12/17.
 */

public class Atividades extends Fragment {
    public static final String EVENTO_ID = "eventoid";
    public static final String EVENTO_TITULO = "eventotitulo";
    public static final String EVENTO_DESCRICAO = "eventodescricao";
    public static final String EVENTO_LOCAL = "eventolocal";
    public static final String EVENTO_DATA = "eventodata";
    public static final String EVENTO_CATEGORIA = "eventocategoria";


    DatabaseReference eventos;
    public ListView listViewEventos;

    List<Evento> eventosList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_eventos, container, false);

        eventos = FirebaseDatabase.getInstance().getReference("eventos");

        listViewEventos = (ListView) view.findViewById(R.id.listViewEventos);
        eventosList = new ArrayList<>();

        FloatingActionButton btnAdicionaEvento = (FloatingActionButton) view.findViewById(R.id.btnAdicionaEvento);
        btnAdicionaEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criaEvento(view);
            }
        });

        listViewEventos.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Evento evento = eventosList.get(i);
                Intent intent = new Intent(getActivity(), PaginaEvento.class);
                intent.putExtra(EVENTO_ID,evento.id);
                intent.putExtra(EVENTO_TITULO,evento.titulo);
                intent.putExtra(EVENTO_DESCRICAO,evento.descricao);
                intent.putExtra(EVENTO_LOCAL,evento.local);
                intent.putExtra(EVENTO_DATA,evento.data);
                intent.putExtra(EVENTO_CATEGORIA,evento.categoria);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Atividades");
    }

    @Override
    public void onStart() {
        super.onStart();

        eventos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventosList.clear();
                for (DataSnapshot eventoSnapshot : dataSnapshot.getChildren())
                {
                    Evento evento = eventoSnapshot.getValue(Evento.class);
                    eventosList.add(0, evento);
                }

                EventosList adapter = new EventosList(getActivity(), eventosList);
                listViewEventos.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void criaEvento(View v)
    {
        Intent intent = new Intent(getActivity(), CriaEvento.class);
        startActivity(intent);
    }
}
