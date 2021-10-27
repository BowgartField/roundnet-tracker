package fr.hamchez.roundnettracker.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import at.favre.lib.crypto.bcrypt.BCrypt;
import fr.hamchez.roundnettracker.database.DatabaseConnection;
import fr.hamchez.roundnettracker.models.User;

public class UserDAO implements DAO<User>{

    DatabaseConnection databaseConnection = new DatabaseConnection();

    public User login(String username, String password){

        String hashedPassword = BCrypt.withDefaults().hashToString(12,password.toCharArray());

        return null;

    }

    @Override
    public User get(int id) {

        User user = null;

        try{

            PreparedStatement preparedStatement = databaseConnection.getConnection()
                    .prepareStatement("SELECT * FROM USER WHERE id = ?");
            preparedStatement.setInt(0,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname")
                );
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return user;

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User insert(User object) {
        return null;
    }

    @Override
    public User modify(User object) {
        return null;
    }

}
