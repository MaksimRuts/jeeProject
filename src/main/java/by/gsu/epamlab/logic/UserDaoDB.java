package by.gsu.epamlab.logic;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.dao.IUserDao;

import java.util.List;

public class UserDaoDB implements IUserDao {

    @Override
    public void add(User user) {

    }

    @Override
    public User get(String login, String password) {
        return null;
    }

    @Override
    public boolean check(String login) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
