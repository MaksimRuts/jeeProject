package by.gsu.epamlab.model.connection;

public class DataBaseConstants {
    public static class DataBase {

        public static final String DRIVER_URI = "com.mysql.jdbc.Driver";
        public static final String DATABASE_URL = "jdbc:mysql://localhost/jee4";
        public static final String DATABASE_LOGIN = "root";
        public static final String DATABASE_PASSWORD = "";
    }

    public static class TableColumns {
        public static final String ID = "id";
        public static final String PASSWORD = "password";
        public static final String LOGIN = "login";
        public static final String USER_ID = "user_id";
        public static final String NAME = "name";
        public static final String TASK_DESCRIPTION = "description";
        public static final String TASK_DATE_ENDING = "date_ending";
        public static final String TASK_TIME_ENDING = "time_ending";
        public static final String TASK_COMPLETED = "completed";
        public static final String TASK_DELETED = "deleted";
        public static final String FILEPATH = "filepath";
    }

    public static class Queries {
        public static final String SELECT_USER_BY_LOGIN = "select * from users where login = ?";
        public static final String INSERT_USER = "insert into users (login, password, name) values (?, ?, ?)";
        public static final String SELECT_ALL_USERS = "select * from users";

        public static final String INSERT_INTO_TASKS = "insert into tasks (user_id, name, description, date_ending, completed, deleted, filepath) values (?, ?, ?, ?, ?, ?, ?)";
        public static final String SELECT_TASK = "select * from tasks where user_id = ? and id = ?";
        public static final String UPDATE_TASK = "update tasks set name = ?, description = ?, date_ending = ?, completed = ?, deleted = ?, filepath = ? where user_id = ? and id = ?";
        public static final String DELETE_TASK = "delete from tasks where user_id = ? and name = ?";
        public static final String SELECT_ALL_TASKS = "select * from tasks where user_id = ?";
        public static final String SELECT_TASKS_BY_TYPES = "select * from tasks where user_id = ? and completed = ? and deleted = ?";
        public static final String SELECT_TASKS_BY_TYPES_AND_DATA = "select * from tasks where user_id = ? and completed = ? and deleted = ? and date_ending <= ? and date_ending >= ?";
        public static final String SELECT_TASKS_BY_TYPES_AND_DATA_AFTER = "select * from tasks where user_id = ? and completed = ? and deleted = ? and date_ending > ?";
    }
}
