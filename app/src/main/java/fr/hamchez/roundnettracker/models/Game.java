package fr.hamchez.roundnettracker.models;

public class Game {

    private int id;
    private int idTeamOne;
    private int idTeamTwo;
    private String localisation;

    public Game(int id, int idTeamOne, int idTeamTwo, String localisation) {
        this.id = id;
        this.idTeamOne = idTeamOne;
        this.idTeamTwo = idTeamTwo;
        this.localisation = localisation;
    }

    public int getId() {
        return id;
    }

    public int getIdTeamOne() {
        return idTeamOne;
    }

    public int getIdTeamTwo() {
        return idTeamTwo;
    }

    public String getLocalisation() {
        return localisation;
    }
}
