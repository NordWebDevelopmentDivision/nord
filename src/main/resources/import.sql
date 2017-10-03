-- Insert role
insert into role (name) values ('ROLE_USER');
insert into role (name) values ('ROLE_ADMIN');

-- Insert two users (passwords are both 'password')
insert into nord_user (username,enabled,password,role_id) values ('user1',true,'$2a$06$XXGRAjNiKfgXsUhDwghRIe0QstIDnXoFw6JvnpM/pTRFKQsUmrUcW',1);
insert into nord_user (username,enabled,password,role_id) values ('user2',true,'$2a$06$XXGRAjNiKfgXsUhDwghRIe0QstIDnXoFw6JvnpM/pTRFKQsUmrUcW',1);
insert into nord_user (username,enabled,password,role_id) values ('admin1',true,'$2a$06$XXGRAjNiKfgXsUhDwghRIe0QstIDnXoFw6JvnpM/pTRFKQsUmrUcW',2);
insert into nord_user (username,enabled,password,role_id) values ('admin2',true,'$2a$06$XXGRAjNiKfgXsUhDwghRIe0QstIDnXoFw6JvnpM/pTRFKQsUmrUcW',2);

