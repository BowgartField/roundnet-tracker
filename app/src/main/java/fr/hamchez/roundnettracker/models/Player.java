package fr.hamchez.roundnettracker.models;

import java.io.Serializable;
import java.util.Random;

public class Player implements Serializable {

    private int id;
    private String firstname;
    private String lastname;

    private String username;
    private String email;
    private String password;
    private String birthday;
    private int idTeam;

    public Player(String username){
        this.username = username;
    }


    public Player(int id, String firstname, String lastname, String username, String email, String password, String birthday, int idTeam) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.idTeam = idTeam;
    }

    public Player(String firstname, String lastname, String username, String email, String password, String birthday, int idTeam) {
        this.id = new Random().nextInt(1000);
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.idTeam = idTeam;
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

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public String getName(){
        return firstname + " " + lastname;
    }
}
