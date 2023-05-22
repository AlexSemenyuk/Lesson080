package org.itstep.data;

import java.time.LocalDateTime;
import java.util.Objects;

public class Post {
    private int id;
    private String title;
    private LocalDateTime published;
    private User user;
    private String imagePath;
    private String content;
    private Draft draft;

        public Post(int id, String title, LocalDateTime published, User user, String imagePath, String content, Draft draft) {
        this.id = id;
        this.title = title;
        this.published = published;
        this.user = user;
        this.imagePath = imagePath;
        this.content = content;
        this.draft = draft;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPublished() {
        return published;
    }

    public void setPublished(LocalDateTime published) {
        this.published = published;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Draft getDraft() {
        return draft;
    }

    public void setDraft(Draft draft) {
        this.draft = draft;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", published=" + published +
                ", user=" + user +
                ", imagePath='" + imagePath + '\'' +
                ", content='" + content + '\'' +
                ", draft=" + draft +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && Objects.equals(title, post.title) && Objects.equals(published, post.published) && Objects.equals(user, post.user) && Objects.equals(imagePath, post.imagePath) && Objects.equals(content, post.content) && draft == post.draft;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, published, user, imagePath, content, draft);
    }
}
