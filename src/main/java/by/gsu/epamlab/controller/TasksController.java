package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.ITaskDao;
import by.gsu.epamlab.model.exceptions.DataSourceException;
import by.gsu.epamlab.model.factories.AbstractDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TasksController extends AbstractController {

    @Override
    protected void performLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute(ControllerConst.Fields.USER);
        String taskTypeStr = req.getParameter(ControllerConst.Actions.ACTION);
        TaskTypesWrapper taskType;

        if (taskTypeStr == null) {
            taskType = TaskTypesWrapper.TODAY;
        } else {
            try {
                taskType = TaskTypesWrapper.valueOf(taskTypeStr);
            } catch (IllegalArgumentException e) {
                taskType = TaskTypesWrapper.TODAY;
            }
        }

        try {
            req.setAttribute(ControllerConst.Fields.WITH_DATE, taskType.isDateShow());
            req.setAttribute(ControllerConst.Fields.BUTTON_COMPLETE, taskType.isButtonComplete());
            req.setAttribute(ControllerConst.Fields.USERNAME, user.getName());

            ITaskDao taskDao = AbstractDaoFactory.getFactory(ControllerConst.FACTORY).getTaskDao();
            List<Task> list = taskDao.getAll(user.getId(), taskType.getType());

            req.setAttribute(ControllerConst.Fields.TASKS_LIST, list);
            // закомментировано для возможности отображения пустой таблицы
            // req.setAttribute("tasksIsEmpty", list.isEmpty());
            req.setAttribute(ControllerConst.Fields.TASK_TYPE, taskType.getValue());

            jumpTo(ControllerConst.Pages.TASKS, req, resp);
        } catch (DataSourceException e) {
            jumpToError(ControllerConst.Errors.TASKS_REQUEST_ERROR, ControllerConst.Controllers.TASKS, req, resp);
        }
    }
}
