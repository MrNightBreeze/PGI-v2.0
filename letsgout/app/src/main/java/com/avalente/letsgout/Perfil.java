package com.avalente.letsgout;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.avalente.letsgout.entidades.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by avalente on 14/12/17.
 */

public class Perfil extends Fragment {

    DatabaseReference users;

    CheckBox desporto, entretenimento, restaurantes, arte, estudar;
    boolean desp, ent, est, art, rest;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.perfil, container, false);

        desporto = view.findViewById(R.id.checkBoxDesporto);
        entretenimento = view.findViewById(R.id.checkBoxEntretenimento);
        restaurantes = view.findViewById(R.id.checkBoxRestaurantes);
        arte = view.findViewById(R.id.checkBoxArtes);
        estudar = view.findViewById(R.id.checkBoxEstudar);

        users = FirebaseDatabase.getInstance().getReference("users");

        return inflater.inflate(R.layout.perfil, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Perfil");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_ok, menu);
    }

    public void checkSelected(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId())
        {
            case R.id.checkBoxArtes:
                if (checked)
                    art = true;
                else
                    art = false;
                break;
            case R.id.checkBoxDesporto:
                if (checked)
                    desp = true;
                else
                    desp = false;
                break;
            case R.id.checkBoxEntretenimento:
                if (checked)
                    ent = true;
                else
                    ent = false;
                break;
            case R.id.checkBoxEstudar:
                if (checked)
                    est = true;
                else
                    est = false;
                break;
            case R.id.checkBoxRestaurantes:
                if (checked)
                    rest = true;
                else
                    rest = false;
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.ok_btn)
        {
            User user= new User(Main2Activity.userid, desporto.isChecked(), entretenimento.isChecked(), arte.isChecked(), estudar.isChecked(), restaurantes.isChecked());

            users.child(Main2Activity.userid).setValue(user);

            // guarda na base de dados
            Toast.makeText(getActivity(),"Interesses guardados", Toast.LENGTH_SHORT).show();

            // chama um intent e redireciona para a MainActivity e passa o id do user
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
