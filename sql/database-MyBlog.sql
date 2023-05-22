DROP DATABASE myBlog;
create database myBlog default char set utf8;
use myBlog;
DROP TABLE `users`;
CREATE TABLE `users`
(
    `id`           INT PRIMARY KEY NOT NULL    AUTO_INCREMENT,
    `first_name`   VARCHAR(255)    NOT NULL,
    `last_name`    VARCHAR(255)    NOT NULL,
    `avatar`       VARCHAR(255)    NOT NULL,
    `login`        VARCHAR(255)    NOT NULL    unique,
    `password`     VARCHAR(255)    NOT NULL    unique,
    `role_id`      INT             NOT NULL
);

CREATE TABLE `roles`
(
    `id`           INT PRIMARY KEY NOT NULL    AUTO_INCREMENT,
    `mean`         VARCHAR(255)    NOT NULL    unique
);

alter table users
    add constraint users_roles_fk
    foreign key (role_id) references roles (id);

CREATE TABLE `posts`
(
    `id`           INT PRIMARY KEY NOT NULL    AUTO_INCREMENT,
    `title`        VARCHAR(255)    NOT NULL,
    `published`    VARCHAR(255)    NOT NULL,
    `author_id`    INT             NOT NULL,
    `image_path`   VARCHAR(255)    NOT NULL,
    `content`      TEXT            NOT NULL,
    `draft_id`     INT             NOT NULL
);

CREATE TABLE `drafts`
(
    `id`           INT PRIMARY KEY NOT NULL    AUTO_INCREMENT,
    `mean`         VARCHAR(255)    NOT NULL    unique
);

alter table posts
    add constraint posts_drafts_fk
        foreign key (draft_id) references drafts (id);

alter table posts
    add constraint posts_users_fk
        foreign key (author_id) references users (id);