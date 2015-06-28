package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.ITaskDao;
import by.gsu.epamlab.model.factories.AbstractDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManageTaskController extends AbstractController {

    @Override
    protected void performLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ControllerConst.Actions.ACTION);
        User user = (User) req.getSession().getAttribute(ControllerConst.Fields.USER);
        String taskTypeStr = req.getParameter(ControllerConst.Fields.TASK_TYPE);

        String[] tasksId = req.getParameterValues(ControllerConst.Fields.SELECT);
        ITaskDao taskDao = AbstractDaoFactory.getFactory(ControllerConst.FACTORY).getTaskDao();
        for (String id : tasksId) {
            if (ControllerConst.Actions.REMOVE.equals(action)) {
                Task task = taskDao.read(user.getId(), Integer.parseInt(id));

//                if (taskTypeStr != null && TaskTypes.RECYCLE_BIN.equals(TaskTypes.valueOf(taskTypeStr))) {
//                    taskDao.delete(task);
//                } else {
                    task.setDeleted(true);
                    taskDao.update(task);
//                }

            } else if (ControllerConst.Actions.COMPLETE.equals(action)) {
                Task task = taskDao.read(user.getId(), Integer.parseInt(id));
                task.setCompleted(true);
                taskDao.update(task);
            }
        }
        jumpTo(ControllerConst.Pages.TASKS, req, resp);

    }
}
