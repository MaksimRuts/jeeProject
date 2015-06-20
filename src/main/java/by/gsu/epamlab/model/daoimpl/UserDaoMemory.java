package by.gsu.epamlab.model.daoimpl;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.IUserDao;
import by.gsu.epamlab.model.exceptions.ExceptionConstants;
import by.gsu.epamlab.model.exceptions.DataSourceException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoMemory implements IUserDao {
    private static final Map<String, User> users;
    private static int id = 1;

    static {
        users = new HashMap<String, User>();
        users.put("qwe", new User(getId(), "qwe", "123"));
        users.put("asd", new User(getId(), "asd", "123"));
        users.put("zxc", new User(getId(), "zxc", "123"));
    }

    private static int getId() {
        return id++;
    }

    @Override
    public boolean check(String login) {
        return users.containsKey(login);
    }

    @Override
    public void create(User user) throws DataSourceException {
        if (!check(user.getLogin())) {

            users.put(user.getLogin(), user);
        } else {
            throw new DataSourceException(ExceptionConstants.Messages.ERROR_USER_ALREADY_PRESENT);
        }
    }

    @Override
    public User get(String login, String password) throws DataSourceException {
        if (users.containsKey(login) && users.get(login).getPassword().equals(password)) {
            return users.get(login);
        } else {
            throw new DataSourceException(ExceptionConstants.Messages.ERROR_USER_REQUEST);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<User>();
        for (Map.Entry<String, User> entry: users.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }
}
