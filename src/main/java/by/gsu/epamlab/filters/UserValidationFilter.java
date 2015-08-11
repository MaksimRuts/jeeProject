package by.gsu.epamlab.filters;

import by.gsu.epamlab.controller.ControllerConst;
import by.gsu.epamlab.controller.TaskTypesWrapper;
import by.gsu.epamlab.model.beans.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserValidationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
        User user = (User) session.getAttribute(ControllerConst.Fields.USER);
        if (user == null) {
            servletRequest.getRequestDispatcher(ControllerConst.Controllers.LOGOUT).forward(servletRequest, servletResponse);
        } else {
//            TaskTypesWrapper taskType = (TaskTypesWrapper) session.getAttribute(ControllerConst.Fields.TASK_TYPE);
//            if (taskType == null) {
//                session.setAttribute(ControllerConst.Fields.TASK_TYPE, TaskTypesWrapper.TODAY);
//            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
