package org.itstep.dao.impl;


import org.itstep.DbUtils;
import org.itstep.dao.BlogDao;
import org.itstep.data.Draft;
import org.itstep.data.Post;
import org.itstep.data.Role;
import org.itstep.data.User;


import java.sql.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class BlogDaoImpl implements BlogDao {
    private final DbUtils dbUtils;
    private final static String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = '%s' and password = '%s';";
    private final static String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = %s;";
    private final static String SELECT_ALL = "SELECT * FROM %s;";
    private final static String SELECT_POST_BY_ID = "SELECT * FROM posts WHERE id = %s;";
    private final static String INSERT_POST = "INSERT INTO posts (title, published, author_id, image_path, content, draft_id)" +
            "VALUES (?, ?, ?, ?, ?, ?);";


//    private final static String INSERT =
//            "INSERT INTO users(first_name, last_name, login, password) VALUES (?, ?, ?, ?);";
//    private final static String Update = "UPDATE tasks SET condition_id = %s WHERE id = %s;";
//    private final static String Delete = "DELETE FROM tasks WHERE id = %s;";
//    private final static String SelectBySort = "SELECT * FROM tasks ORDER BY %s;";

    public BlogDaoImpl(String url, String username, String password) {
        dbUtils = DbUtils.getInstance();
        dbUtils.init(url, username, password);
    }

    @Override
    public User findUserByLoginAndPassword (String login, String password) {
        String selectTotal = SELECT_USER_BY_LOGIN_AND_PASSWORD.formatted(login, password);
        System.out.println("selectTotal = " + selectTotal);
        return findUserBySelect(selectTotal);
    }

    @Override
    public User findUserById (Integer id) {
        String selectTotal = SELECT_USER_BY_ID.formatted(id);
        System.out.println("selectTotal = " + selectTotal);
        return findUserBySelect(selectTotal);
    }

    private User findUserBySelect (String select){
        User [] rezultUser = new User[1];
        rezultUser[0] = null;
        try {
            Optional<Connection> optionalConnection = dbUtils.getConnection();
            optionalConnection.ifPresent(connection -> {
                try {
                    Statement stmt = connection.createStatement();
                    ResultSet resultSet = stmt.executeQuery(select);
                    while (resultSet.next()) {
                        int idDB = resultSet.getInt("id");
                        String firstNameDB = resultSet.getString("first_name");
                        String lastNameDB = resultSet.getString("last_name");
                        String avatarDB = resultSet.getString("avatar");
                        String loginDB = resultSet.getString("login");
                        String passwordDB = resultSet.getString("password");
                        int roleDB = resultSet.getInt("role_id");
                        Role role = null;
                        switch (roleDB){
                            case 1 -> role = Role.USER;
                            case 2 -> role = Role.ADMIN;
//                            default: role = Role.USER;
                        }
                        rezultUser[0] = new User(idDB, firstNameDB, lastNameDB, avatarDB, loginDB, passwordDB, role);
//                        System.out.println("BlogDaoImpl " + rezultUser[0].toString());
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rezultUser[0];
    }

    @Override
    public List<User> findAllUsers() {
        List <User> users = new CopyOnWriteArrayList<User>();
        try {
            Optional<Connection> optionalConnection = dbUtils.getConnection();
            optionalConnection.ifPresent(connection -> {
                try {
                    Statement stmt = connection.createStatement();
                    String totalSelect = SELECT_ALL.formatted("users");
                    ResultSet resultSet = stmt.executeQuery(totalSelect);
                    while (resultSet.next()) {
                        int idDB = resultSet.getInt("id");
                        String firstNameDB = resultSet.getString("first_name");
                        String lastNameDB = resultSet.getString("last_name");
                        String avatarDB = resultSet.getString("avatar");
                        String loginDB = resultSet.getString("login");
                        String passwordDB = resultSet.getString("password");
                        int roleDB = resultSet.getInt("role_id");
                        Role role = null;
                        switch (roleDB){
                            case 1 -> role = Role.USER;
                            case 2 -> role = Role.ADMIN;
//                            default: role = Role.USER;
                        }
                        users.add(new User(idDB, firstNameDB, lastNameDB, avatarDB, loginDB, passwordDB, role));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public Post findPostById (Integer id){
        String select = SELECT_POST_BY_ID.formatted(id);
        System.out.println("select = " + select);
        Post [] rezultPost = new Post[1];
        rezultPost[0] = null;
        try {
            Optional<Connection> optionalConnection = dbUtils.getConnection();
            optionalConnection.ifPresent(connection -> {
                try {
                    Statement stmt = connection.createStatement();
                    ResultSet resultSet = stmt.executeQuery(select);
                    while (resultSet.next()) {
                        int idDB = resultSet.getInt("id");
                        String titleDB = resultSet.getString("title");
                        String publishedDB = resultSet.getString("published");
                        int authorIdDB = resultSet.getInt("author_id");
                        String imagePathDB = resultSet.getString("image_path");
                        String contentDB = resultSet.getString("content");
                        int draftIdDB = resultSet.getInt("draft_id");

                        Draft draft = null;
                        switch (draftIdDB){
                            case 1 -> draft = Draft.YES;
                            case 2 -> draft = Draft.NO;
//                            default: role = Role.USER;
                        }
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
                        LocalDateTime published = LocalDateTime.parse(publishedDB, formatter);

                        User user = findUserById(authorIdDB);

                        rezultPost[0] = new Post (idDB, titleDB, published, user, imagePathDB, contentDB, draft);
                        System.out.println("BlogDaoImpl " + rezultPost[0].toString());
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rezultPost[0];
    }
    @Override
    public List<Post> findAllPosts (){
        List <Post> posts = new CopyOnWriteArrayList<Post>();
        try {
            Optional<Connection> optionalConnection = dbUtils.getConnection();
            optionalConnection.ifPresent(connection -> {
                try {
                    Statement stmt = connection.createStatement();
                    ResultSet resultSet = stmt.executeQuery(SELECT_ALL.formatted("posts ORDER BY id desc"));
                    while (resultSet.next()) {
                        int idDB = resultSet.getInt("id");
                        String titleDB = resultSet.getString("title");
                        String publishedDB = resultSet.getString("published");
                        int authorIdDB = resultSet.getInt("author_id");
                        String imagePathDB = resultSet.getString("image_path");
                        String contentDB = resultSet.getString("content");
                        int draftIdDB = resultSet.getInt("draft_id");

                        Draft draft = null;
                        switch (draftIdDB){
                            case 1 -> draft = Draft.YES;
                            case 2 -> draft = Draft.NO;
//                            default: role = Role.USER;
                        }
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
                        LocalDateTime published = LocalDateTime.parse(publishedDB, formatter);

                        User user = findUserById(authorIdDB);
                        posts.add(new Post(idDB, titleDB, published, user, imagePathDB, contentDB, draft));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }
    @Override
    public void addPostToDatabase (String title, String published, Integer authorId, String imagePath, String content, Integer draftId) {
        try {
            Optional<Connection> optionalConnection = dbUtils.getConnection();
            optionalConnection.ifPresent(connection -> {
                try {
                    var stmt = connection.prepareStatement(INSERT_POST);
                    stmt.setString(1, title);
                    stmt.setString(2, published);
                    stmt.setInt(3, authorId);
                    stmt.setString(4, imagePath);
                    stmt.setString(5, content);
                    stmt.setInt(6, draftId);
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public void addUser(String firstName, String lastName, String login, String password) {
//        try {
//            Optional<Connection> optionalConnection = dbUtils.getConnection();
//            optionalConnection.ifPresent(connection -> {
//                try {
//                    var stmt = connection.prepareStatement(INSERT);
//                    stmt.setString(1, firstName);
//                    stmt.setString(2, lastName);
//                    stmt.setString(3, login);
//                    stmt.setString(4, password);
//
////                    stmt.executeUpdate(INSERT);
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                } finally {
//                    try {
//                        connection.close();
//                    } catch (SQLException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            });
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


}
