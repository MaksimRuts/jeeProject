package by.gsu.epamlab.model.daoimpl.memory;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.IUserDao;
import by.gsu.epamlab.model.exceptions.DataSourceException;
import by.gsu.epamlab.model.exceptions.ExceptionConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoMemory implements IUserDao {
    private static final Map<User, String> users;
    private static int id = 1;

    static {
        users = new HashMap<User, String>();
        users.put(new User(generateId(), "qwe", "Qwe"), "123");
        users.put(new User(generateId(), "asd", "Asd"), "asd");
        users.put(new User(generateId(), "zxc", "Zxc"), "zxc");
    }

    private static int generateId() {
        return id++;
    }

    @Override
    public User create(User user, String password) throws DataSourceException {
        if (!users.containsKey(user)) {
            synchronized (users) {
                user.setId(generateId());
                users.put(user, password);
            }
            return user;
        } else {
            throw new DataSourceException(ExceptionConstants.Messages.ERROR_USER_ALREADY_PRESENT);
        }
    }

    @Override
    public User read(String login, String password) throws DataSourceException {
        for (User user : users.keySet()) {
            if (user.getLogin().equals(login)) {
                if (users.get(user).equals(password)) {
                    return user;
                }
            }
        }
        throw new DataSourceException(ExceptionConstants.Messages.ERROR_USER_REQUEST);
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<User>();
        for (Map.Entry<User, String> entry: users.entrySet()) {
            list.add(entry.getKey());
        }
        return list;
    }
}
