package com.example.repository;

import com.example.Game;
import com.example.Move;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class PostgreSQLRepository{
    private static final String Postgre_URL = "jdbc:postgresql://localhost:5432/postgres";
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
                        "CREATE TABLE IF NOT EXISTS ChessGames.moves ( " +
                        "move_number INT NOT NULL, " +
                        "game_id INT NOT NULL, " +
                        "color VARCHAR(5),  " +
                        "notation VARCHAR (10)," +
                        "PRIMARY KEY (move_number, game_id)" +
                        ");" +
                        "CREATE TABLE IF NOT EXISTS ChessGames.notations ( " +
                        "notation VARCHAR (10) PRIMARY KEY, " +
                        "piece VARCHAR (10), " +
                        "square VARCHAR (2)," +
                        "isCheck boolean, " +
                        "isMate boolean, " +
                        "isShortCastle boolean," + 
                        "isLongCastle boolean, " +
                        "isCapture boolean,  " +
                        "isPromotion boolean " +
                        ");" + 
                        "ALTER TABLE ChessGames.moves " +
                        "ADD FOREIGN KEY (game_id) REFERENCES ChessGames.games(game_id) ON DELETE CASCADE; " +
                        "ALTER TABLE ChessGames.moves " +
                        "ADD FOREIGN KEY (notation) REFERENCES ChessGames.notations(notation); " +
                        ";";
                stmt.execute(sql);
                System.out.println("Successful connection to PostgreSQl database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getBestMove(List<String> moves){
        try (Statement stmt = connection.createStatement()) {
            int moveNumber = moves.size() + 1;
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT move").append(moveNumber).append(".notation, COUNT(*) AS wins ");
            sql.append("FROM chessgames.moves move1 ");
            for(int i = 2; i < moveNumber+1; i++){
                sql.append("JOIN chessgames.moves move").append(i).append(" ");
                sql.append("ON move").append(i-1).append(".game_id = move").append(i).append(".game_id ");
            }
            sql.append("JOIN chessgames.games g on g.game_id = move1.game_id ");
            sql.append("WHERE move1.move_number = 1 ");
            for(int i = 0; i < moves.size(); i++){
                sql.append("AND move").append(i+1).append(".notation = \'").append(moves.get(i)).append("\' ");
                sql.append("AND move").append(i+2).append(".move_number = ").append(i+2).append(" ");
            }
            sql.append("AND g.result = \'");
            if(moveNumber % 2 == 1)sql.append("1-0\' ");
            else sql.append("0-1\' ");
            sql.append("GROUP BY move").append(moveNumber).append(".notation ");
            sql.append("ORDER BY wins DESC LIMIT 1;");
            ResultSet rs = stmt.executeQuery(sql.toString());
            rs.next();
            return rs.getString("notation");
        } catch (SQLException e) {e.printStackTrace();}
        

        return "Couldn't find best move";
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
        createMoves(game.getID(), game.getMoves());
    }

    public void createMoves(int id, List<Move> moves){
        String sql =
            "INSERT INTO ChessGames.moves (game_id, move_number, color, notation)" +
            "VALUES (?, ?, ?, ?)";
        for(Move move : moves){
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.setInt(2, move.getMoveNumber());
                stmt.setString(3, move.getColor());
                stmt.setString(4, move.getNotation());
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Unable to add moves to database");
            }
        }
    }

    public void createNotations(Move move){
        if(move.getPiece() == null){
            String sql =
            "INSERT INTO ChessGames.notations (notation) VALUES (?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, move.getNotation());
                stmt.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
            }
            return;
        }
        String sql =
            "INSERT INTO ChessGames.notations (notation, piece, square, isCheck, isMate, isShortCastle, isLongCastle, isCapture, isPromotion)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, move.getNotation());
            stmt.setString(2, move.getPiece());
            stmt.setString(3, move.getSquare());
            stmt.setBoolean(4, move.isCheck());
            stmt.setBoolean(5, move.isMate());
            stmt.setBoolean(6, move.isShortCastle());
            stmt.setBoolean(7, move.isLongCastle());
            stmt.setBoolean(8, move.isCapture());
            stmt.setBoolean(9, move.isPromotion());
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
                    rs.getString("notation"));
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

    public void clearRepo() {
        try (Statement stmt = connection.createStatement()) {
            String sql = "TRUNCATE TABLE ChessGames.games";
            stmt.execute(sql);
        } catch (SQLException e) {e.printStackTrace();}
    }

    public boolean gamesIsEmpty(){
        try (Statement stmt = connection.createStatement()) {
            String sql = "SELECT COUNT(*) from ChessGames.games";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getInt("count") == 0;
        } catch (SQLException e) {e.printStackTrace();}
        return false;
    }

    public boolean notationsIsEmpty(){
        try (Statement stmt = connection.createStatement()) {
            String sql = "SELECT COUNT(*) from ChessGames.notations";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getInt("count") == 0;
        } catch (SQLException e) {e.printStackTrace();}
        return false;
    }

    public boolean isNotation(String notation){
        if (notation == null) return false;
        String sql =
                "SELECT COUNT(*) FROM ChessGames.notations WHERE notations.notation = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, notation);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("count") != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
