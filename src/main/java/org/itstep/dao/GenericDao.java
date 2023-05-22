package org.itstep.dao;

import org.itstep.data.Post;
import org.itstep.data.User;

import java.time.LocalDateTime;
import java.util.List;

// Data Access Object
public interface GenericDao<S, U, I, P> {

//    void addUser (S firstName, S lastName, S login, S password);

    U findUserByLoginAndPassword (S login, S password);
    U findUserById (I id);
    List<U> findAllUsers();
    Post findPostById (I id);
    List<P> findAllPosts();
    void addPostToDatabase (S title, S published, I authorId, S imagePath, S content, I draftId);
}


