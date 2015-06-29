package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exceptions.ValidationException;
import by.gsu.epamlab.model.factories.AbstractDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddTaskController extends AbstractController {

    @Override
    protected void performLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ControllerConst.Actions.ACTION);

        if (action != null) {

            String name = req.getParameter(ControllerConst.Fields.TASK_NAME);
            String date = req.getParameter(ControllerConst.Fields.TASK_DATE);
            String description = req.getParameter(ControllerConst.Fields.TASK_DESCRIPTION);

            try {
                validateField(name);
                validateField(date);
                validateField(description);

                User user = (User) req.getSession().getAttribute(ControllerConst.Fields.USER);
                Task task = new Task();
                task.setName(name);
                task.setDescription(description);
                task.setDateEnding(date);
                task.setUserId(user.getId());
                AbstractDaoFactory.getFactory(ControllerConst.FACTORY)
                        .getTaskDao()
                        .create(task);
                redirectTo(ControllerConst.Controllers.TASKS, req, resp);
            } catch (ValidationException e) {
                jumpToError(ControllerConst.Errors.EMPTY_FIELDS, ControllerConst.Pages.ADD_TASK, req, resp);
            }
        } else {
            jumpTo(ControllerConst.Pages.ADD_TASK, req, resp);
        }
    }
}
