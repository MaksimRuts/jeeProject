package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.exceptions.ValidationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        performLogic(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        performLogic(req, resp);
    }

    protected abstract void performLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    protected void jumpTo(String page, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(page).forward(req, resp);
    }

    protected void jumpToError(String message, String backPage,
                               HttpServletRequest req,
                               HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute(ControllerConst.Fields.ERROR_MESSAGE, message);
        req.setAttribute(ControllerConst.Fields.BACK_PAGE, backPage);
        jumpTo(ControllerConst.Pages.ERROR, req, resp);
    }

    protected void redirectTo(String page, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(page);
    }

    protected void validateField(String field) {
        if (field == null || "".equals(field)) {
            throw new ValidationException(field);
        }
    }
}
