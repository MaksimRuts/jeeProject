package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.IUserDao;
import by.gsu.epamlab.model.daoimpl.UserDaoFactory;
import by.gsu.epamlab.model.exceptions.DataSourceException;

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

        IUserDao userDao = UserDaoFactory.getUserDao(ControllerConst.Factories.USER_DAO_CLASS);

        if (ControllerConst.Actions.LOGIN.equals(action)) {
            try {
                User user = userDao.get(login, password);
                req.getSession(true).setAttribute("user", user);
                jumpTo(ControllerConst.Controllers.TASKS, req, resp);
            } catch (DataSourceException e) {
                req.setAttribute(ControllerConst.Fields.ERROR_MESSAGE, ControllerConst.Errors.INVALID_LOGIN_OR_PASSWORD);
                jumpTo(ControllerConst.Pages.ERROR, req, resp);
            }
        } else if (ControllerConst.Actions.REGISTER.equals(action)) {
            try {
                userDao.create(new User(login, password));
                req.setAttribute(ControllerConst.Fields.INFO_MESSAGE, ControllerConst.Messages.REGISTRATION_SUCCESSFULLY_COMPLETED);
                req.setAttribute(ControllerConst.Fields.USERNAME, login);

                User user = userDao.get(login, password);
                req.getSession(true).setAttribute("user", user);
                jumpTo(ControllerConst.Pages.TASKS, req, resp);
            } catch (DataSourceException e) {
                req.setAttribute(ControllerConst.Fields.ERROR_MESSAGE, ControllerConst.Errors.REGISTRATION);
                jumpTo(ControllerConst.Pages.ERROR, req, resp);
            }
        } else {
            jumpTo(ControllerConst.Pages.LOGIN, req, resp);
        }
    }

//    @Override
//    public void destroy() {
//        super.destroy();
//        // TODO подумать где лучше закрыть соединение, либо создать пулл подключений
//        ConnectionManager.close();
//    }
}
