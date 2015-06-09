package by.gsu.epamlab.dao;

import by.gsu.epamlab.beans.User;

import java.util.List;

public interface IUserDao {
    void add(User user);
    User get(String login, String password);
    boolean check(String login);
    List<User> getAll();
}
