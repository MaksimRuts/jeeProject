package by.gsu.epamlab.model.daoimpl.database;

import by.gsu.epamlab.model.beans.TaskType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatementFactory {
    public static PreparedStatement getInstanceForTasks(TaskType taskType, int userId, Connection connection) throws SQLException {
        PreparedStatement stmt;
        switch (taskType) {
            case TODAY:
            case TOMORROW:
            case SOMEDAY:
                stmt = connection.prepareStatement("select * from tasks where user_id = ? and completed = ? and deleted = ? and date_ending <= ? and date_ending >= ?");
                stmt.setBoolean(2, taskType.isCompleted());
                stmt.setBoolean(3, taskType.isDeleted());
                stmt.setDate(4, taskType.getDateHigher());
                stmt.setDate(5, taskType.getDateBelow());
                break;
            case COMPLETE:
                stmt = connection.prepareStatement("select * from tasks where user_id = ? and completed = ? and deleted = ?");
                stmt.setBoolean(2, true);
                stmt.setBoolean(3, false);
                break;
            case RECYCLE_BIN:
                stmt = connection.prepareStatement("select * from tasks where user_id = ? and deleted = ?");
                stmt.setBoolean(2, true);
                break;
            case ALL:
            default:
                stmt = connection.prepareStatement("select * from tasks where user_id = ?");
                break;
        }
        stmt.setInt(1, userId);
        return stmt;
    }
}
