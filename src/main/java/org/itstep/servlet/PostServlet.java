package org.itstep.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.itstep.data.Post;
import org.itstep.data.User;

import java.io.IOException;

@WebServlet(urlPatterns = "/post")
public class PostServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/view/post.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post post = null;
        String postId = req.getParameter("id");

        if (postId != null && !postId.isBlank() ) {
            System.out.println("postId = " + postId);
            int id = Integer.parseInt(postId);
            post = blogDao.findPostById(id);
            if (post != null){
                System.out.println(post.toString());
                HttpSession session = req.getSession();
                session.setAttribute("post", post);
//                req.setAttribute("post", post);
            }
        }
        resp.sendRedirect(req.getServletContext().getContextPath() + "/post");
    }
}
