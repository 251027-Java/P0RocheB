package com.example.repository;

import com.example.Game;
import com.example.Move;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class PostgreSQLRepository{
    private static final String Postgre_URL = "jdbc:postgresql://localhost:5432:chessdb";
    private static final String Postgre_User = "postgres";
    private static final String Postgre_PW = "secret";
    private Connection connection;

    public PostgreSQLRepository() throws SQLException{
        try{
            connection = DriverManager.getConnection(Postgre_URL, Postgre_User, Postgre_PW);
            try (Statement stmt = connection.createStatement()) {
                String sql =
                        "CREATE SCHEMA IF NOT EXISTS ChessGames;" +
                        "CREATE TABLE IF NOT EXISTS ChessGames.games (" +
                        "game_id INT PRIMARY KEY," +
                        "date TIMESTAMP NOT NULL," +
                        "white VARCHAR(50) NOT NULL," +
                        "black VARCHAR(50) NOT NULL," +
                        "event VARCHAR(100) NOT NULL," +
                        "result VARCHAR(10) NOT NULL" +
                        ");" +  
                        "CREATE TABLE IF NOT EXISTS ChessGames.move ( " +
                        "move_number INT NOT NULL, " +
                        "game_id INT NOT NULL, " +
                        "color VARCHAR(5),  " +
                        "notation VARCHAR (10)," +
                        "PRIMARY KEY (move_number, game_id)" +
                        ");" +
                        "CREATE TABLE IF NOT EXISTS ChessGames.notation ( " +
                        "notation VARCHAR (10) PRIMARY KEY, " +
                        "piece VARCHAR (10) NOT NULL, " +
                        "square VARCHAR (2) NOT NULL," +
                        "isCheck boolean NOT NULL, " +
                        "isMate boolean NOT NULL, " +
                        "isShortCastle boolean NOT NULL," + 
                        "isLongCastle boolean NOT NULL, " +
                        "isCapture boolean NOT NULL,  " +
                        "isPromotion boolean NOT NULL " +
                        ");" + 
                        "ALTER TABLE ChessGames.move " +
                        "ADD FOREIGN KEY (game_id) REFERENCES ChessGames.games(game_id) ON DELETE CASCADE; " +
                        "ALTER TABLE ChessGames.move " +
                        "ADD FOREIGN KEY (notation) REFERENCES ChessGames.notation(notation); " +
                        ";";
                stmt.execute(sql);
                System.out.println("Successful creation of PostgreSQl database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createGame(Game game) {
        String sql =
                "INSERT INTO ChessGames.games (game_id, date, white, black, event, result)" +
                "VALUES (?, ?, ?, ?, ?, ?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, game.getID());
            stmt.setDate(2, game.getDate());
            stmt.setString(3, game.getWhite());
            stmt.setString(4, game.getBlack());
            stmt.setString(5, game.getEvent());
            stmt.setString(6, game.getResult());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Game readGame(int id) {
        String sql = "SELECT * FROM ChessGames.moves WHERE moves.game_id = ?;";
        List<Move> moves = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Move move = new Move(
                    rs.getInt("move_number"),
                    rs.getString("color"),
                    rs.getString("notaion"));
                moves.add(move);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        sql = "SELECT * FROM ChessGames.games WHERE games.game_id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                Game game = new Game(
                        rs.getInt("game_id"),
                        moves,
                        rs.getString("white"),
                        rs.getString("black"),
                        rs.getString("result"),
                        rs.getString("event"),
                        rs.getDate("date")
                );
                return game;
            }
            System.out.println("No games with that ID");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateExpense(Game game) {

    }

    public void deleteGame(int id) {
        if (readGame(id) == null){ return; }
        String sql =
                "DELETE FROM ChessGames.games WHERE games.game_id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Game> loadGames() {
        List<Game> ret = new ArrayList<>();
        String sql = "SELECT * FROM ChessGames.games";
        try (Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()){
                ret.add(readGame(rs.getInt("game_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void saveGames(List<Game> games) {

    }

    public void clearRepo() {
        try (Statement stmt = connection.createStatement()) {
            String sql = "TRUNCATE TABLE ChessGames.games";
            stmt.execute(sql);
        } catch (SQLException e) {e.printStackTrace();}
    }
}
