package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.exceptions.ExceptionConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionTaskController extends AbstractController {

    @Override
    protected void performLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ControllerConst.Actions.ACTION);
        if (action != null) {
            String page;
            if (ControllerConst.Actions.ADD.equals(action)) {
                page = ControllerConst.Pages.ADD_TASK;
            } else if (ControllerConst.Actions.EDIT.equals(action)) {
                page = ControllerConst.Controllers.EDIT_TASK;
            } else if (ControllerConst.Actions.REMOVE.equals(action)) {
                page = ControllerConst.Controllers.MANAGE_TASK;
            } else if (ControllerConst.Actions.RESTORE.equals(action)) {
                page = ControllerConst.Controllers.MANAGE_TASK;
            } else if (ControllerConst.Actions.REMOVE_ALL.equals(action)) {
                page = ControllerConst.Controllers.MANAGE_TASK;
            } else if (ControllerConst.Actions.COMPLETE.equals(action)) {
                page = ControllerConst.Controllers.MANAGE_TASK;
            } else if (ControllerConst.Actions.REMOVE_FILE.equals(action)) {
                page = ControllerConst.Controllers.MANAGE_TASK;
            } else if (ControllerConst.Actions.DOWNLOAD_FILE.equals(action)) {
                page = ControllerConst.Controllers.DOWNLOAD_FILE;
            } else {
                page = ControllerConst.Controllers.TASKS;
            }
            jumpTo(page, req, resp);
        } else {
            jumpTo(ControllerConst.Controllers.TASKS, req, resp);
        }
    }
}
