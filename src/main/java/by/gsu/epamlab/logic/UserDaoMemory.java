package by.gsu.epamlab.logic;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.dao.IUserDao;
import by.gsu.epamlab.exceptions.ExceptionConstants;
import by.gsu.epamlab.exceptions.DataSourceException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoMemory implements IUserDao {
    private static final Map<String, User> users;

    static {
        users = new HashMap<String, User>();
        users.put("qwe", new User("qwe", "123"));
        users.put("asd", new User("asd", "123"));
        users.put("zxc", new User("zxc", "123"));
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
        if (users.containsKey(login) && users.get(login).equals(password)) {
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
