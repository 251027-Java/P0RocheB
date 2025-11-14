package com.example.repository;

import com.example.Game;

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
                        "event VARCHAR(100) NOT NULL" +
                        "result VARCHAR(10)" +
                        ");" + 
                        "CREATE TABLE" +
                        "";
                stmt.execute(sql);
                System.out.println("Successful creation of H2 database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createGame(Game game) {
        String sql =
                "INSERT INTO ExpenseReport.Expenses (id, date, price, merchant)" +
                        "VALUES ( ?, ?, ?, ?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            //stmt.setInt(1, );
            //stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Game readGame(int id) {
        String sql =
                "SELECT * FROM ExpenseReport.Expenses WHERE Expenses.id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                /*
                Game game = new Game(
                        rs.getInt("id"),
                        new java.util.Date(rs.getTimestamp("date").getTime()),
                        rs.getDouble("price"),
                        rs.getString("merchant")
                );
                */
                return null;
            }
            System.out.println("No expense with that ID");
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
                "DELETE FROM ExpenseReport.Expenses WHERE Expenses.id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Game> loadExpenses() {
        List<Game> ret = new ArrayList<>();
        String sql = "SELECT * FROM ExpenseReport.Expenses";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()){
                /*
                Game game = new Game(
                        rs.getInt("id"),
                        new java.util.Date(rs.getTimestamp("date").getTime()),
                        rs.getDouble("price"),
                        rs.getString("merchant")
                );
                ret.add(expense);
                */
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void saveExpenses(List<Game> games) {

    }

    public void clearRepo() {
        try (Statement stmt = connection.createStatement()) {
            String sql = "TRUNCATE TABLE ExpenseReport.Expenses";
            stmt.execute(sql);
        } catch (SQLException e) {e.printStackTrace();}
    }
}
