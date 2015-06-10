package by.gsu.epamlab.testing;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.connection.ConnectionManager;
import by.gsu.epamlab.dao.IUserDao;
import by.gsu.epamlab.exceptions.DataSourceException;
import by.gsu.epamlab.logic.UserDaoDB;
import by.gsu.epamlab.logic.UserDaoFactory;

import java.util.Arrays;

public class TestUserDao {
    public static void main(String[] args) {
        IUserDao daoImpl = UserDaoFactory.getUserDao(UserDaoDB.class);
        User u1 = new User("qaz", "147");
        User u2 = new User("qwe", "123");

        System.out.println(Arrays.toString(daoImpl.getAll().toArray()));

        try {
            System.out.println(daoImpl.get("qwe"));
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
        try {
            daoImpl.add(u1);
        } catch (DataSourceException e) {
            System.err.println(e.toString());
        }
        try {
            daoImpl.add(u2);
        } catch (DataSourceException e) {
            System.err.println(e.toString());
        }

        System.out.println(Arrays.toString(daoImpl.getAll().toArray()));

        ConnectionManager.close();
    }
}
