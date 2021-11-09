package fr.hamchez.roundnettracker.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.hamchez.roundnettracker.database.RoundnetSQLite;
import fr.hamchez.roundnettracker.models.TeamPoint;

public class TeamPointDAO implements DAO<TeamPoint> {

    RoundnetSQLite roundnetSQLite;

    private String TABLE_NAME = "points";

    private String ID = "id";
    private String ID_GAME = "idGame";
    private String ID_TEAM = "idTeam";

    public TeamPointDAO(Context context){
        roundnetSQLite = new RoundnetSQLite(context);
    }

    public void remove(int id){
        roundnetSQLite.getWritableDatabase().rawQuery("DELETE FROM points WHERE id = ?", new String[]{ String.valueOf(id) });
    }

    public List<TeamPoint> getFromTeamId(int id){

        List<TeamPoint> teamPointList = new ArrayList<>();

        Cursor cursor = roundnetSQLite.getReadableDatabase().rawQuery("SELECT * FROM points WHERE idGame = ?", new String[]{String.valueOf(id)});

        while(cursor.moveToNext()){
            teamPointList.add(new TeamPoint(
               cursor.getInt(cursor.getColumnIndex(ID)),
               cursor.getInt(cursor.getColumnIndex(ID_GAME)),
                cursor.getInt(cursor.getColumnIndex(ID_TEAM))
            ));
        }

        return teamPointList;
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
    public boolean insert(TeamPoint teamPoint) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,teamPoint.getId());
        contentValues.put(ID_GAME,teamPoint.getIdGame());
        contentValues.put(ID_TEAM, teamPoint.getIdTeam());

        roundnetSQLite.getWritableDatabase().insert(TABLE_NAME,null,contentValues);

        return true;

    }

    @Override
    public TeamPoint modify(TeamPoint object) {
        return null;
    }


}
