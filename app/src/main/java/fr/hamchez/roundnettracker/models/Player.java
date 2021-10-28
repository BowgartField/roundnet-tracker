package fr.hamchez.roundnettracker.models;

import java.io.Serializable;

public class Player implements Serializable {

    private int id;
    private String firstname;
    private String lastname;


    public Player(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getName(){
        return firstname + " " + lastname;
    }
}
