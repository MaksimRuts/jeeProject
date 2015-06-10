package by.gsu.epamlab.connection;

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
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(DataBaseConstants.DataBase.DATABASE_URL, DataBaseConstants.DataBase.DATABASE_LOGIN, DataBaseConstants.DataBase.DATABASE_PASSWORD);
        } catch (ReflectiveOperationException e) {
            // TODO
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO
            e.printStackTrace();
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
                // TODO
                e.printStackTrace();
            }
        }
        return stmtPool.get(query);
    }

    public static void close() {
        for (Map.Entry<String, PreparedStatement> entry : stmtPool.entrySet()) {
            if (entry.getValue() != null) {
                try {
                    entry.getValue().close();
                } catch (SQLException e) {
                    // todo
                    e.printStackTrace();
                }
            }
        }
        stmtPool.clear();

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // todo
                e.printStackTrace();
            }
        }
    }
}
