package by.gsu.epamlab.testing;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.IUserDao;
import by.gsu.epamlab.model.exceptions.DataSourceException;
import by.gsu.epamlab.model.factories.AbstractDaoFactory;
import by.gsu.epamlab.model.factories.MemoryDaoFactory;

import java.util.Arrays;

public class TestUserDao {
    public static void main(String[] args) {
        IUserDao daoImpl = AbstractDaoFactory.getFactory(MemoryDaoFactory.class).getUserDao();

        User u1 = new User("qaz", "Qaz");
        User u2 = new User("qwe", "Qwe");

        System.out.println(Arrays.toString(daoImpl.getAll().toArray()));

        try {
            System.out.println(daoImpl.read("qwe", "123"));
        } catch (DataSourceException e) {
            e.printStackTrace();
        }

        try {
            daoImpl.create(u1, "123");
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
        try {
            daoImpl.create(u2, "234");
        } catch (DataSourceException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(daoImpl.getAll().toArray()));
    }
}
