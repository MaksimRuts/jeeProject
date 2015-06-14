package by.gsu.epamlab.logic;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.dao.IUserDao;
import by.gsu.epamlab.exceptions.ExceptionConstants;
import by.gsu.epamlab.exceptions.DataSourceException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoHardcode implements IUserDao {
    private static final Map<String, String> users;

    static {
        users = new HashMap<String, String>();
        users.put("qwe", "123");
        users.put("asd", "456");
        users.put("zxc", "789");
    }

    @Override
    public boolean check(String login) {
        return users.containsKey(login);
    }

    @Override
    public void create(User user) throws DataSourceException {
        if (!check(user.getLogin())) {
            users.put(user.getLogin(), user.getPassword());
        } else {
            throw new DataSourceException(ExceptionConstants.Messages.ERROR_USER_ALREADY_PRESENT);
        }

    }

    @Override
    public User get(String login, String password) throws DataSourceException {
        if (users.containsKey(login) && users.get(login).equals(password)) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(users.get(login));
            return user;
        } else {
            throw new DataSourceException(ExceptionConstants.Messages.ERROR_USER_REQUEST);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<User>();
        for (Map.Entry<String, String> entry: users.entrySet()) {
            list.add(new User(entry.getKey(), entry.getValue()));
        }
        return list;
    }
}
