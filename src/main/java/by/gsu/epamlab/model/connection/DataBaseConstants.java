package by.gsu.epamlab.model.connection;

public class DataBaseConstants {
    public static class DataBase {

        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_LOGIN = "login";
        public static final String DRIVER_URI = "com.mysql.jdbc.Driver";
        public static final String DATABASE_URL = "jdbc:mysql://localhost/jee2";
        public static final String DATABASE_LOGIN = "root";
        public static final String DATABASE_PASSWORD = "";
    }

    public static class TableColumns {
        public static final String ID = "id";
        public static final String USER_ID = "user_id";
        public static final String TASK_NAME = "name";
        public static final String TASK_DESCRIPTION = "description";
        public static final String TASK_DATE_ENDING = "date_ending";
        public static final String TASK_TIME_ENDING = "time_ending";
        public static final String TASK_COMPLETED = "completed";
        public static final String TASK_DELETED = "deleted";
    }

    public static class Queries {
        public static final String SELECT_BY_LOGIN = "select * from users where login = ?";
        public static final String SELECT_USER = "select * from users where login = ? and password = ?";
        public static final String INSERT_USER = "insert into users (login, password) values (?, ?)";
        public static final String SELECT_ALL_USERS = "select * from users";

        public static final String INSERT_INTO_TASKS = "insert into tasks (user_id, name, description, date_ending, time_ending, completed, deleted) values (?, ?, ?, ?, ?, ?, ?)";
        public static final String SELECT_FROM_TASKS = "select * from tasks where user_id = ? and name = ?";
        public static final String UPDATE_TASK = "update tasks set name = ?, description = ?, date_ending = ?, time_ending = ?, completed = ?, deleted = ? where user_id = ?";
        public static final String DELETE_TASK = "delete from tasks where user_id = ? and name = ?";
        public static final String SELECT_ALL_TASKS = "select * from tasks where user_id = ?";
    }
}