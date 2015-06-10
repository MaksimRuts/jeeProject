package by.gsu.epamlab.logic;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.connection.ConnectionManager;
import by.gsu.epamlab.connection.DataBaseConstants;
import by.gsu.epamlab.dao.IUserDao;
import by.gsu.epamlab.exceptions.DataSourceException;
import by.gsu.epamlab.exceptions.ExceptionConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoDB implements IUserDao {

    @Override
    public void add(User user) throws DataSourceException {
        try {
            get(user.getLogin());
        } catch (DataSourceException e) {
            PreparedStatement stmt = ConnectionManager.getPreparedStatement(DataBaseConstants.Queries.INSERT_USER);
            try {
                stmt.setString(1, user.getLogin());
                stmt.setString(2, user.getPassword());
                stmt.executeUpdate();
            } catch (SQLException sqlEx) {
                throw new DataSourceException(ExceptionConstants.Messages.ERROR_USER_CREATING);
            }
            return;
        }
        throw new DataSourceException(ExceptionConstants.Messages.ERROR_USER_ALREADY_PRESENT);
    }

    @Override
    public User get(String login) throws DataSourceException {
        PreparedStatement stmt = ConnectionManager.getPreparedStatement(DataBaseConstants.Queries.SELECT_BY_LOGIN);
        try {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            User user = new User();
            user.setLogin(login);
            user.setPassword(rs.getString(DataBaseConstants.DataBase.COLUMN_PASSWORD));
            return user;
        } catch (SQLException e) {
            throw new DataSourceException(ExceptionConstants.Messages.ERROR_REQUEST_USER);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<User>();
        PreparedStatement stmt = ConnectionManager.getPreparedStatement(DataBaseConstants.Queries.SELECT_ALL_USERS);
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getString(DataBaseConstants.DataBase.COLUMN_LOGIN),
                        rs.getString(DataBaseConstants.DataBase.COLUMN_PASSWORD)));
            }
            return list;
        } catch (SQLException e) {
            return list;
        }
    }
}
