package by.gsu.epamlab.controller;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.dao.IUserDao;
import by.gsu.epamlab.exceptions.DataSourceException;
import by.gsu.epamlab.logic.UserDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        performLogic(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        performLogic(req, resp);
    }

    protected void performLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ControllerConst.PAGE_FIELD_ACTION);
        String login = req.getParameter(ControllerConst.PAGE_FIELD_LOGIN);
        String password = req.getParameter(ControllerConst.PAGE_FIELD_PASSWORD);
        String page;

        IUserDao userDao = UserDaoFactory.getUserDao(ControllerConst.USER_DAO_CLASS);

        if (ControllerConst.PAGE_FIELD_ACTION_LOGIN.equals(action)) {
            try {
                User user = userDao.get(login, password);
                req.setAttribute(ControllerConst.PAGE_FIELD_USERNAME, user.getLogin());
                page = ControllerConst.PAGE_MAIN;
            } catch (DataSourceException e) {
                req.setAttribute(ControllerConst.PAGE_FIELD_ERROR_MESSAGE, ControllerConst.ERROR_INVALID_LOGIN_OR_PASSWORD);
                page = ControllerConst.PAGE_ERROR;
            }


        } else if (ControllerConst.PAGE_FIELD_ACTION_REGISTER.equals(action)) {
            try {
                userDao.create(new User(login, password));
                req.setAttribute(ControllerConst.PAGE_FIELD_INFO_MESSAGE, ControllerConst.MESSAGE_REGISTRATION_SUCCESSFULLY_COMPLETED);
                req.setAttribute(ControllerConst.PAGE_FIELD_USERNAME, login);
                page = ControllerConst.PAGE_MAIN;
            } catch (DataSourceException e) {
                req.setAttribute(ControllerConst.PAGE_FIELD_ERROR_MESSAGE, ControllerConst.ERROR_REGISTRATION);
                page = ControllerConst.PAGE_ERROR;
            }
        } else {
            page = ControllerConst.PAGE_LOGIN;
        }

        getServletContext().getRequestDispatcher(page).forward(req, resp);
    }
}
