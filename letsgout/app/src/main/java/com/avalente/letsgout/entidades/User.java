package com.avalente.letsgout.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avalente on 12/12/17.
 */

public class User {
    public String id;
    public boolean desporto, entretenimento, artes, restaurantes, estudar;

    public User(String id, boolean d, boolean e, boolean a, boolean est, boolean r)
    {
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
}
