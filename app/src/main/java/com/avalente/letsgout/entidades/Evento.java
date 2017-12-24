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
    public String chat;



    public Evento(String id, String titulo, String descricao, String local, String categoria, String data, String chat)
    {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.local = local;
        this.categoria = categoria;
        this.data = data;
        this.boss = null;
        this.pessoas = null;
        this.chat = chat;
    }

    public Evento(String id, String titulo, String descricao, String local, User boss, String categoria, String data, String chat)
    {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.local = local;
        this.boss = boss;
        this.categoria = categoria;
        this.data = data;
        this.pessoas = null;
        this.chat = chat;
    }

    public Evento ()
    {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public User getBoss() {
        return boss;
    }

    public void setBoss(User boss) {
        this.boss = boss;
    }

    public ArrayList<User> getPessoas() {
        return pessoas;
    }

    public void setPessoas(ArrayList<User> pessoas) {
        this.pessoas = pessoas;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }
}
