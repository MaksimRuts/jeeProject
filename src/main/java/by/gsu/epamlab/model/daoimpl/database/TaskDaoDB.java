package by.gsu.epamlab.model.daoimpl.database;

import by.gsu.epamlab.controller.TaskTypes;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.connection.ConnectionManager;
import by.gsu.epamlab.model.connection.DataBaseConstants;
import by.gsu.epamlab.model.dao.ITaskDao;
import by.gsu.epamlab.model.exceptions.DataSourceException;
import by.gsu.epamlab.model.exceptions.ExceptionConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoDB implements ITaskDao {

    @Override
    public void create(Task task) throws DataSourceException {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(DataBaseConstants.Queries.INSERT_INTO_TASKS);
            stmt.setInt(1, task.getUserId());
            stmt.setString(2, task.getName());
            stmt.setString(3, task.getDescription());
            stmt.setDate(4, task.getDateEnding());
            stmt.setTime(5, task.getTimeEnding());
            stmt.setBoolean(6, task.isCompleted());
            stmt.setBoolean(7, task.isDeleted());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataSourceException(ExceptionConstants.Messages.RECORD_ALREADY_EXIST);
        } finally {
            ConnectionManager.close(stmt);
            ConnectionManager.close(con);
        }
    }

    @Override
    public Task read(int noteId, String name) throws DataSourceException {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(DataBaseConstants.Queries.SELECT_FROM_TASKS);
            stmt.setInt(1, noteId);
            stmt.setString(2, name);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt(DataBaseConstants.TableColumns.ID));
                task.setUserId(rs.getInt(DataBaseConstants.TableColumns.USER_ID));
                task.setName(rs.getString(DataBaseConstants.TableColumns.NAME));
                task.setDescription(rs.getString(DataBaseConstants.TableColumns.TASK_DESCRIPTION));
                task.setDateEnding(rs.getDate(DataBaseConstants.TableColumns.TASK_DATE_ENDING));
                task.setTimeEnding(rs.getTime(DataBaseConstants.TableColumns.TASK_TIME_ENDING));
                task.setCompleted(rs.getBoolean(DataBaseConstants.TableColumns.TASK_COMPLETED));
                task.setDeleted(rs.getBoolean(DataBaseConstants.TableColumns.TASK_DELETED));
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
            stmt.setTime(4, task.getTimeEnding());
            stmt.setBoolean(5, task.isCompleted());
            stmt.setBoolean(6, task.isDeleted());
            stmt.setInt(7, task.getUserId());

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
        List<Task> tasks = new ArrayList<Task>();
        Connection con = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            stmt = con.prepareStatement(DataBaseConstants.Queries.SELECT_ALL_TASKS);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt(DataBaseConstants.TableColumns.ID));
                task.setUserId(rs.getInt(DataBaseConstants.TableColumns.USER_ID));
                task.setName(rs.getString(DataBaseConstants.TableColumns.NAME));
                task.setDescription(rs.getString(DataBaseConstants.TableColumns.TASK_DESCRIPTION));
                task.setDateEnding(rs.getDate(DataBaseConstants.TableColumns.TASK_DATE_ENDING));
                task.setTimeEnding(rs.getTime(DataBaseConstants.TableColumns.TASK_TIME_ENDING));
                task.setCompleted(rs.getBoolean(DataBaseConstants.TableColumns.TASK_COMPLETED));
                task.setDeleted(rs.getBoolean(DataBaseConstants.TableColumns.TASK_DELETED));
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

    @Override
    public List<Task> getAll(int userId, TaskTypes taskType) {
        return taskType.thinOutTasks(getAll(userId));
    }
}
