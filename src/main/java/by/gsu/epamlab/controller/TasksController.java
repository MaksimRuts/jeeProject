package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.ITaskDao;
import by.gsu.epamlab.model.exceptions.DataSourceException;
import by.gsu.epamlab.model.factories.AbstractDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TasksController extends AbstractController {

    @Override
    protected void performLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ControllerConst.Actions.ACTION);
        HttpSession session = req.getSession();
        TaskTypesWrapper taskType = (TaskTypesWrapper) session.getAttribute(ControllerConst.Fields.TASK_TYPE);

        try {
//            if (taskType == null) {
//                taskType = TaskTypesWrapper.TODAY;
//            }

            if (action != null) {
                try {
                    taskType = TaskTypesWrapper.valueOf(action);
                } catch (IllegalArgumentException e) {
                    // do nothing
                }
            }

            User user = (User)session.getAttribute(ControllerConst.Fields.USER);
            session.setAttribute(ControllerConst.Fields.TASK_TYPE, taskType);

            ITaskDao taskDao = AbstractDaoFactory.getFactory(ControllerConst.FACTORY).getTaskDao();
            List<Task> list = taskDao.getAll(user.getId(), taskType.getType());
            req.setAttribute(ControllerConst.Fields.TASKS_LIST, list);

        } catch (DataSourceException e) {
            jumpToError(ControllerConst.Errors.TASKS_REQUEST_ERROR, ControllerConst.Controllers.TASKS, req, resp);
        }
        jumpTo(ControllerConst.Pages.TASKS, req, resp);
    }
}
