DROP TABLE loggedInUser CASCADE CONSTRAINTS;
DROP TABLE creditCard CASCADE CONSTRAINTS ;
DROP TABLE identity CASCADE CONSTRAINTS;
DROP TABLE webAccount CASCADE CONSTRAINTS;
DROP TABLE note CASCADE CONSTRAINTS;  --unsure if correct
DROP TABLE passport CASCADE CONSTRAINTS;--unsure if correct

DROP sequence seqCreditCardId;
drop sequence seqIdentityId;
drop sequence seqNoteId;
drop sequence seqPassportId;
drop sequence seqWebAccountId;

CREATE TABLE loggedInUser (
    username VARCHAR2(50),
    pwd VARCHAR2(128), --verschlüsselt
    salt VARCHAR2(24),

    CONSTRAINT pkUser PRIMARY KEY(username)
);

CREATE TABLE creditCard (
    cardName VARCHAR2(50),
    cardNumber VARCHAR2(64), --verschlüsselt
    ownerName VARCHAR2(50),
    bankName VARCHAR2(50),
    expireDate VARCHAR2(5),
    provider VARCHAR2(20),
    additionalInformation VARCHAR2(150),
    securityCode INTEGER, --verschlüsselt
    userName VARCHAR2(40),
    id integer,
    constraint pkCreditCard primary key (id),
    CONSTRAINT fkCreditCardUser FOREIGN KEY (userName) REFERENCES loggedInUser (username)  ON DELETE CASCADE
);

CREATE TABLE webAccount (
    name VARCHAR2(50),
    websiteName VARCHAR2(50),
    websiteURL VARCHAR2(150),
    username VARCHAR2(50),
    password VARCHAR2(64), --verschlüsselt
    programUser VARCHAR2(50),
    additionalInformation VARCHAR2(100),
    id integer,

    constraint pkWebAccount primary key (id),
    CONSTRAINT fkWebAccount FOREIGN KEY(programUser) REFERENCES loggedInUser (username)  ON DELETE CASCADE
);

CREATE TABLE identity (
    username VARCHAR2(50),
    salutation VARCHAR2(10),
    firstName VARCHAR2(50),
    surName VARCHAR2(50),
    streetAdress VARCHAR2(50),
    cityAdress VARCHAR2(50),
    zipAdress INTEGER,
    stateAdress VARCHAR2(50),
    birthDate VARCHAR2(10),
    country VARCHAR2(50),
    additionalInformation varchar2(100),
    nr INTEGER,

    CONSTRAINT pkIdentity PRIMARY KEY(nr),
    CONSTRAINT fkIdentity FOREIGN KEY(username) REFERENCES loggedInUser (username)  ON DELETE CASCADE
);

CREATE TABLE passport (
    firstName VARCHAR2(64),
    surName VARCHAR2(64),
    nationality VARCHAR2(64),
    dateOfBirth VARCHAR2(20),
    placeOfBirth VARCHAR2(100),
    dateOfIssue VARCHAR2(20),
    expirationDate VARCHAR2(20),
    sex VARCHAR2(10),
    authority VARCHAR2(64),
    passportNumber VARCHAR2(64),
    additionalInformation VARCHAR2(64),
    username VARCHAR2(64),

    id integer,

    constraint pkPassport primary key (id),

    CONSTRAINT fkPassport FOREIGN KEY (username) REFERENCES loggedInUser(username)  ON DELETE CASCADE
);

CREATE TABLE note (
    text VARCHAR2(300),
    username VARCHAR2(64),
    title varchar2(64),
    id integer,
    constraint  pkNote primary key (id),
    CONSTRAINT fkNote FOREIGN KEY (username) REFERENCES loggedInUser(username)  ON DELETE CASCADE
);


  CREATE SEQUENCE seqWebAccountId
    START WITH 1;

CREATE SEQUENCE seqIdentityId
    START WITH 1;

CREATE SEQUENCE seqCreditCardId
    START WITH 1;

CREATE SEQUENCE seqPassportId
    START WITH 1;

CREATE SEQUENCE seqNoteId
    START WITH 1;

SELECT * FROM loggedInUser;
SELECT * FROM webAccount;
SELECT * FROM identity;
SELECT * FROM passPort;
SELECT * FROM creditCard;