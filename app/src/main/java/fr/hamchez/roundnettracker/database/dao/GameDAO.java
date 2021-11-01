package fr.hamchez.roundnettracker.database.dao;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;
import java.util.Random;

import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.database.RoundnetSQLite;
import fr.hamchez.roundnettracker.models.Game;

public class GameDAO implements DAO<Game>{

    RoundnetSQLite roundnetSQLite;

    private String TABLE_NAME = "game";

    private String ID = "id";
    private String ID_TEAM_ONE = "idTeamOne";
    private String ID_TEAM_TWO = "idTeamTwo";
    private String LOCALISATION = "localisation";

    public GameDAO(Context context){
        roundnetSQLite = new RoundnetSQLite(context);
    }

    @Override
    public Game get(int id) {
        return null;
    }

    @Override
    public List<Game> getAll() {
        return null;
    }

    @Override
    public boolean insert(Game game) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, new Random().nextInt(1000));
        contentValues.put(ID_TEAM_ONE, game.getIdTeamOne());
        contentValues.put(ID_TEAM_TWO, game.getIdTeamTwo());
        contentValues.put(LOCALISATION, game.getLocalisation());

        roundnetSQLite.getWritableDatabase().insert(TABLE_NAME,null, contentValues);

        return true;
    }

    @Override
    public Game modify(Game object) {
        return null;
    }
}
