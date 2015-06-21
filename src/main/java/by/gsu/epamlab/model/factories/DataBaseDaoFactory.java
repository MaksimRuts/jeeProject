package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.dao.ITaskDao;
import by.gsu.epamlab.model.dao.IUserDao;
import by.gsu.epamlab.model.daoimpl.memory.TaskDaoDB;
import by.gsu.epamlab.model.daoimpl.database.UserDaoDB;

public class DataBaseDaoFactory extends AbstractDaoFactory {
    private IUserDao userDao = null;
    private ITaskDao taskDao = null;

    @Override
    public IUserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoDB();
        }
        return userDao;
    }

    @Override
    public ITaskDao getTaskDao() {
        if (taskDao == null) {
            taskDao = new TaskDaoDB();
        }
        return taskDao;
    }
}
