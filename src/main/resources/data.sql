insert into blog_user (login, password) values ('alexandre', '$2a$10$wrZ7StSLDNlmzlRGURYiK.WDkZhNNvq6Us5CNZctylJNfTt0ehZWm');
insert into blog_user (login, password) values ('junior', '$2a$10$1mwB/Ava5fvG9wl7ilHfpezj1z3oEekYM7fi.alwZt6f2U0XLRwVG');
insert into blog_user (login, password) values ('jose', '$2a$10$5bYlwsbHdDrqnjOkAmoyTu2SqvkRANHkKtLcxXDdVX3f2M3ZtOZ6S');

insert into profile (name) values ('admin');
insert into profile (name) values ('editor');

insert into blog_user_profiles (user_id, profiles_id) values (1, 1);
insert into blog_user_profiles (user_id, profiles_id) values (2, 2);
insert into blog_user_profiles (user_id, profiles_id) values (3, 2);

insert into post (content, title, url_image_featured, user_id, creation_date) values ('testando conteudo 1', 'Post 1', 'https://img.ibxk.com.br/2021/09/02/02081436750009.jpg?w=1120&h=420&mode=crop&scale=both', 1, '2019-05-07 23:00:00');
insert into post (content, title, url_image_featured, user_id, creation_date) values ('testando conteudo 2', 'Post 2', 'https://img.ibxk.com.br/2021/09/01/01170726337410.jpg?w=1120&h=420&mode=crop&scale=both', 3, '2021-02-02 08:00:00');

insert into gallery (title, url_image_featured, user_id, creation_date) values ('Gallery 1', 'https://img.ibxk.com.br/2021/09/01/01231105367023.jpg?w=1120&h=420&mode=crop&scale=both', 1, '2019-05-05 20:00:00');
insert into gallery (title, url_image_featured, user_id, creation_date) values ('Gallery 2', 'https://img.ibxk.com.br/2021/09/01/01231845680025.jpg?w=1120&h=420&mode=crop&scale=both', 3, '2019-05-05 21:00:00');

insert into comment (content, post_id, user_id, creation_date) values ('comment testing', 1, 3, '2019-05-05 20:00:00');

insert into image (url, gallery_id, post_id) values ('https://img.ibxk.com.br/2021/09/01/01154937447338.jpg?w=1120&h=420&mode=crop&scale=both', 1, 1);
insert into image (url, gallery_id, post_id) values ('https://img.ibxk.com.br/2021/09/01/01183811521450.jpg?w=1120&h=420&mode=crop&scale=both', 1, 2);
insert into image (url, gallery_id, post_id) values ('https://img.ibxk.com.br/2021/09/01/01163605828372.jpg?w=1120&h=420&mode=crop&scale=both', 2, 2);
insert into image (url, gallery_id, post_id) values ('https://img.ibxk.com.br/2021/09/01/01164648563396.jpg?w=1120&h=420&mode=crop&scale=both', 2, 2);


