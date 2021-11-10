package fr.hamchez.roundnettracker.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.database.DatabaseConnection;
import fr.hamchez.roundnettracker.database.RoundnetSQLite;
import fr.hamchez.roundnettracker.models.Game;

public class GameDAO implements DAO<Game>{

    RoundnetSQLite roundnetSQLite;

    private String TABLE_NAME = "game";

    private String ID = "id";
    private String ID_TEAM_ONE = "idTeamOne";
    private String ID_TEAM_TWO = "idTeamTwo";
    private String LOCALISATION = "localisation";
    private String FINISHED = "finished";


    public GameDAO(Context context){
        roundnetSQLite = new RoundnetSQLite(context);
    }

    public void saveGame(){

        List<Game> toBackup = getAll();
        Connection connection = new DatabaseConnection().getConnection();

        try{

            for(int i = 0; i < 5; i++){

                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO game(id, idTeamOne, idTeamTwo, localisation) VALUES(?,?,?,?)"
                );

                preparedStatement.setInt(0, toBackup.get(i).getId());
                preparedStatement.setInt(1, toBackup.get(i).getIdTeamOne());
                preparedStatement.setInt(2, toBackup.get(i).getIdTeamTwo());
                preparedStatement.setString(3, toBackup.get(i).getLocalisation());

                preparedStatement.executeQuery();

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Game getLiveGame(){

        Game game = null;
        Cursor cursor = roundnetSQLite.getWritableDatabase().rawQuery("SELECT * FROM game WHERE finished = ?", new String[]{ "false" });

        while (cursor.moveToNext()){
            game = new Game(
                cursor.getInt(cursor.getColumnIndex(ID)),
                cursor.getInt(cursor.getColumnIndex(ID_TEAM_ONE)),
                cursor.getInt(cursor.getColumnIndex(ID_TEAM_TWO)),
                cursor.getString(cursor.getColumnIndex(LOCALISATION))
            );
        }

        cursor.close();
        return game;

    }

    @Override
    public Game get(int id) {

        Game game;

        Cursor cursor = roundnetSQLite.getReadableDatabase().rawQuery("SELECT * FROM game WHERE id = ?",new String[]{  });

        game = getGameFromCursor(cursor);
        cursor.close();

        return game;
    }

    @Override
    public List<Game> getAll() {

        List<Game> gameList = new ArrayList<>();

        Cursor cursor = roundnetSQLite.getReadableDatabase().rawQuery("SELECT * FROM game",new String[]{});

        while (cursor.moveToNext()){
            gameList.add(
                new Game(
                        cursor.getInt(cursor.getColumnIndex(ID)),
                        cursor.getInt(cursor.getColumnIndex(ID_TEAM_ONE)),
                        cursor.getInt(cursor.getColumnIndex(ID_TEAM_TWO)),
                        cursor.getString(cursor.getColumnIndex(LOCALISATION)),
                        Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(FINISHED))
                )
            ));
        }

        cursor.close();
        return gameList;


    }

    @Override
    public boolean insert(Game game) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, new Random().nextInt(1000));
        contentValues.put(ID_TEAM_ONE, game.getIdTeamOne());
        contentValues.put(ID_TEAM_TWO, game.getIdTeamTwo());
        contentValues.put(LOCALISATION, game.getLocalisation());
        contentValues.put(FINISHED, "false");

        roundnetSQLite.getWritableDatabase().insert(TABLE_NAME,null, contentValues);

        return true;
    }

    @Override
    public Game modify(Game object) {
        return null;
    }

    private Game getGameFromCursor(Cursor cursor){
        return new Game(
            cursor.getInt(cursor.getColumnIndex(ID)),
            cursor.getInt(cursor.getColumnIndex(ID_TEAM_ONE)),
            cursor.getInt(cursor.getColumnIndex(ID_TEAM_TWO)),
            cursor.getString(cursor.getColumnIndex(LOCALISATION))
        );
    }

    public void finished(int idGame){

        ContentValues contentValues = new ContentValues();
        contentValues.put(FINISHED,"true");

        roundnetSQLite.getWritableDatabase().update(
            TABLE_NAME,
            contentValues,
            "id = ?",
            new String[]{String.valueOf(idGame)}
        );

    }

}
