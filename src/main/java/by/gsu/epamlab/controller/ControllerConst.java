package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.daoimpl.NoteDaoMemory;
import by.gsu.epamlab.model.daoimpl.UserDaoMemory;

public class ControllerConst {
    public static class Pages {
        public static final String ERROR = "/error.jsp";
        public static final String LOGIN = "/login.jsp";
        public static final String TASKS = "/tasks.jsp";
    }

    public static class Controllers {
        public static final String TASKS = "/tasks";
        public static final String LOGOUT = "/logout";
        public static final String LOGIN = "/login";
    }

    public static class Fields {
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
        public static final String USERNAME = "username";
        public static final String ERROR_MESSAGE = "errorMessage";
        public static final String INFO_MESSAGE = "infoMessage";
    }

    public static class Actions {
        public static final String ACTION = "action";
        public static final String LOGIN = "login_button";
        public static final String REGISTER = "register_button";
    }

    public static class Errors {
        public static final String INVALID_LOGIN_OR_PASSWORD = "Invalid login or password";
        public static final String REGISTRATION = "Registration error";
        public static final String UNSUPPORTED = "Unsupported error. Don't panic and contact to developers";
    }

    public static class Messages {
        public static final String NOTES_LIST_EMPTY = "Your notes list is empty";
        public static final String REGISTRATION_SUCCESSFULLY_COMPLETED = "Registration successfully completed";
    }

    public static class Factories {
        public static final Class<UserDaoMemory> USER_DAO_CLASS = UserDaoMemory.class;
        public static final Class<NoteDaoMemory> NOTES_DAO_CLASS = NoteDaoMemory.class;
    }

//    public static enum TaskTypes {
//        TODAY("Today"), TOMORROW("Tomorrow"), SOMEDAY("Someday"), FIXED("Fixed"), RECYCLE_BIN("Recycle Bin");
//
//        private String value;
//
//        private TaskTypes(String value) {
//            this.value = value;
//        }
//
//        @Override
//        public String toString() {
//            return value;
//        }
//    }
}

