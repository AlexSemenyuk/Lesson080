package org.itstep.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.itstep.data.User;
import org.itstep.folderForImage.PathOnProject;
import org.itstep.folderForImage.PathOnTomcat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;
@MultipartConfig
@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends BaseServlet {
    private final PathOnProject pathOnProject = new PathOnProject();
    private final PathOnTomcat pathOnTomcat = new PathOnTomcat();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = blogDao.findAllUsers();
        if (users != null) {
            System.out.println("Admin GET users");
            users.stream().forEach(user -> System.out.println(user.toString()));
        }
        HttpSession session = req.getSession();
        session.setAttribute("users", users);
        System.out.println();
        req.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("title");
        LocalDateTime time = LocalDateTime.now();
        String published = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss").format((TemporalAccessor) time);
//        String author = req.getParameter("author");
        String content = req.getParameter("content");
        String draft = req.getParameter("draft");
//        String imagePath = req.getParameter("imagepath");
        Part file = req.getPart("file");
        Path uploadsDirOnProject = null;
        Path filePathOnTomcat = null;
        try {
            // 1. Write on folder on Project
            uploadsDirOnProject = pathOnProject.getUploadsDir(req.getServletContext());
            if (!Files.exists(uploadsDirOnProject)) {
                Files.createDirectories(uploadsDirOnProject);
            }
            Path filePathOnProject = Path.of(uploadsDirOnProject.toString(), file.getSubmittedFileName());
            Files.copy(file.getInputStream(), filePathOnProject, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Saved successfully to " + filePathOnProject);

            // 2. Write on folder on Tomcat
            Path uploadsDirOnTomcat = pathOnTomcat.getUploadsDir(req.getServletContext());
            if (!Files.exists(uploadsDirOnTomcat)) {
                Files.createDirectories(uploadsDirOnTomcat);
            }
            filePathOnTomcat = Path.of(uploadsDirOnTomcat.toString(), file.getSubmittedFileName());
            Files.copy(file.getInputStream(), filePathOnTomcat, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Saved successfully to " + filePathOnTomcat);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String imagePath = "resources/images/" + file.getSubmittedFileName();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Integer draftId = 0;
        if (title != null && !title.isBlank() &&
//                imagePath != null && !imagePath.isBlank() &&
                content != null && !content.isBlank() &&
                user != null) {

            int authorId = user.getId();
            System.out.println("authorId = " + authorId);
            if (draft == null) {
                draft = "off";
            }
            switch (draft) {
                case "on" -> draftId = Integer.valueOf(1);
                case "off" -> draftId = Integer.valueOf(2);
            }
            if (Integer.valueOf(authorId) instanceof Integer && draftId instanceof Integer) {
                blogDao.addPostToDatabase(title, published, authorId, imagePath, content, draftId);
            }
        }
//        System.out.println("Post:" + req.getServletContext().getContextPath() + "/admin");
        resp.sendRedirect(req.getServletContext().getContextPath() + "/admin");
    }
}

