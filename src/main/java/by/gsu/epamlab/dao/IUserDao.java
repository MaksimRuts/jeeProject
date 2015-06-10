package by.gsu.epamlab.dao;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.exceptions.DataSourceException;

import java.util.List;

public interface IUserDao {
    void add(User user) throws DataSourceException;
    User get(String login) throws DataSourceException;
    List<User> getAll();
}
