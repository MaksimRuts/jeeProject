package by.gsu.epamlab.model.dao;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exceptions.DataSourceException;

import java.util.List;

public interface IUserDao {
    User create(User user, String password) throws DataSourceException;
    User read(String login, String password) throws DataSourceException;
    List<User> getAll();
}
