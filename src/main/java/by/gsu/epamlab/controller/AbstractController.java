package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.ITaskDao;
import by.gsu.epamlab.model.exceptions.ValidationException;
import by.gsu.epamlab.model.factories.AbstractDaoFactory;
import by.gsu.epamlab.requestparser.FileManagement;
import by.gsu.epamlab.requestparser.RequestBody;
import by.gsu.epamlab.requestparser.RequestParser;
import by.gsu.epamlab.requestparser.UploadedFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class AbstractController extends HttpServlet {
    protected boolean isMultipart = false;
    private RequestBody request;
    private HttpServletRequest req;

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
        return;
    }

    protected void jumpToError(String message,
                               HttpServletRequest req,
                               HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute(ControllerConst.Fields.ERROR_MESSAGE, message);
        jumpTo(ControllerConst.Pages.ERROR, req, resp);
    }

    protected void jumpToError(String message, String backPage,
                               HttpServletRequest req,
                               HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute(ControllerConst.Fields.BACK_PAGE, backPage);
        jumpToError(message, req, resp);
    }

    protected void redirectTo(String page, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(page);
        return;
    }

    protected void validateField(String field) {
        if (field == null || "".equals(field)) {
            throw new ValidationException(field);
        }
    }

    protected void checkRequestType(HttpServletRequest req) throws IOException {
        this.req = req;
        String contentType = req.getContentType();
        if (contentType.startsWith(ControllerConst.MULTIPART_FORM_DATA)) {
            isMultipart = true;
            request = RequestParser.parse(req);
        } else {
            isMultipart = false;
        }
    }

    protected String getParameter(String name) {
        if (isMultipart) {
            return request.getParameter(name);
        } else {
            return this.req.getParameter(name);
        }
    }

    protected UploadedFile getFile(String name) {
        if (isMultipart) {
            return request.getFile(name);
        } else {
            return null;
        }
    }

    protected void downloadFile(User user, Task task, HttpServletResponse resp) throws ServletException, IOException {
        ITaskDao taskDao = AbstractDaoFactory.getFactory(ControllerConst.FACTORY)
                .getTaskDao();

        String filename = task.getFilename();
        String filepath = getServletContext().getInitParameter(ControllerConst.File.FILEPATH);
        resp.setContentType(ControllerConst.File.APPLICATION_OCTET_STREAM);
        resp.setHeader(ControllerConst.File.CONTENT_DISPOSITION, ControllerConst.File.ATTACHMENT_FILENAME + filename + "\"");

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
