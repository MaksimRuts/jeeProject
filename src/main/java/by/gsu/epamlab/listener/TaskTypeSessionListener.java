package by.gsu.epamlab.listener;

import by.gsu.epamlab.controller.ControllerConst;
import by.gsu.epamlab.controller.TaskTypesWrapper;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class TaskTypeSessionListener implements HttpSessionListener{
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute(ControllerConst.Fields.TASK_TYPE, TaskTypesWrapper.TODAY);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
