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
    public void add(User user) throws DataSourceException {
        try {
            get(user.getLogin());
        } catch (DataSourceException e) {
            users.put(user.getLogin(), user.getPassword());
            return;
        }
        throw new DataSourceException(ExceptionConstants.Messages.ERROR_USER_ALREADY_PRESENT);
    }

    @Override
    public User get(String login) throws DataSourceException {
        if (users.containsKey(login)) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(users.get(login));
            return user;
        }
        throw new DataSourceException(ExceptionConstants.Messages.ERROR_REQUEST_USER);
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
