package fr.hamchez.roundnettracker.models;

public class Game {

    private int id;
    private int idTeamOne;
    private int idTeamTwo;
    private String localisation;
    private boolean finished;

    public Game(int id, int idTeamOne, int idTeamTwo, String localisation) {
        this.id = id;
        this.idTeamOne = idTeamOne;
        this.idTeamTwo = idTeamTwo;
        this.localisation = localisation;
        this.finished = false;
    }

    public Game(int id, int idTeamOne, int idTeamTwo, String localisation, boolean finished) {
        this.id = id;
        this.idTeamOne = idTeamOne;
        this.idTeamTwo = idTeamTwo;
        this.localisation = localisation;
        this.finished = finished;
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

    public boolean isFinished() {
        return finished;
    }
}
