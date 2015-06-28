package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.IUserDao;
import by.gsu.epamlab.model.exceptions.DataSourceException;
import by.gsu.epamlab.model.factories.AbstractDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationController extends AbstractController {
    @Override
    protected void performLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ControllerConst.Actions.ACTION);
        String login = req.getParameter(ControllerConst.Fields.LOGIN);
        String password = req.getParameter(ControllerConst.Fields.PASSWORD);
        String name = req.getParameter(ControllerConst.Fields.NAME);

        IUserDao userDao = AbstractDaoFactory.getFactory(ControllerConst.FACTORY).getUserDao();


        if (ControllerConst.Actions.REGISTER.equals(action)) {
            try {
                validateField(login);
                validateField(password);
                validateField(name);

                User user = userDao.create(new User(login, name), password);
                req.getSession(true).setAttribute(ControllerConst.Fields.USER, user);
                redirectTo(ControllerConst.Controllers.TASKS, req, resp);
            } catch (DataSourceException e) {
                req.setAttribute(ControllerConst.Fields.ERROR_MESSAGE, ControllerConst.Errors.REGISTRATION);
                jumpTo(ControllerConst.Pages.ERROR, req, resp);
            } catch (IllegalArgumentException e) {
                req.setAttribute(ControllerConst.Fields.ERROR_MESSAGE, ControllerConst.Errors.EMPTY_FIELDS);
                jumpTo(ControllerConst.Pages.ERROR, req, resp);
            }
        } else {
            jumpTo(ControllerConst.Pages.LOGIN, req, resp);
        }
    }
}
