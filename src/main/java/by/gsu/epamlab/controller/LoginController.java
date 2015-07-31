package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.IUserDao;
import by.gsu.epamlab.model.exceptions.DataSourceException;
import by.gsu.epamlab.model.exceptions.ValidationException;
import by.gsu.epamlab.model.factories.AbstractDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends AbstractController {

    @Override
    protected void performLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ControllerConst.Actions.ACTION);
        String login = req.getParameter(ControllerConst.Fields.LOGIN);
        String password = req.getParameter(ControllerConst.Fields.PASSWORD);

        IUserDao userDao = AbstractDaoFactory.getFactory(ControllerConst.FACTORY).getUserDao();

        if (ControllerConst.Actions.LOGIN.equals(action)) {
            try {
                validateField(login);
                validateField(password);

                User user = userDao.read(login, password);
                req.getSession(true).setAttribute(ControllerConst.Fields.USER, user);
                jumpTo(ControllerConst.Controllers.ACTION, req, resp);
            } catch (DataSourceException e) {
                jumpToError(ControllerConst.Errors.INVALID_LOGIN_OR_PASSWORD, req, resp);
            } catch (ValidationException e) {
                jumpToError(ControllerConst.Errors.EMPTY_FIELDS, req, resp);
            }
        } else {
            jumpTo(ControllerConst.Pages.LOGIN, req, resp);
        }
    }
}
