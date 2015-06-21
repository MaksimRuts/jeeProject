package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.ITaskDao;
import by.gsu.epamlab.model.factories.AbstractDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;

public class TasksController extends AbstractController {

    @Override
    protected void performLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute(ControllerConst.Fields.USER);
        String taskTypeStr = req.getParameter(ControllerConst.Actions.ACTION);
        TaskTypes taskType;

        try {
            taskType = TaskTypes.valueOf(taskTypeStr);
        } catch (IllegalArgumentException e) {
            taskType = TaskTypes.TODAY;
        } catch (NullPointerException e) {
            taskType = TaskTypes.TODAY;
        }

        switch (taskType) {
            case TODAY:
            case TOMORROW:
                req.setAttribute(ControllerConst.Fields.WITH_DATE, false);
                req.setAttribute(ControllerConst.Fields.BUTTON_FIX, true);
                break;
            case SOMEDAY:
                req.setAttribute(ControllerConst.Fields.WITH_DATE, true);
                req.setAttribute(ControllerConst.Fields.BUTTON_FIX, true);
                break;
            case FIXED:
            case RECYCLE_BIN:
                req.setAttribute(ControllerConst.Fields.WITH_DATE, true);
                req.setAttribute(ControllerConst.Fields.BUTTON_FIX, false);
                break;
        }

        req.setAttribute(ControllerConst.Fields.USERNAME, user.getLogin());

        ITaskDao taskDao = AbstractDaoFactory.getFactory(ControllerConst.FACTORY).getTaskDao();

        List<Task> list = taskDao.getAll(user.getId());
        ListIterator<Task> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (!tasksFilter(listIterator.next(), taskType)) {
                listIterator.remove();
            }
        }
        req.setAttribute("tasksList", list);
        // закомментировано для возможности отображения пустой таблицы
        // req.setAttribute("tasksIsEmpty", list.isEmpty());
        req.setAttribute(ControllerConst.Fields.TASK_TYPE, taskType.getValue());

        jumpTo(ControllerConst.Pages.TASKS, req, resp);
    }


    private boolean tasksFilter(Task tasks, TaskTypes term) {
        int nextDay = 1;
        switch (term) {
            case TODAY:
                return Date.valueOf(LocalDate.now()).equals(tasks.getDateEnding());
            case TOMORROW:
                return Date.valueOf(LocalDate.now().plusDays(nextDay)).equals(tasks.getDateEnding());
            case SOMEDAY:
                return tasks.getDateEnding().compareTo(Date.valueOf(LocalDate.now().plusDays(nextDay))) > 0;
            case FIXED:
                return tasks.isCompleted();
            case RECYCLE_BIN:
                return tasks.isDeleted();
            default:
                return false;
        }
    }
}
