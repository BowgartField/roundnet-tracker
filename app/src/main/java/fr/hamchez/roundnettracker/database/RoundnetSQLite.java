package fr.hamchez.roundnettracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.database.dao.PlayerDAO;
import fr.hamchez.roundnettracker.models.Player;

public class RoundnetSQLite extends SQLiteOpenHelper {

    Context context;

    public RoundnetSQLite(Context context) {
        super(context, "Roundnet", null, 1);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    private void createTables(SQLiteDatabase db){

        StringBuilder buffer = new StringBuilder();

        try(InputStream is = context.getResources().openRawResource(R.raw.create_tables)){

            // the stream holding the file content
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            String line;

            while ((line = bufferedReader.readLine()) != null) {

                buffer.append(line);

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        // Arrays.asList(buffer.toString().split(";")).forEach(System.out::println);
        Arrays.asList(buffer.toString().replace("\n","").split(";")).forEach(db::execSQL);

    }

    public void createTestPlayers(){

        SQLiteDatabase db = this.getWritableDatabase();

        new PlayerDAO(context).insert(new Player(
                "Jean-Benoît",
                "Richez",
                "jbrichez",
                "jerichez02@gmail.com",
                "test",
                null,
                0
        ));

    }
}
