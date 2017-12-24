package com.avalente.letsgout.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avalente on 12/12/17.
 */

public class User {
    public String id,firstName;
    public boolean desporto, entretenimento, artes, restaurantes, estudar;

    public User(String id,String fn, boolean d, boolean e, boolean a, boolean est, boolean r)
    {
        this.firstName = fn;
        this.id = id;
        this.desporto = d;
        this.entretenimento = e;
        this.artes = a;
        this.estudar = est;
        this.restaurantes = r;
    }

    public User()
    {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isDesporto() {
        return desporto;
    }

    public void setDesporto(boolean desporto) {
        this.desporto = desporto;
    }

    public boolean isEntretenimento() {
        return entretenimento;
    }

    public void setEntretenimento(boolean entretenimento) {
        this.entretenimento = entretenimento;
    }

    public boolean isArtes() {
        return artes;
    }

    public void setArtes(boolean artes) {
        this.artes = artes;
    }

    public boolean isRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(boolean restaurantes) {
        this.restaurantes = restaurantes;
    }

    public boolean isEstudar() {
        return estudar;
    }

    public void setEstudar(boolean estudar) {
        this.estudar = estudar;
    }
}
