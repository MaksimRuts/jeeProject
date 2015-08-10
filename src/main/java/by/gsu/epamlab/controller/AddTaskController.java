package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exceptions.ValidationException;
import by.gsu.epamlab.model.factories.AbstractDaoFactory;
import by.gsu.epamlab.requestparser.FileManagement;
import by.gsu.epamlab.requestparser.RequestBody;
import by.gsu.epamlab.requestparser.RequestParser;
import by.gsu.epamlab.requestparser.UploadedFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddTaskController extends AbstractController {

    @Override
    protected void performLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestBody request = RequestParser.parse(req);
        String action = request.getParameter(ControllerConst.Actions.ACTION);

        if (action != null) {
            HttpSession session = req.getSession();

            String name = request.getParameter(ControllerConst.Fields.TASK_NAME);
            String date = request.getParameter(ControllerConst.Fields.TASK_DATE);
            String description = request.getParameter(ControllerConst.Fields.TASK_DESCRIPTION);
            UploadedFile file = request.getFile(ControllerConst.Fields.FILE);
            TaskTypesWrapper taskType = (TaskTypesWrapper) session.getAttribute(ControllerConst.Fields.TASK_TYPE);

            try {
                validateField(name);

                User user = (User) session.getAttribute(ControllerConst.Fields.USER);
                Task task = new Task();

                if (taskType != null) {
                    if (!taskType.isDateShow()) {
                        task.setDateEnding(taskType.getDate());
                    } else {
                        validateField(date);
                        task.setDateEnding(date);
                    }
                }

                task.setName(name);
                task.setDescription(description);
                task.setUserId(user.getId());

                if (file != null) {
                    String filepath = getServletContext().getInitParameter(ControllerConst.FILEPATH);
                    filepath = FileManagement.concatPath(filepath, user.getLogin());
                    if (FileManagement.saveFile(file, filepath)) {
//                    if (FileManagement.saveFile(file, ControllerConst.FilePath.getAbsolutePath(getServletContext()))) {
                        task.setFilename(file.getFilename());
                    }
                }

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
