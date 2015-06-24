package by.gsu.epamlab.model.dao;

import by.gsu.epamlab.controller.TaskTypes;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.exceptions.DataSourceException;

import java.util.List;

public interface ITaskDao {
    void create(Task task) throws DataSourceException;
    Task read(int noteId, String name) throws DataSourceException;
    void update(Task task) throws DataSourceException;
    void delete(Task task) throws DataSourceException;
    List<Task> getAll(int userId);
    List<Task> getAll(int userId, TaskTypes taskType);
}
