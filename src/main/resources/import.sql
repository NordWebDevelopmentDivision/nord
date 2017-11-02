-- Insert role
insert into role (name) values ('ROLE_USER');
insert into role (name) values ('ROLE_ADMIN');

-- Insert two users (passwords are both 'password')
insert into nord_user (username,enabled,password,role_id,senior,points) values ('user1',true,'$2a$06$XXGRAjNiKfgXsUhDwghRIe0QstIDnXoFw6JvnpM/pTRFKQsUmrUcW',1,false,0);
insert into nord_user (username,enabled,password,role_id,senior,points) values ('user2',true,'$2a$06$XXGRAjNiKfgXsUhDwghRIe0QstIDnXoFw6JvnpM/pTRFKQsUmrUcW',1,false,0);
insert into nord_user (username,enabled,password,role_id,senior,points) values ('admin1',true,'$2a$06$XXGRAjNiKfgXsUhDwghRIe0QstIDnXoFw6JvnpM/pTRFKQsUmrUcW',2,true,0);
insert into nord_user (username,enabled,password,role_id,senior,points) values ('admin2',true,'$2a$06$XXGRAjNiKfgXsUhDwghRIe0QstIDnXoFw6JvnpM/pTRFKQsUmrUcW',2,true,0);

