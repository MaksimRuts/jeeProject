package by.gsu.epamlab.model.daoimpl.database;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.connection.ConnectionManager;
import by.gsu.epamlab.model.connection.DataBaseConstants;
import by.gsu.epamlab.model.dao.IUserDao;
import by.gsu.epamlab.model.exceptions.DataSourceException;
import by.gsu.epamlab.model.exceptions.ExceptionConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoDB implements IUserDao {

    @Override
    public boolean check(String login) {
        PreparedStatement stmt = ConnectionManager.getPreparedStatement(DataBaseConstants.Queries.SELECT_BY_LOGIN);
        try {
            stmt.setString(1, login);
            return stmt.executeQuery().next();
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public void create(User user) throws DataSourceException {
        if (!check(user.getLogin())) {
            PreparedStatement stmt = ConnectionManager.getPreparedStatement(DataBaseConstants.Queries.INSERT_USER);
            try {
                stmt.setString(1, user.getLogin());
                stmt.setString(2, user.getPassword());
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new DataSourceException(ExceptionConstants.Messages.ERROR_USER_CREATING, e);
            }
        } else {
            throw new DataSourceException(ExceptionConstants.Messages.ERROR_USER_ALREADY_PRESENT);
        }
    }

    @Override
    public User get(String login, String password) throws DataSourceException {
        PreparedStatement stmt = ConnectionManager.getPreparedStatement(DataBaseConstants.Queries.SELECT_USER);
        try {
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            User user = new User();
            user.setLogin(login);
            user.setPassword(rs.getString(DataBaseConstants.DataBase.COLUMN_PASSWORD));
            user.setId(rs.getInt(DataBaseConstants.TableColumns.ID));
            rs.close();
            return user;
        } catch (SQLException e) {
            throw new DataSourceException(ExceptionConstants.Messages.ERROR_USER_REQUEST, e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<User>();
        PreparedStatement stmt = ConnectionManager.getPreparedStatement(DataBaseConstants.Queries.SELECT_ALL_USERS);
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setLogin(rs.getString(DataBaseConstants.DataBase.COLUMN_LOGIN));
                user.setPassword(rs.getString(DataBaseConstants.DataBase.COLUMN_PASSWORD));
                user.setId(rs.getInt(DataBaseConstants.TableColumns.ID));
                list.add(user);
            }
            rs.close();
            return list;
        } catch (SQLException e) {
            return list;
        }
    }
}
