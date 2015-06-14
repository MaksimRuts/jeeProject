package by.gsu.epamlab.dao;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.exceptions.DataSourceException;

import java.util.List;

public interface IUserDao {
    boolean check(String login);
    void create(User user) throws DataSourceException;
    User get(String login, String password) throws DataSourceException;
    List<User> getAll();
}
