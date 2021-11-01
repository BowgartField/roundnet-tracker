package fr.hamchez.roundnettracker.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.database.RoundnetSQLite;
import fr.hamchez.roundnettracker.models.Team;

public class TeamDAO implements DAO<Team> {

    RoundnetSQLite roundnetSQLite;

    private String TABLE_NAME = "team";

    private String ID = "id";
    private String NAME = "name";
    private String LOCALISATION = "localisation";

    public TeamDAO(Context context){
        roundnetSQLite = new RoundnetSQLite(context);
    }

    @Override
    public Team get(int id) {
        return null;
    }

    @Override
    public List<Team> getAll() {

        List<Team> teamList = new ArrayList<>();

        Cursor cursor = roundnetSQLite.getReadableDatabase().rawQuery("SELECT * FROM team",null);

        while(cursor.moveToNext()){
            teamList.add(new Team(
                    cursor.getInt(cursor.getColumnIndex(ID)),
                    cursor.getString(cursor.getColumnIndex(NAME)),
                    cursor.getString(cursor.getColumnIndex(LOCALISATION))
                )
            );
        }

        cursor.close();
        return teamList;
    }

    @Override
    public boolean insert(Team team) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,new Random().nextInt(1000));
        contentValues.put(NAME,team.getName());
        contentValues.put(LOCALISATION,team.getLocalisation());

        roundnetSQLite.getWritableDatabase().insert(TABLE_NAME,null,contentValues);

        return true;
    }

    @Override
    public Team modify(Team object) {
        return null;
    }
}
