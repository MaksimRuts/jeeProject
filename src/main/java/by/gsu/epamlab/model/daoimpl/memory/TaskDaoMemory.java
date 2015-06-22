package by.gsu.epamlab.model.daoimpl.memory;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.dao.ITaskDao;
import by.gsu.epamlab.model.exceptions.DataSourceException;
import by.gsu.epamlab.model.exceptions.ExceptionConstants;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskDaoMemory implements ITaskDao {
    private static Map<Integer, Map<String, Task>> notes;

        @Override
    public void create(Task task) throws DataSourceException {
        if (!notes.containsKey(task.getUserId())) {
            notes.put(task.getUserId(), new HashMap<String, Task>());
            notes.get(task.getUserId()).put(task.getName(), task);
        } else {
            if (!notes.get(task.getUserId()).containsKey(task.getName())) {
                notes.get(task.getUserId()).put(task.getName(), task);
            } else {
                throw new DataSourceException(ExceptionConstants.Messages.RECORD_ALREADY_EXIST);
            }
        }
    }

    @Override
    public Task read(int noteId, String name) throws DataSourceException {
        if (notes.containsKey(noteId) && notes.get(noteId).containsKey(name)) {
            return notes.get(noteId).get(name);
        } else {
            throw new DataSourceException(ExceptionConstants.Messages.RECORD_NOT_EXIST);
        }
    }

    @Override
    public void update(Task task) throws DataSourceException {
        if (notes.containsKey(task.getUserId()) &&
                notes.get(task.getUserId()).containsKey(task.getName())){
            notes.get(task.getUserId()).put(task.getName(), task);
        } else {
            throw new DataSourceException(ExceptionConstants.Messages.RECORD_UPDATE_ERROR);
        }
    }

    @Override
    public void delete(Task task) throws DataSourceException {
        try {
            if (notes.containsKey(task.getUserId())){
                if (!notes.get(task.getUserId()).remove(task.getName(), task)) {
                    throw new DataSourceException();
                }
            }
        } catch (Exception e) {
            throw new DataSourceException(ExceptionConstants.Messages.RECORD_DELETE_ERROR);
        }
    }

    @Override
    public List<Task> getAll(int userId) {
        return notes.containsKey(userId) ?
                new ArrayList<Task>(notes.get(userId).values()) : new ArrayList<Task>();
    }

    static {
        notes = new HashMap<Integer, Map<String, Task>>();

        Task task1 = new Task();

        task1.setName("Complete stage 2");
        task1.setDateEnding(Date.valueOf(LocalDate.now().plusDays(1)));
        task1.setTimeEnding(Time.valueOf(LocalTime.now()));
        task1.setUserId(1);
        task1.setDescription("Add notes handling");
        task1.setCompleted(false);
        task1.setDeleted(false);

        notes.put(task1.getUserId(), new HashMap<String, Task>());
        notes.get(task1.getUserId()).put(task1.getName(), task1);

        Task task2 = new Task();

        task2.setName("Complete stage 3");
        task2.setDateEnding(Date.valueOf(LocalDate.now().plusDays(4)));
        task2.setTimeEnding(Time.valueOf(LocalTime.now()));
        task2.setUserId(1);
        task2.setDescription("Add file management");
        task2.setCompleted(false);
        task2.setDeleted(false);

        notes.put(task2.getUserId(), new HashMap<String, Task>());
        notes.get(task2.getUserId()).put(task2.getName(), task2);
    }
}
