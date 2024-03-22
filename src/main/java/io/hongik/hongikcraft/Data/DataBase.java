package io.hongik.hongikcraft.Data;

import java.sql.*;

public class DataBase {
    private static Connection connection;

    private final String host = "127.0.0.1";
    private final int port = 3306;
    private final String username = "main_";
    private final String password = "03023";

    public Connection openConnection(){
        try {
            if (connection != null && !connection.isClosed()) {
                return null;
            }

            synchronized (this) {
                if (connection != null && !connection.isClosed()) {
                    return null;
                }
                Class.forName("com.mysql.jdbc.Driver");
                String url = String.format("jdbc:mysql://%s:%d/jdbc?&user=%s&password=%s", host, port, username, password);
                connection = DriverManager.getConnection(url);
            }
        }
        catch (Exception e) {
            return null;
        }
        return connection;
    }

    public static String getDatabaseString(String query, String colum) throws SQLException {
        String data = null;
        try(Connection conn = new DataBase().openConnection(); ResultSet rs = conn.prepareStatement(query).executeQuery()){
            if (rs.next() && !rs.getString(colum).isEmpty()) data = rs.getString(colum);
        }

        return data;
    }

    public static void insertDataBaseString(String query) throws SQLException {
        try (Connection conn = new DataBase().openConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.executeUpdate();
        }
    }
}
