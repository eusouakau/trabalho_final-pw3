package com.example.pense_responda.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String nome;
    private String email;

    public User() {}

    public User(String id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public User(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static List<User> inicializaLista(){
        List<User> users = new ArrayList<>();
        users.add(new User("Silvia", "20/12/2000", "Rua x, 10"));
        users.add(    new User("Maria", "20/11/2001", "Rua y, 20"));
        users.add(    new User("José", "20/10/2002", "Rua z, 30"));
        users.add(    new User("Mário", "20/12/2000", "Rua w, 40"));
        users.add(     new User("Juca", "20/11/2001", "Rua q, 50"));
        users.add(    new User("Jane", "20/10/2002", "Rua p, 60"));
        users.add(new User("Silvia", "20/12/2000", "Rua x, 10"));
        users.add(    new User("Maria", "20/11/2001", "Rua y, 20"));
        users.add(    new User("José", "20/10/2002", "Rua z, 30"));
        users.add(    new User("Mário", "20/12/2000", "Rua w, 40"));
        users.add(     new User("Juca", "20/11/2001", "Rua q, 50"));
        users.add(    new User("Jane", "20/10/2002", "Rua p, 60"));
        users.add(new User("Silvia", "20/12/2000", "Rua x, 10"));
        users.add(    new User("Maria", "20/11/2001", "Rua y, 20"));
        users.add(    new User("José", "20/10/2002", "Rua z, 30"));
        users.add(    new User("Mário", "20/12/2000", "Rua w, 40"));
        users.add(     new User("Juca", "20/11/2001", "Rua q, 50"));
        users.add(    new User("Jane", "20/10/2002", "Rua p, 60"));
        return users;
    }
}


