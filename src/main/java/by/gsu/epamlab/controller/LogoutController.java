package by.gsu.epamlab.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutController extends AbstractController {
    @Override
    protected void performLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        redirectTo(ControllerConst.Controllers.LOGIN, req, resp);
    }
}