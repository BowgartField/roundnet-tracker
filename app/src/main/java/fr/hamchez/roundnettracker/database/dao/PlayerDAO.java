package fr.hamchez.roundnettracker.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import at.favre.lib.crypto.bcrypt.BCrypt;
import fr.hamchez.roundnettracker.database.DatabaseConnection;
import fr.hamchez.roundnettracker.database.RoundnetSQLite;
import fr.hamchez.roundnettracker.models.Player;

public class PlayerDAO implements DAO<Player>{

    RoundnetSQLite roundnetSQLite;

    private String TABLE_NAME = "player";

    private String ID = "id";
    private String FIRSTNAME = "firstName";
    private String LASTNAME = "lastName";
    private String USERNAME = "userName";
    private String EMAIL = "email";
    private String BIRTHDAY = "birthday";
    private String ID_TEAM = "idTeam";
    private String PASSWORD = "password";

    public PlayerDAO(Context context){
        roundnetSQLite = new RoundnetSQLite(context);
    }

    public Player login(String username, String password){

        Player player = null;

        String[] values = {
          username
        };

        Cursor cursor = roundnetSQLite.getWritableDatabase().rawQuery("SELECT * FROM player WHERE username = ?",values);

        while(cursor.moveToNext()){

            String hashedPassword = cursor.getString(cursor.getColumnIndex(PASSWORD));

            BCrypt.Result result = BCrypt.verifyer(BCrypt.Version.VERSION_2A).verify(
                    password.getBytes(StandardCharsets.UTF_8),
                    hashedPassword.getBytes(StandardCharsets.UTF_8
            ));

            if(result.verified){
                player = new Player(
                        cursor.getInt(cursor.getColumnIndex(ID)),
                        cursor.getString(cursor.getColumnIndex(FIRSTNAME)),
                        cursor.getString(cursor.getColumnIndex(LASTNAME)),
                        cursor.getString(cursor.getColumnIndex(USERNAME)),
                        cursor.getString(cursor.getColumnIndex(EMAIL)),
                        null,
                        cursor.getString(cursor.getColumnIndex(BIRTHDAY)),
                        cursor.getInt(cursor.getColumnIndex(ID_TEAM))
                );
            }

        }

        cursor.close();

        return player;

    }

    @Override
    public Player get(int id) {
        return null;
    }

    public Player getPlayerByUsername(String username){

        Player player = null;

        String[] values = {
          username
        };

        Cursor cursor = roundnetSQLite.getWritableDatabase().rawQuery("SELECT * FROM player WHERE username = ?", values);

        while(cursor.moveToNext()){
            player = new Player(
                cursor.getInt(cursor.getColumnIndex(ID)),
                cursor.getString(cursor.getColumnIndex(FIRSTNAME)),
                cursor.getString(cursor.getColumnIndex(LASTNAME)),
                cursor.getString(cursor.getColumnIndex(USERNAME)),
                cursor.getString(cursor.getColumnIndex(EMAIL)),
                null,
                cursor.getString(cursor.getColumnIndex(BIRTHDAY)),
                cursor.getInt(cursor.getColumnIndex(ID_TEAM))
            );
        }

        cursor.close();
        return player;

    }

    @Override
    public List<Player> getAll() {
        return null;
    }

    @Override
    public boolean insert(Player player) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(ID,player.getId());
        contentValues.put(FIRSTNAME,player.getFirstname());
        contentValues.put(LASTNAME,player.getLastname());
        contentValues.put(EMAIL,player.getEmail());
        contentValues.put(USERNAME,player.getUsername());
        contentValues.put(BIRTHDAY,player.getBirthday());
        contentValues.put(ID_TEAM,player.getIdTeam());
        contentValues.put(PASSWORD,BCrypt.with(BCrypt.Version.VERSION_2A).hashToString(12,player.getPassword().toCharArray()));

        roundnetSQLite.getWritableDatabase().insert(TABLE_NAME,null,contentValues);

        return true;

    }

    @Override
    public Player modify(Player object) {
        return null;
    }

}
