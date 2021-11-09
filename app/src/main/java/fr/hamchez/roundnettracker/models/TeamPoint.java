package fr.hamchez.roundnettracker.models;

public class TeamPoint {

    private int id;
    private int idGame;
    private int idTeam;

    public TeamPoint(int id, int idGame, int idTeam) {
        this.id = id;
        this.idGame = idGame;
        this.idTeam = idTeam;
    }

    public int getId() {
        return id;
    }

    public int getIdGame() {
        return idGame;
    }

    public int getIdTeam() {
        return idTeam;
    }

    @Override
    public String toString() {
        return "TeamPoint{" +
                "id=" + id +
                ", idGame=" + idGame +
                ", idTeam=" + idTeam +
                '}';
    }
}
