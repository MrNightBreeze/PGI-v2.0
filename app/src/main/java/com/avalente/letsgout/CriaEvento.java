package com.avalente.letsgout;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.avalente.letsgout.entidades.Evento;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CriaEvento extends AppCompatActivity {

    private EditText edtTitulo;
    private EditText edtDescricao;
    private EditText edtLocal;
    private EditText edtData;

    DatabaseReference eventos;

    Spinner spinnerCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        eventos = FirebaseDatabase.getInstance().getReference("eventos");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_evento);

        edtTitulo = (EditText)findViewById(R.id.edtTitulo);
        edtLocal= (EditText)findViewById(R.id.edtLocal);
        edtDescricao= (EditText)findViewById(R.id.edtDescricao);
        edtData= (EditText)findViewById(R.id.edtData);

        spinnerCategorias = (Spinner) findViewById(R.id.spinnerCategorias);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_cria_evento, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean isCampoVazio(String valor)
    {
        boolean res = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return res;
    }

    public void validaCampos()
    {
        String titulo = edtTitulo.getText().toString();
        String local = edtLocal.getText().toString();
        String descricao = edtDescricao.getText().toString();
        String data = edtData.getText().toString();
        String categoria = spinnerCategorias.getSelectedItem().toString();
        String chat = "messages";

        boolean res;

        if (res = isCampoVazio(titulo))
        {
            edtTitulo.requestFocus();
        }
        else
        {
            if (res = isCampoVazio(descricao))
            {
                edtDescricao.requestFocus();
            }
            else
            {
                if (res = isCampoVazio(local))
                {
                    edtLocal.requestFocus();
                }
                else
                {
                    if (res = isCampoVazio(data))
                    {
                        edtData.requestFocus();
                    }
                    else
                    {
                        // cria o evento e adiciona na BD
                        String id = eventos.push().getKey();
                        Evento evento = new Evento(id, titulo, descricao, local, categoria, data, chat);
                        
                        eventos.child(id).setValue(evento);

                        Toast.makeText(this, "Evento Criado", Toast.LENGTH_SHORT).show();

                        finish();
                    }
                }
            }
        }
        if (res)
        {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.aviso);
            dlg.setMessage(R.string.msgCampos);
            dlg.setNeutralButton(R.string.ok, null);
            dlg.show();

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.ok_btn:
                validaCampos();
                break;

            case R.id.cancelar_btn:
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
