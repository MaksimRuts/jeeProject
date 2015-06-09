package by.gsu.epamlab.testing;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.dao.IUserDao;
import by.gsu.epamlab.logic.UserDaoFactory;
import by.gsu.epamlab.logic.UserDaoHardcode;

import java.util.Arrays;

public class TestUserDaoHardcode {
    public static void main(String[] args) {
        IUserDao daoImpl = UserDaoFactory.getUserDao(UserDaoHardcode.class);
        User u1 = new User("qaz", "147");
        User u2 = new User("qwe", "123");

        System.out.println(Arrays.toString(daoImpl.getAll().toArray()));

        System.out.println(daoImpl.get("qwe", "123"));

        if (!daoImpl.check(u1.getName())) {
            daoImpl.add(u1);
        }
        if (!daoImpl.check(u2.getName())) {
            daoImpl.add(u2);
        }

        System.out.println(Arrays.toString(daoImpl.getAll().toArray()));
    }
}
