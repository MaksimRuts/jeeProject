package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.ITaskDao;
import by.gsu.epamlab.model.factories.AbstractDaoFactory;
import by.gsu.epamlab.requestparser.FileManagement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class FileDownloadController extends AbstractController {
    @Override
    protected void performLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ITaskDao taskDao = AbstractDaoFactory.getFactory(ControllerConst.FACTORY)
                .getTaskDao();
        String taskId = req.getParameter(ControllerConst.Fields.TASK_ID);
        User user = (User) req.getSession().getAttribute(ControllerConst.Fields.USER);
        Task task = taskDao.read(user.getId(), Integer.parseInt(taskId));

        String filename = task.getFilename();
        String filepath = getServletContext().getInitParameter(ControllerConst.FILEPATH);
        resp.setContentType("APPLICATION/OCTET-STREAM");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        String path = FileManagement.concatPath(filepath, user.getLogin(), filename);

        FileInputStream fileInputStream = new FileInputStream(path);
        int i;
        PrintWriter out = resp.getWriter();
        while ((i=fileInputStream.read()) != -1) {
            out.write(i);
        }
        fileInputStream.close();
        out.close();
    }
}
