package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.factories.AbstractDaoFactory;
import by.gsu.epamlab.model.factories.DataBaseDaoFactory;

import javax.servlet.ServletContext;
import java.io.File;

public class ControllerConst {
    public static final Class<? extends AbstractDaoFactory> FACTORY = DataBaseDaoFactory.class;
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    public static final String FILEPATH = "filepath";
//    public static final Class<? extends AbstractDaoFactory> FACTORY = MemoryDaoFactory.class;

    public static class Pages {
        public static final String ERROR = "/error.jsp";
        public static final String LOGIN = "/login.jsp";
        public static final String TASKS = "/tasks.jsp";
        public static final String REGISTRATION = "/registration.jsp";
        public static final String ADD_TASK = "/addTask.jsp";
        public static final String EDIT_TASK = "/editTask.jsp";
    }

    public static class Controllers {
        public static final String TASKS = "/tasks";
        public static final String LOGOUT = "/logout";
        public static final String LOGIN = "/login";
        public static final String ACTION = "/action";
        public static final String ADD_TASK = "/addTask";
        public static final String EDIT_TASK = "/editTask";
        public static final String MANAGE_TASK = "/manageTask";
        public static final String DOWNLOAD_FILE = "/download";

    }

    public static class Fields {
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
        public static final String NAME = "name";
        public static final String ERROR_MESSAGE = "errorMessage";
        public static final String USER = "user";
        public static final String TASK_TYPE = "taskType";
        public static final String TASKS_LIST = "tasksList";
        public static final String SELECT = "select";
        public static final String TASK = "task";
        public static final String TASK_NAME = "taskName";
        public static final String TASK_DATE = "taskDate";
        public static final String TASK_DESCRIPTION = "taskDescription";
        public static final String BACK_PAGE = "backPage";
        public static final String TASK_ID = "taskId";
        public static final String FILE = "file";
        public static final String FILE_PATH = '/' + FilePath.UPLOAD_RELATIVE_PATH + '/';
    }

    public static class Actions {
        public static final String ACTION = "action";
        public static final String LOGIN = "login_button";
        public static final String REGISTER = "register_button";

        public static final String ADD = "add";
        public static final String EDIT = "edit";
        public static final String REMOVE = "remove";
        public static final String COMPLETE = "complete";
        public static final String CONFIRM = "confirm";
        public static final String RESTORE = "restore";
        public static final String REMOVE_ALL = "removeAll";
        public static final String REMOVE_FILE = "removeFile";
        public static final String DOWNLOAD_FILE = "downloadFile";
    }

    public static class Errors {
        public static final String EMPTY_FIELDS = "Empty fields";
        public static final String INVALID_LOGIN_OR_PASSWORD = "Invalid login or password";
        public static final String REGISTRATION = "Registration error";
        public static final String UNSUPPORTED = "Unsupported error. Don't panic and contact to developers";
        public static final String TASKS_REQUEST_ERROR = "Tasks request error";
    }

    public static class Messages {
        public static final String NOTES_LIST_EMPTY = "You don't have tasks for ";
        public static final String REGISTRATION_SUCCESSFULLY_COMPLETED = "Registration successfully completed";
    }

    public static class FilePath {
        public static final String UPLOAD_RELATIVE_PATH = "uploaded";

        public static String getAbsolutePath(ServletContext context) {
            return context.getRealPath(context.getContextPath()) + UPLOAD_RELATIVE_PATH;
        }
        public static final String FILES_REPOSITORY = "uploaded";
    }
}

