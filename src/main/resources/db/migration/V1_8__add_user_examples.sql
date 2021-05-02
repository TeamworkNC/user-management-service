INSERT INTO user_info (birthday, description, login, logo_url, registration_date, email)
VALUES ('2000-01-01', 'admin', 'admin', '', '2021-01-01', 'admin@example.com');

INSERT INTO user_global_roles(user_id, global_role_id)
VALUES (1, 1); -- роль пользователя

INSERT INTO user_global_roles(user_id, global_role_id)
VALUES (1, 2); -- роль админа

INSERT INTO user_pass(password_hash, user_user_id)
VALUES ('$2a$10$3L5oNIeNTlq13WB88I0Qv.lf6wnipFPZvIqLay9UFp7wNsXSRQ.nC', 1);



INSERT INTO user_info (birthday, description, login, logo_url, registration_date, email)
VALUES ('2010-01-01', 'hi i am alan_wake', 'alan_wake', 'https://avatarfiles.alphacoders.com/102/thumb-1920-102095.jpg',
        '2021-01-01',
        'alan_wake@example.com');

INSERT INTO user_global_roles(user_id, global_role_id)
VALUES (2, 1); -- роль пользователя

INSERT INTO user_pass(password_hash, user_user_id)
VALUES ('$2a$10$xC9YVEJ76bagbI.AJj/eZ.j.ppilqqEz7vTx6mJHRx6C8rmbRbrqW', 2);



INSERT INTO user_info (birthday, description, login, logo_url, registration_date, email)
VALUES ('2012-01-01', 'hi i am dio_brando', 'dio_brando',
        'http://pm1.narvii.com/6692/bf258f67af4f1d9ee5a9556335be056473d4e3b7_00.jpg',
        '2021-01-01', 'dio_brando@example.com');

INSERT INTO user_global_roles(user_id, global_role_id)
VALUES (3, 1); -- роль пользователя

INSERT INTO user_pass(password_hash, user_user_id)
VALUES ('$2a$10$xC9YVEJ76bagbI.AJj/eZ.j.ppilqqEz7vTx6mJHRx6C8rmbRbrqW', 3);



INSERT INTO user_info (birthday, description, login, logo_url, registration_date, email)
VALUES ('2012-01-01', 'hi i am kazuma_kiryu', 'kazuma_kiryu',
        'https://static.wikia.nocookie.net/yakuza/images/3/3c/258794_1a.jpg/revision/latest/scale-to-width-down/310?cb=20210107153556&path-prefix=ru',
        '2021-01-01', 'kazuma_kiryu@example.com');

INSERT INTO user_global_roles(user_id, global_role_id)
VALUES (4, 1); -- роль пользователя

INSERT INTO user_pass(password_hash, user_user_id)
VALUES ('$2a$10$xC9YVEJ76bagbI.AJj/eZ.j.ppilqqEz7vTx6mJHRx6C8rmbRbrqW', 4);