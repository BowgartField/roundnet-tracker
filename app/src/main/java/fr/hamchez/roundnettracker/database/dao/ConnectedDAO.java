package fr.hamchez.roundnettracker.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import fr.hamchez.roundnettracker.database.RoundnetSQLite;
import fr.hamchez.roundnettracker.models.Connected;
import fr.hamchez.roundnettracker.models.Player;

public class ConnectedDAO implements DAO<Player>{

    private String USERNAME = "userName";

    private RoundnetSQLite roundnetSQLite;
    private PlayerDAO playerDAO;

    public ConnectedDAO(Context context){
        roundnetSQLite = new RoundnetSQLite(context);
        playerDAO = new PlayerDAO(context);
    }


    @Override
    public Player get(int id) {
        return null;
    }

    @Override
    public List<Player> getAll() {

        List<Player> listConnected = new ArrayList<>();

        Cursor cursor = roundnetSQLite.getWritableDatabase().rawQuery("SELECT * FROM connected",null);

        while(cursor.moveToNext()){
            listConnected.add(playerDAO.getPlayerByUsername(cursor.getString(cursor.getColumnIndex(USERNAME))));
        }

        cursor.close();
        return listConnected;

    }

    @Override
    public boolean insert(Player player) {

        Boolean result = false;

        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME,player.getUsername());

        roundnetSQLite.getWritableDatabase().insert("connected",null,contentValues);

        result = true;

        return result;

    }

    @Override
    public Player modify(Player object) {
        return null;
    }

}
