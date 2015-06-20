package by.gsu.epamlab.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public final class ConnectionManager {

    private static Connection connection;

    private static Map<String, PreparedStatement> stmtPool = new HashMap<String, PreparedStatement>();

    static {
        try {
            Class.forName(DataBaseConstants.DataBase.DRIVER_URI).newInstance();
            connection = DriverManager.getConnection(DataBaseConstants.DataBase.DATABASE_URL,
                    DataBaseConstants.DataBase.DATABASE_LOGIN,
                    DataBaseConstants.DataBase.DATABASE_PASSWORD);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionManager() {
    }

    public static Connection getConnection() {
        return connection;
    }

    public static PreparedStatement getPreparedStatement(String query) {
        if (!stmtPool.containsKey(query)) {
            try {
                stmtPool.put(query, getConnection().prepareStatement(query));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return stmtPool.get(query);
    }

    public static void close() {
        try {
            for (Map.Entry<String, PreparedStatement> entry : stmtPool.entrySet()) {
                if (entry.getValue() != null) {

                        entry.getValue().close();

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            stmtPool.clear();
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
