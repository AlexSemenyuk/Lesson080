use myBlog;
insert into roles (mean)
VALUES ('User'), ('Admin');

insert into users (first_name, last_name, avatar, login, password, role_id)
VALUES ('Oleg', 'Luchenko', 'none', 'admin', 'admin', 1),
       ('Vitek', 'Verepianko', 'none', 'vitek', '123', 2),
       ('Anna', 'Yaschenko', 'none', 'anna', '321', 2),
	   ('Alex', 'Semenyuk', 'https://www.vhv.rs/dpng/d/276-2761771_transparent-avatar-png-vector-avatar-icon-png-png.png', 'alex', '111', 2);
 

insert into drafts (mean)
VALUES ('Yes'), ('No');

insert into posts (title, published, author_id, image_path, content, draft_id)
VALUES ('Garden', '16.05.2023 11:22:38', 1, 'resources/images/garden - 1.jpg', 'My Garden', 1),
       ('My walk', '16.05.2023 11:22:38', 1, 'resources/images/my walk - 1.jpg', '<p>What do I do during my walk?</p>', 2),
       ('My hobby', '17.05.2023 11:51:51', 1, 'resources/images/hobby - 1.jpg', '<p style="text-align: left;">I play in <strong>chess</strong> when I have a <strong><em>lot of free time</em></strong></p>', 2),
	   ('My future profession', '17.05.2023 13:26:02', 1, 'resources/images/future profession - 1.jpg', '<p>What <strong>will I do</strong> then <strong><em>I became programist</em></strong>?</p>', 2),
	   ('My family', '17.05.2023 13:46:54', 1, 'resources/images/my family - 1.jpg', '<p>I spend free time with <strong>my family</strong></p>', 2),
       ('This is my family', '17.05.2023 13:53:39', 4, 'resources/images/my family - 2.jpg', '<p>Only photo</p>', 2),
       ('Spring', '22.05.2023 17:29:12', 4, 'resources/images/Spring - 1.jpg', '<p>Spring is the <strong>most beautiful</strong> season</p>', 2),
       ('Autumn', '22.05.2023 17:33:45', 4, 'resources/images/Autumn - 1.jpg', '<p>Autumn is <em><strong>the brightest</strong></em> season</p>', 2);

select * from users where login='admin' and password='admin';
SELECT * FROM users WHERE login = 'admin' and password = 'admin';
SELECT * FROM posts order by id desc;




