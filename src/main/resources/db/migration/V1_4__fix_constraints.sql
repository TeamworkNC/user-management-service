ALTER TABLE achievement_info
    ALTER COLUMN name SET NOT NULL;

ALTER TABLE global_role_info
    ALTER COLUMN name SET NOT NULL;

ALTER TABLE user_info
    ADD CONSTRAINT UC_UserInfo_Login UNIQUE (login);

ALTER TABLE user_info
    ADD CONSTRAINT UC_UserInfo_Email UNIQUE (email);

ALTER TABLE user_info
    ADD CONSTRAINT UC_UserInfo_FirstName UNIQUE (first_name);

ALTER TABLE user_info
    ADD CONSTRAINT UC_UserInfo_LastName UNIQUE (last_name);

ALTER TABLE user_info
    ALTER COLUMN registration_date SET NOT NULL;