CREATE SCHEMA `408assignment3`;

CREATE TABLE userinfo
(
    ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    age INT
);

CREATE TABLE useremail
(
    ID INT,
    email VARCHAR(100),
    CONSTRAINT useremail_userinfo_ID_fk FOREIGN KEY (ID) REFERENCES userinfo (ID)
);

CREATE TABLE userdepartment
(
    ID INT,
    department VARCHAR(20),
    CONSTRAINT userdepartment_userinfo_ID_fk FOREIGN KEY (ID) REFERENCES userinfo (ID)
);

CREATE TABLE userphone
(
    ID INT,
    phone VARCHAR(20),
    CONSTRAINT userphone_userinfo_ID_fk FOREIGN KEY (ID) REFERENCES userinfo (ID)
);

CREATE TABLE userposition
(
  ID INT,
  position VARCHAR(20),
  CONSTRAINT userposition_userinfo_ID_fk FOREIGN KEY (ID) REFERENCES userinfo (ID)
)