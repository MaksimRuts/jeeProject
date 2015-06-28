package by.gsu.epamlab.model.connection;

import java.sql.*;

public final class ConnectionManager {

    static {
        try {
            Class.forName(DataBaseConstants.DataBase.DRIVER_URI).newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionManager() {
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DataBaseConstants.DataBase.DATABASE_URL,
                    DataBaseConstants.DataBase.DATABASE_LOGIN,
                    DataBaseConstants.DataBase.DATABASE_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(ResultSet... sets) {
        for (ResultSet resultSet : sets) {
            if (resultSet != null) {
                try {
                    if (!resultSet.isClosed()) {
                        resultSet.close();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void close(PreparedStatement... preparedStatements) {
        for (PreparedStatement stmt : preparedStatements) {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
