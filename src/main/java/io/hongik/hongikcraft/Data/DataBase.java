package io.hongik.hongikcraft.Data;

import java.sql.*;

public class DataBase {
    private instance = Hongik.getInstance();
    
    private static Connection connection;

    private final String host = instance.getMessageDataFile().getString("dataBase.host");
    private final int port = instance.getMessageDataFile().getString("dataBase.port");
    private final String username = instance.getMessageDataFile().getString("dataBase.id");
    private final String password = instance.getMessageDataFile().getString("dataBase.pw");

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
