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
            } else if (ControllerConst.Actions.REMOVE.equals(action)) {
                page = ControllerConst.Controllers.MANAGE_TASK;
            } else if (ControllerConst.Actions.COMPLETE.equals(action)) {
                page = ControllerConst.Controllers.MANAGE_TASK;
            } else {
                page = ControllerConst.Controllers.TASKS;
            }
            jumpTo(page, req, resp);
        } else {
            jumpToError(ExceptionConstants.Messages.INVALID_ACTION, ControllerConst.Controllers.TASKS, req, resp);
        }
    }
}
