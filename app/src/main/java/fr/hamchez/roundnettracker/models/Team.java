package fr.hamchez.roundnettracker.models;

import java.io.Serializable;

public class Team implements Serializable {

    private int id;
    private String name;
    private String localisation;

    public Team(int id, String name, String localisation) {
        this.id = id;
        this.name = name;
        this.localisation = localisation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocalisation() {
        return localisation;
    }
}
