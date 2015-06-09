package by.gsu.epamlab.logic;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.dao.IUserDao;

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
    public void add(User user) {
        users.put(user.getName(), user.getPassword());
    }

    @Override
    public User get(String login, String password) {
        if (users.containsKey(login)) {
            User user = new User();
            user.setName(login);
            user.setPassword(users.get(login));
            return user;
        }
        return null;
    }

    @Override
    public boolean check(String login) {
        return users.get(login) != null;
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
