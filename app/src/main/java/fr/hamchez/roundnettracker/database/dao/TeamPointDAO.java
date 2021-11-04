package fr.hamchez.roundnettracker.database.dao;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import fr.hamchez.roundnettracker.database.RoundnetSQLite;
import fr.hamchez.roundnettracker.models.TeamPoint;

public class TeamPointDAO implements DAO<TeamPoint> {

    RoundnetSQLite roundnetSQLite;

    public TeamPointDAO(Context context){
        roundnetSQLite = new RoundnetSQLite(context);
    }

    public List<TeamPoint> getFromTeamId(int id){
        return new ArrayList<>();
    }

    @Override
    public TeamPoint get(int id) {
        return null;
    }

    @Override
    public List<TeamPoint> getAll() {
        return new ArrayList<>();
    }

    @Override
    public boolean insert(TeamPoint object) {
        return false;
    }

    @Override
    public TeamPoint modify(TeamPoint object) {
        return null;
    }
}
