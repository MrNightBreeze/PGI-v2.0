package com.avalente.letsgout;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.avalente.letsgout.entidades.Evento;

import java.util.List;

/**
 * Created by avalente on 12/12/17.
 */

public class EventosList extends ArrayAdapter <Evento> {
    private Activity context;
    private List<Evento> eventoList;

    public EventosList (Activity context, List <Evento> eventoList)
    {
        super(context, R.layout.list_eventos, eventoList);
        this.context = context;
        this.eventoList = eventoList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_eventos, null, true);

        TextView textViewTitulo = (TextView) listViewItem.findViewById(R.id.textViewTitulo);
        TextView textViewDescricao= (TextView) listViewItem.findViewById(R.id.textViewDescricao);
        TextView textViewLocal= (TextView) listViewItem.findViewById(R.id.textViewLocal);

        Evento evento = eventoList.get(position);
        textViewTitulo.setText(evento.titulo);
        textViewDescricao.setText(evento.descricao);
        textViewLocal.setText(evento.local);

        return listViewItem;
    }
}
