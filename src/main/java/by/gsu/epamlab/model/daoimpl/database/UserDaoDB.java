package by.gsu.epamlab.model.daoimpl.database;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.connection.ConnectionManager;
import by.gsu.epamlab.model.connection.DataBaseConstants;
import by.gsu.epamlab.model.dao.IUserDao;
import by.gsu.epamlab.model.exceptions.DataSourceException;
import by.gsu.epamlab.model.exceptions.ExceptionConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoDB implements IUserDao {

    @Override
    public User create(User user, String password) throws DataSourceException {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement stmtGet = null;
        PreparedStatement stmtSet = null;
        ResultSet rs = null;

        try {
            stmtGet = con.prepareStatement(DataBaseConstants.Queries.SELECT_USER_BY_LOGIN);
            stmtSet = con.prepareStatement(DataBaseConstants.Queries.INSERT_USER);

            stmtGet.setString(1, user.getLogin());
            rs = stmtGet.executeQuery();
            if (!rs.next()) {
                stmtSet.setString(1, user.getLogin());
                stmtSet.setString(2, password);
                stmtSet.setString(3, user.getName());
                synchronized (stmtSet) {
                    stmtSet.executeUpdate();
                }
                rs = stmtGet.executeQuery();
                if (rs.next()) {
                    User newUser = new User();
                    newUser.setLogin(rs.getString(DataBaseConstants.TableColumns.LOGIN));
                    newUser.setName(rs.getString(DataBaseConstants.TableColumns.NAME));
                    newUser.setId(rs.getInt(DataBaseConstants.TableColumns.ID));
                    return new User(rs.getInt(DataBaseConstants.TableColumns.ID),
                            rs.getString(DataBaseConstants.TableColumns.LOGIN),
                            rs.getString(DataBaseConstants.TableColumns.NAME));
                } else {
                    throw new DataSourceException(ExceptionConstants.Messages.ERROR_USER_CREATING);
                }
            } else {
                throw new DataSourceException(ExceptionConstants.Messages.ERROR_USER_ALREADY_PRESENT);
            }
        } catch (SQLException e) {
            throw new DataSourceException(ExceptionConstants.Messages.ERROR_USER_CREATING, e);
        } finally {
            ConnectionManager.close(rs);
            ConnectionManager.close(stmtSet, stmtGet);
            ConnectionManager.close(con);
        }
    }

    @Override
    public User read(String login, String password) throws DataSourceException {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(DataBaseConstants.Queries.SELECT_USER_BY_LOGIN);
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            if (rs.next()) {
                if (password.equals(rs.getString(DataBaseConstants.TableColumns.PASSWORD))) {
                    return new User(rs.getInt(DataBaseConstants.TableColumns.ID),
                            rs.getString(DataBaseConstants.TableColumns.LOGIN),
                            rs.getString(DataBaseConstants.TableColumns.NAME));
                }
            }
            throw new DataSourceException(ExceptionConstants.Messages.ERROR_USER_REQUEST);
        } catch (SQLException e) {
            throw new DataSourceException(ExceptionConstants.Messages.ERROR_USER_REQUEST, e);
        } finally {
            ConnectionManager.close(rs);
            ConnectionManager.close(stmt);
            ConnectionManager.close(con);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<User>();
        Connection con = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(DataBaseConstants.Queries.SELECT_ALL_USERS);
            rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(DataBaseConstants.TableColumns.ID));
                user.setLogin(rs.getString(DataBaseConstants.TableColumns.LOGIN));
                user.setName(rs.getString(DataBaseConstants.TableColumns.NAME));
                list.add(user);
            }
            rs.close();
            return list;
        } catch (SQLException e) {
            throw new DataSourceException(ExceptionConstants.Messages.ERROR_USER_REQUEST, e);
        } finally {
            ConnectionManager.close(rs);
            ConnectionManager.close(stmt);
            ConnectionManager.close(con);
        }
    }
}
