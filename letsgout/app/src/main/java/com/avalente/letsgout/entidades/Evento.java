package com.avalente.letsgout.entidades;

import java.util.ArrayList;

/**
 * Created by avalente on 05/12/17.
 */

public class Evento {

    public String id;
    public String titulo;
    public String descricao;
    public String local;
    public String data;
    public String categoria;
    public User boss;
    public ArrayList <User> pessoas;

    public Evento(String id, String titulo, String descricao, String local, String categoria, String data)
    {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.local = local;
        this.categoria = categoria;
        this.data = data;
        this.boss = null;
        this.pessoas = null;
    }

    public Evento(String id, String titulo, String descricao, String local, User boss, String categoria, String data)
    {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.local = local;
        this.boss = boss;
        this.categoria = categoria;
        this.data = data;
        this.pessoas = null;
    }

    public Evento ()
    {

    }
}
