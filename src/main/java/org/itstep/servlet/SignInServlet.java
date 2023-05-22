package org.itstep.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.itstep.data.User;

import java.io.IOException;
@WebServlet(urlPatterns = "/")
public class SignInServlet extends BaseServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SignIn-Get:");
        req.getRequestDispatcher("/WEB-INF/view/signin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        System.out.println("login = " + login);
        System.out.println("password = " + password);
        if (login != null && !login.isBlank() &&
                password != null && !password.isBlank() ) {
            user = blogDao.findUserByLoginAndPassword(login, password);

            if (user != null) {
                System.out.println("SignIn-Post: " + user.toString());
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
            }
        }

        System.out.println("Post:" + req.getServletContext().getContextPath() + "/");
        resp.sendRedirect(req.getServletContext().getContextPath() + "/");
    }

}


