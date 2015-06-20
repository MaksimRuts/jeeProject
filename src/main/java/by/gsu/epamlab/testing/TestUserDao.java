package by.gsu.epamlab.testing;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.connection.ConnectionManager;
import by.gsu.epamlab.model.dao.IUserDao;
import by.gsu.epamlab.model.daoimpl.UserDaoFactory;
import by.gsu.epamlab.model.daoimpl.UserDaoMemory;
import by.gsu.epamlab.model.exceptions.DataSourceException;

import java.util.Arrays;

public class TestUserDao {
    public static void main(String[] args) {
        IUserDao daoImpl = UserDaoFactory.getUserDao(UserDaoMemory.class);
        User u1 = new User("qaz", "147");
        User u2 = new User("qwe", "123");

        System.out.println(Arrays.toString(daoImpl.getAll().toArray()));

        try {
            System.out.println(daoImpl.get("qwe", "123"));
        } catch (DataSourceException e) {
            e.printStackTrace();
        }

        try {
            daoImpl.create(u1);
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
        try {
            daoImpl.create(u2);
        } catch (DataSourceException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(daoImpl.getAll().toArray()));

        ConnectionManager.close();
    }
}
