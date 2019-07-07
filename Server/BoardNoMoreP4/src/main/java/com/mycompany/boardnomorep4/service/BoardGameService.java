package com.mycompany.boardnomorep4.service;

import com.mycompany.boardnomorep4.db.DatabaseConnector;
import com.mycompany.boardnomorep4.db.DatabaseUtils;
import com.mycompany.boardnomorep4.model.BoardGame;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BoardGameService {
    
    private final static String ALL_BOARDGAMES_QUERY = "SELECT * FROM games";
    
    public static BoardGame getBoardGameById(int id) {
        //Get a new connection object before going forward with the JDBC invocation.
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_BOARDGAMES_QUERY + " WHERE gid = " + id);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    BoardGame boardGame = new BoardGame();

                    boardGame.setGid(resultSet.getInt("gid"));
                    boardGame.setName(resultSet.getString("name"));
                    boardGame.setPrice(resultSet.getInt("price"));
                    boardGame.setMinP(resultSet.getInt("minP"));
                    boardGame.setMaxP(resultSet.getInt("maxP"));
                    boardGame.setPlay_time(resultSet.getInt("play_time"));
                    boardGame.setAge(resultSet.getInt("age"));
                    boardGame.setDescription(resultSet.getString("description")); 

                    return boardGame;

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {

                    // We will always close the connection once we are done interacting with the Database.
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
        
        
        } //end getBoardGameById
    
    public static List<BoardGame> getAllBoardGames() {
        List<BoardGame> boardGames = new ArrayList<>();

        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_BOARDGAMES_QUERY);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    BoardGame boardGame = new BoardGame();

                    boardGame.setGid(resultSet.getInt("gid"));
                    boardGame.setName(resultSet.getString("name"));
                    boardGame.setPrice(resultSet.getInt("price"));
                    boardGame.setMinP(resultSet.getInt("minP"));
                    boardGame.setMaxP(resultSet.getInt("maxP"));
                    boardGame.setPlay_time(resultSet.getInt("play_time"));
                    boardGame.setAge(resultSet.getInt("age"));
                    boardGame.setDescription(resultSet.getString("description")); 

                    boardGames.add(boardGame);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return boardGames;
    }

    public static boolean AddGame(BoardGame game) {

        String sql = "INSERT INTO games  (name, price, minP, maxP, play_time, age, description)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DatabaseConnector.getConnection();
        return DatabaseUtils.performDBUpdate(connection, sql, game.getName(), Integer.toString(game.getPrice()), Integer.toString(game.getMinP()),
                Integer.toString(game.getMaxP()), Integer.toString(game.getPlay_time()), Integer.toString(game.getAge()), game.getDescription());

    }

    public static boolean updateGame(BoardGame game) {

        String sql = "UPDATE games SET name=?, price=?, minP=?, maxP=?, play_time=? age=? description=? WHERE gid=?;";

        Connection connection = DatabaseConnector.getConnection();

        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, game.getName(), String.valueOf(game.getPrice()), 
                String.valueOf(game.getMinP()), String.valueOf(game.getMaxP()), String.valueOf(game.getPlay_time()), 
                String.valueOf(game.getAge()), game.getDescription(), String.valueOf(game.getGid()));

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;

    }

    public static boolean deleteGame(BoardGame game) {

        String sql = "DELETE FROM games WHERE gid=?;";

        Connection connection = DatabaseConnector.getConnection();

        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(game.getGid()));

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;
    }
    
}
