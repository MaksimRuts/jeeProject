package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.beans.Note;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.INoteDao;
import by.gsu.epamlab.model.daoimpl.NoteDaoMemory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TasksController extends AbstractController {
    @Override
    protected void performLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        String taskTypeStr = req.getParameter(ControllerConst.Actions.ACTION);
        TaskTypes taskType;

        try {
            taskType = TaskTypes.valueOf(taskTypeStr);
        } catch (IllegalArgumentException e) {
            taskType = TaskTypes.TODAY;
        } catch (NullPointerException e) {
            taskType = TaskTypes.TODAY;
        }


//        if (taskTypeStr != null) {
//            taskType = TaskTypes.valueOf(taskTypeStr);
//        } else {
//            taskType = TaskTypes.TODAY;
//        }

        req.setAttribute("username", user.getLogin());
        // TODO
        INoteDao noteDao = new NoteDaoMemory();

        List<Note> list = noteDao.getAll(user.getId());
        req.setAttribute("notesList", list);
        req.setAttribute("notesIsEmpty", list.isEmpty());
        req.setAttribute("withDate", false);
        req.setAttribute("taskType", taskType.getValue());


        jumpTo(ControllerConst.Pages.TASKS, req, resp);
    }
}
