package fr.hamchez.roundnettracker.database.dao;

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

            String hashedPassword = cursor.getString(cursor.getColumnIndex("password"));

            BCrypt.Result result = BCrypt.verifyer(BCrypt.Version.VERSION_2A).verify(
                    password.getBytes(StandardCharsets.UTF_8),
                    hashedPassword.getBytes(StandardCharsets.UTF_8
            ));

            if(result.verified){
                player = new Player(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("firstName")),
                        cursor.getString(cursor.getColumnIndex("lastName"))
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

    @Override
    public List<Player> getAll() {
        return null;
    }

    @Override
    public Player insert(Player object) {
        return null;
    }

    @Override
    public Player modify(Player object) {
        return null;
    }

}
