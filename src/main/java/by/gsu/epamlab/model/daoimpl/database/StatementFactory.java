package by.gsu.epamlab.model.daoimpl.database;

import by.gsu.epamlab.model.beans.TaskType;
import by.gsu.epamlab.model.connection.DataBaseConstants;

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
                stmt = connection.prepareStatement(DataBaseConstants.Queries.SELECT_TASKS_BY_TYPES_AND_DATA);
                stmt.setBoolean(2, taskType.isCompleted());
                stmt.setBoolean(3, taskType.isDeleted());
                stmt.setDate(4, taskType.getDateHigher());
                stmt.setDate(5, taskType.getDateBelow());
                break;
            case COMPLETED:
                stmt = connection.prepareStatement(DataBaseConstants.Queries.SELECT_TASKS_BY_TYPES);
                stmt.setBoolean(2, true);
                stmt.setBoolean(3, false);
                break;
            case RECYCLE_BIN:
                stmt = connection.prepareStatement(DataBaseConstants.Queries.SELECT_TASKS_DELETED);
                stmt.setBoolean(2, true);
                break;
            case ALL:
            default:
                stmt = connection.prepareStatement(DataBaseConstants.Queries.SELECT_ALL_TASKS);
                break;
        }
        stmt.setInt(1, userId);
        return stmt;
    }
}
