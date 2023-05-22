package org.itstep.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import org.itstep.dao.BlogDao;
import org.itstep.dao.impl.BlogDaoImpl;

public abstract class BaseServlet extends HttpServlet{
    protected BlogDao blogDao;
    @Override
    public void init(ServletConfig config) throws ServletException {
        String url = config.getServletContext().getInitParameter("url");
        String username = config.getServletContext().getInitParameter("username");
        String password = config.getServletContext().getInitParameter("password");
//        taskDao = new TaskDaoImpl(url, username, password);
        blogDao = new BlogDaoImpl("jdbc:mariadb://localhost/myblog", "root", "");
    }
}




