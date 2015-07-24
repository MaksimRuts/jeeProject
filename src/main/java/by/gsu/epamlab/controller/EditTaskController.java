package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.ITaskDao;
import by.gsu.epamlab.model.factories.AbstractDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditTaskController extends AbstractController {
    @Override
    protected void performLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ControllerConst.Actions.ACTION);
        if (ControllerConst.Actions.REMOVE.equals(action)) {
            jumpTo(ControllerConst.Controllers.MANAGE_TASK, req, resp);
        } else if (ControllerConst.Actions.CONFIRM.equals(action)) {
            String name = req.getParameter(ControllerConst.Fields.TASK_NAME);
            String date = req.getParameter(ControllerConst.Fields.TASK_DATE);
            String description = req.getParameter(ControllerConst.Fields.TASK_DESCRIPTION);
            String tasksId = req.getParameter(ControllerConst.Fields.SELECT);

            validateField(name);
            validateField(date);
            validateField(description);
            validateField(tasksId);

            ITaskDao taskDao = AbstractDaoFactory.getFactory(ControllerConst.FACTORY)
                    .getTaskDao();

            User user = (User) req.getSession().getAttribute(ControllerConst.Fields.USER);
            Task task = taskDao.read(user.getId(), Integer.parseInt(tasksId));

            task.setName(name);
            task.setDescription(description);
            task.setUserId(user.getId());
            task.setDateEnding(date);

            taskDao.update(task);
            redirectTo(ControllerConst.Controllers.TASKS, req, resp);
        } else {
            // get first selected task
            String[] tasksId = req.getParameterValues(ControllerConst.Fields.SELECT);
            if (tasksId != null && tasksId.length > 0) {
                User user = (User) req.getSession().getAttribute(ControllerConst.Fields.USER);
                ITaskDao taskDao = AbstractDaoFactory.getFactory(ControllerConst.FACTORY)
                        .getTaskDao();
                Task task = taskDao.read(user.getId(), Integer.parseInt(tasksId[0]));
                req.setAttribute(ControllerConst.Fields.TASK, task);
                jumpTo(ControllerConst.Pages.EDIT_TASK, req, resp);
            } else {
                jumpTo(ControllerConst.Controllers.TASKS, req, resp);
            }
        }
    }
}
