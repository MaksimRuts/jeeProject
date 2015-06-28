package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.ITaskDao;
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
        TaskTypes taskType;

        if (taskTypeStr == null) {
            taskType = TaskTypes.TODAY;
        } else {
            try {
                taskType = TaskTypes.valueOf(taskTypeStr);
            } catch (IllegalArgumentException e) {
                taskType = TaskTypes.TODAY;
            }
        }


        req.setAttribute(ControllerConst.Fields.WITH_DATE, taskType.getDateViewParam());
        req.setAttribute(ControllerConst.Fields.BUTTON_FIX, taskType.getFixedViewParam());
        req.setAttribute(ControllerConst.Fields.USERNAME, user.getName());

        ITaskDao taskDao = AbstractDaoFactory.getFactory(ControllerConst.FACTORY).getTaskDao();
        List<Task> list = taskDao.getAll(user.getId(), taskType);

        req.setAttribute(ControllerConst.Fields.TASKS_LIST, list);
        // закомментировано для возможности отображения пустой таблицы
        // req.setAttribute("tasksIsEmpty", list.isEmpty());
        req.setAttribute(ControllerConst.Fields.TASK_TYPE, taskType.getValue());

        jumpTo(ControllerConst.Pages.TASKS, req, resp);
    }
}
