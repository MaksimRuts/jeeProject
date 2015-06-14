package by.gsu.epamlab.connection;

public class DataBaseConstants {
    public static class DataBase {

        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_LOGIN = "login";
        static final String DATABASE_URL = "jdbc:mysql://localhost/jee1";
        static final String DATABASE_LOGIN = "root";
        static final String DATABASE_PASSWORD = "";
    }

    public static class Queries {

        public static final String SELECT_BY_LOGIN = "select * from users where login = ?";
        public static final String SELECT_USER = "select * from users where login = ? and password = ?";
        public static final String INSERT_USER = "insert into users (login, password) values (?, ?)";
        public static final String SELECT_ALL_USERS = "select * from users";
    }
}
