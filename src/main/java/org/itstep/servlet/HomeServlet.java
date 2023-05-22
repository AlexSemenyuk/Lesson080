package org.itstep.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.itstep.data.Post;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> posts = blogDao.findAllPosts();
        if (posts != null) {
            posts = posts.stream().filter(post -> {
                if (post.getDraft().num() != 1){
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
            posts.stream().forEach(p -> System.out.println(p.toString()));
            HttpSession session = req.getSession();
            session.setAttribute("posts", posts);
        }

        req.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.sendRedirect(req.getServletContext().getContextPath() + "/admin");
    }

}
