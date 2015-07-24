package by.gsu.epamlab.model.daoimpl.database;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.TaskTypes;
import by.gsu.epamlab.model.connection.ConnectionManager;
import by.gsu.epamlab.model.connection.DataBaseConstants;
import by.gsu.epamlab.model.dao.ITaskDao;
import by.gsu.epamlab.model.exceptions.DataSourceException;
import by.gsu.epamlab.model.exceptions.ExceptionConstants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoDB implements ITaskDao {

    @Override
    public void create(Task task) throws DataSourceException {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement stmtSet = null;
        PreparedStatement stmtGet = null;
        ResultSet rs = null;
        try {
            stmtGet = con.prepareStatement(DataBaseConstants.Queries.SELECT_TASK);
            stmtGet.setInt(1, task.getUserId());
            stmtGet.setString(2, task.getName());
            rs = stmtGet.executeQuery();
            if (!rs.next()) {
                stmtSet = con.prepareStatement(DataBaseConstants.Queries.INSERT_INTO_TASKS);
                stmtSet.setInt(1, task.getUserId());
                stmtSet.setString(2, task.getName());
                stmtSet.setString(3, task.getDescription());
                stmtSet.setDate(4, task.getDateEnding());
                stmtSet.setBoolean(5, task.isCompleted());
                stmtSet.setBoolean(6, task.isDeleted());
                stmtSet.setString(7, task.getFilename());
                stmtSet.executeUpdate();
            } else {
                throw new DataSourceException(ExceptionConstants.Messages.RECORD_ALREADY_EXIST);
            }
        } catch (SQLException e) {
            throw new DataSourceException(ExceptionConstants.Messages.RECORD_CREATE_ERROR);
        } finally {
            ConnectionManager.close(rs);
            ConnectionManager.close(stmtSet, stmtGet);
            ConnectionManager.close(con);
        }
    }

    @Override
    public Task read(int userId, int taskId) throws DataSourceException {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(DataBaseConstants.Queries.SELECT_TASK);
            stmt.setInt(1, userId);
            stmt.setInt(2, taskId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt(DataBaseConstants.TableColumns.ID));
                task.setUserId(rs.getInt(DataBaseConstants.TableColumns.USER_ID));
                task.setName(rs.getString(DataBaseConstants.TableColumns.NAME));
                task.setDescription(rs.getString(DataBaseConstants.TableColumns.TASK_DESCRIPTION));
                task.setDateEnding(rs.getDate(DataBaseConstants.TableColumns.TASK_DATE_ENDING));
                task.setCompleted(rs.getBoolean(DataBaseConstants.TableColumns.TASK_COMPLETED));
                task.setDeleted(rs.getBoolean(DataBaseConstants.TableColumns.TASK_DELETED));
                task.setFilename(rs.getString(DataBaseConstants.TableColumns.FILEPATH));
                return task;
            } else {
                throw new DataSourceException(ExceptionConstants.Messages.RECORD_NOT_EXIST);
            }
        } catch (SQLException e) {
            throw new DataSourceException(ExceptionConstants.Messages.RECORD_NOT_EXIST);
        } finally {
            ConnectionManager.close(rs);
            ConnectionManager.close(stmt);
            ConnectionManager.close(con);
        }
    }

    @Override
    public void update(Task task) throws DataSourceException {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(DataBaseConstants.Queries.UPDATE_TASK);
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDescription());
            stmt.setDate(3, task.getDateEnding());
            stmt.setBoolean(4, task.isCompleted());
            stmt.setBoolean(5, task.isDeleted());
            stmt.setString(6, task.getFilename());
            stmt.setInt(7, task.getUserId());
            stmt.setInt(8, task.getId());

            synchronized (stmt) {
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataSourceException(ExceptionConstants.Messages.RECORD_UPDATE_ERROR);
        } finally {
            ConnectionManager.close(stmt);
            ConnectionManager.close(con);
        }
    }

    @Override
    public void delete(Task task) throws DataSourceException {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(DataBaseConstants.Queries.DELETE_TASK);
            stmt.setInt(1, task.getUserId());
            stmt.setString(2, task.getName());
            synchronized (stmt) {
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataSourceException(ExceptionConstants.Messages.RECORD_DELETE_ERROR);
        } finally {
            ConnectionManager.close(stmt);
            ConnectionManager.close(con);
        }
    }

    @Override
    public List<Task> getAll(int userId) {
        return getAll(userId, TaskTypes.ALL);
    }

    @Override
    public List<Task> getAll(int userId, TaskTypes taskType) {
        List<Task> tasks = new ArrayList<Task>();
        Connection con = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Date date = taskType.getDateBelow();

        try{
            stmt = con.prepareStatement(DataBaseConstants.Queries.SELECT_TASKS_BY_TYPES_AND_DATA);
            stmt.setInt(1, userId);
            stmt.setBoolean(2, taskType.isCompleted());
            stmt.setBoolean(3, taskType.isDeleted());
            stmt.setDate(4, taskType.getDateHigher());
            stmt.setDate(5, taskType.getDateBelow());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt(DataBaseConstants.TableColumns.ID));
                task.setUserId(rs.getInt(DataBaseConstants.TableColumns.USER_ID));
                task.setName(rs.getString(DataBaseConstants.TableColumns.NAME));
                task.setDescription(rs.getString(DataBaseConstants.TableColumns.TASK_DESCRIPTION));
                task.setDateEnding(rs.getDate(DataBaseConstants.TableColumns.TASK_DATE_ENDING));
                task.setCompleted(rs.getBoolean(DataBaseConstants.TableColumns.TASK_COMPLETED));
                task.setDeleted(rs.getBoolean(DataBaseConstants.TableColumns.TASK_DELETED));
                task.setFilename(rs.getString(DataBaseConstants.TableColumns.FILEPATH));
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException e) {
            throw new DataSourceException(ExceptionConstants.Messages.RECORD_READING_ERROR);
        } finally {
            ConnectionManager.close(rs);
            ConnectionManager.close(stmt);
            ConnectionManager.close(con);
        }
    }
}
