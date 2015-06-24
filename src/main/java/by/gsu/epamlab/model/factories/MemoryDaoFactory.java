package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.dao.ITaskDao;
import by.gsu.epamlab.model.dao.IUserDao;
import by.gsu.epamlab.model.daoimpl.memory.TaskDaoMemory;
import by.gsu.epamlab.model.daoimpl.memory.UserDaoMemory;

public class MemoryDaoFactory extends AbstractDaoFactory {
    private IUserDao userDao = null;
    private ITaskDao taskDao = null;

    @Override
    public IUserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoMemory();
        }
        return userDao;
    }

    @Override
    public ITaskDao getTaskDao() {
        if (taskDao == null) {
            taskDao = new TaskDaoMemory();
        }
        return taskDao;
    }
}
