package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.TaskTypes;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.ITaskDao;
import by.gsu.epamlab.model.factories.AbstractDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ManageTaskController extends AbstractController {

    @Override
    protected void performLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ControllerConst.Actions.ACTION);
        User user = (User) req.getSession().getAttribute(ControllerConst.Fields.USER);
        ITaskDao taskDao = AbstractDaoFactory.getFactory(ControllerConst.FACTORY).getTaskDao();

        if (ControllerConst.Actions.REMOVE_ALL.equals(action)) {
            List<Task> tasks = taskDao.getAll(user.getId(), TaskTypes.RECYCLE_BIN);
            for (Task task : tasks) {
                taskDao.delete(task);
            }
        } else {
            TaskTypesWrapper taskType = (TaskTypesWrapper) req.getSession().getAttribute(ControllerConst.Fields.TASK_TYPE);
            String[] tasksId = req.getParameterValues(ControllerConst.Fields.SELECT);
            if (tasksId != null) {
                for (String id : tasksId) {
                    if (ControllerConst.Actions.REMOVE.equals(action)) {
                        Task task = taskDao.read(user.getId(), Integer.parseInt(id));
                        if (taskType != null && TaskTypesWrapper.RECYCLE_BIN.equals(taskType)) {
                            taskDao.delete(task);
                        } else {
                            task.setDeleted(true);
                            taskDao.update(task);
                        }
                    } else if (ControllerConst.Actions.COMPLETE.equals(action)) {
                        Task task = taskDao.read(user.getId(), Integer.parseInt(id));
                        task.setCompleted(true);
                        taskDao.update(task);
                    } else if (ControllerConst.Actions.RESTORE.equals(action)) {
                        Task task = taskDao.read(user.getId(), Integer.parseInt(id));
                        task.setDeleted(false);
                        taskDao.update(task);
                    }
                }
            }
        }
        jumpTo(ControllerConst.Controllers.TASKS, req, resp);
    }
}
