INSERT INTO User (user_id,user_email,user_password,user_enabled,user_name,user_last_name,user_age) VALUES(1,'andres@gmail.com','$2a$05$nUzCt3HxE5Cbmk1i/BDWJO8RapQekRfOHs9SpzoK0RWt3WzHBsQeS',1,'Andres','Gonzalez',37);
INSERT INTO User (user_id,user_email,user_password,user_enabled,user_name,user_last_name,user_age) VALUES(2,'guest@gmail.com','$2a$05$nUzCt3HxE5Cbmk1i/BDWJO8RapQekRfOHs9SpzoK0RWt3WzHBsQeS',1,'Invitado','',0);

INSERT INTO Authority (authority_id,authority) VALUES (1, 'ROLE_ADMIN');
INSERT INTO Authority (authority_id,authority) VALUES (2, 'ROLE_USER');
INSERT INTO Authority (authority_id,authority) VALUES (3, 'ROLE_GUEST');

INSERT INTO user_authority (user_id, authority_id) VALUES (1,1);
INSERT INTO user_authority (user_id, authority_id) VALUES (1,2);
INSERT INTO user_authority (user_id, authority_id) VALUES (2,3);