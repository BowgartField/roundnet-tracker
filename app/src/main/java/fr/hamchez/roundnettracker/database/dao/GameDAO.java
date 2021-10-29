package fr.hamchez.roundnettracker.database.dao;

import java.util.List;

import fr.hamchez.roundnettracker.models.Game;

public class GameDAO implements DAO<Game>{

    @Override
    public Game get(int id) {
        return null;
    }

    @Override
    public List<Game> getAll() {
        return null;
    }

    @Override
    public boolean insert(Game object) {
        return false;
    }

    @Override
    public Game modify(Game object) {
        return null;
    }
}
