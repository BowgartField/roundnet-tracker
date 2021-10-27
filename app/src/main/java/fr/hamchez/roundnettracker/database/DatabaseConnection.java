package fr.hamchez.roundnettracker.database;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnection {

    private Connection connection;

    public Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            e.printStackTrace();
        }

        new Thread(() -> {

            try{

                connection = DriverManager.getConnection("jdbc:mysql://10.0.2.2:3306/tp2","root","");

            }catch(Exception e){
                e.printStackTrace();
                Log.e("[Roundnet] ", e.getMessage());
            }

        }).start();

        return connection;

    }

}
