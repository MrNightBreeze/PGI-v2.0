package com.avalente.letsgout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by avalente on 13/12/17.
 */

public class Feedback extends Fragment {

    private EditText edtSugestao;
    private EditText edtQueixa;
    private Button btnSugestao;
    private Button btnQueixa;

    DatabaseReference sugestoes;
    DatabaseReference queixas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.feedback, container, false);

        sugestoes = FirebaseDatabase.getInstance().getReference("sugestoes");
        queixas = FirebaseDatabase.getInstance().getReference("queixas");

        edtSugestao= (EditText) view.findViewById(R.id.editTextSugestoes);
        edtQueixa= (EditText) view.findViewById(R.id.editTextQueixas);

        btnQueixa = (Button) view.findViewById(R.id.buttonQueixa);
        btnSugestao = (Button) view.findViewById(R.id.buttonSugestao);

        btnQueixa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviaQueixa();
            }
        });

        btnSugestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviaSugestao();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Feedback");
    }

    public boolean isCampoVazio(String valor)
    {
        boolean res = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return res;
    }

    public void enviaSugestao()
    {
        String sugestao = edtSugestao.getText().toString();
        boolean res;

        if (res = isCampoVazio(sugestao))
        {
            edtSugestao.requestFocus();
            AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
            dlg.setTitle(R.string.aviso);
            dlg.setMessage(R.string.msgCampos);
            dlg.setNeutralButton(R.string.ok, null);
            dlg.show();
        }
        else
        {
            // cria o evento e adiciona na BD
            String ids = sugestoes.push().getKey();

            sugestoes.child(ids).setValue(sugestao);

            Toast.makeText(getActivity(), "Sugestao enviada", Toast.LENGTH_SHORT).show();
        }
    }

    public void enviaQueixa()
    {
        String queixa = edtQueixa.getText().toString();
        boolean res;

        if (res = isCampoVazio(queixa))
        {
            edtQueixa.requestFocus();
            AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
            dlg.setTitle(R.string.aviso);
            dlg.setMessage(R.string.msgCampos);
            dlg.setNeutralButton(R.string.ok, null);
            dlg.show();
        }
        else
        {
            // cria o evento e adiciona na BD
            String ids = queixas.push().getKey();

            queixas.child(ids).setValue(queixa);

            Toast.makeText(getActivity(), "Queixa enviada", Toast.LENGTH_SHORT).show();
        }
    }
}
