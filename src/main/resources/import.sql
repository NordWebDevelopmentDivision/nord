-- Insert role
insert into role (name) values ('ROLE_USER');
insert into role (name) values ('ROLE_ADMIN');

-- Insert two users (passwords are both 'password')
insert into nord_user (username,enabled,password,role_id) values ('u',true,'$2a$10$jKsL6a/wqwzTYiZGUFecQOSIHpYwkpJm8n2VRgYNsQ/XD7GP1CLv2',1);
insert into nord_user (username,enabled,password,role_id) values ('u2',true,'$2a$10$jKsL6a/wqwzTYiZGUFecQOSIHpYwkpJm8n2VRgYNsQ/XD7GP1CLv2',1);
insert into nord_user (username,enabled,password,role_id) values ('a',true,'$2a$10$jKsL6a/wqwzTYiZGUFecQOSIHpYwkpJm8n2VRgYNsQ/XD7GP1CLv2',2);
insert into nord_user (username,enabled,password,role_id) values ('a2',true,'$2a$10$jKsL6a/wqwzTYiZGUFecQOSIHpYwkpJm8n2VRgYNsQ/XD7GP1CLv2',2);

