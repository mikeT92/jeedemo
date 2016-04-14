/* 
 * V2__createUserModel.sql
 * Erzeugt alle Datenbank-Objekte für das User-Datenmodell.
 */

/*
 * Enthält alle registrierten Benutzer der JEEDEMO Applikation
 */
CREATE TABLE T_USER (
	USER_ID VARCHAR(16) PRIMARY KEY NOT NULL ,
    FIRST_NAME VARCHAR(64) NOT NULL ,
    LAST_NAME VARCHAR(64) NOT NULL ,
    FULL_NAME VARCHAR(128) NOT NULL ,
    GENDER INT NOT NULL,
    DATE_OF_BIRTH DATE NOT NULL ,
    EMAIL VARCHAR(64) NOT NULL ,
    PHONE VARCHAR(16) ,
    MOBILE VARCHAR(16) ,
    VERSION INT NOT NULL,
    CREATION_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LAST_MODIFICATION_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX X_USER
ON T_USER
(USER_ID ASC);
   
/*
 * Enthält die Passwörter aller registrierten Benutzer der JEETRAIN Applikation
 */
CREATE TABLE T_CREDENTIAL (
	USER_ID VARCHAR(16) PRIMARY KEY NOT NULL ,
    PASSWORD VARCHAR(256) NOT NULL,
    LAST_MODIFICATION_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX X_CREDENTIAL
ON T_CREDENTIAL
(USER_ID ASC);

/*
 * Enthält die unterstützten Rollen
 */
CREATE TABLE T_ROLE(
	ROLE_ID BIGINT PRIMARY KEY NOT NULL ,
    ROLE_NAME VARCHAR(32) NOT NULL ,
    LAST_MODIFICATION_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX X_ROLE
   ON T_ROLE
   (ROLE_ID);

CREATE UNIQUE INDEX X_ROLE_BY_NAME
   ON T_ROLE
   (ROLE_NAME ASC);

INSERT INTO T_ROLE VALUES (1, 'JEEDEMO_USER', CURRENT_TIMESTAMP);
INSERT INTO T_ROLE VALUES (2, 'JEEDEMO_ADMIN', CURRENT_TIMESTAMP);
   
/*
 * Enthält die Rollenzuweisungen aller JEETRAIN Benutzer
 */
CREATE TABLE T_USER_ROLE_ASSIGNMENT(
	USER_ID VARCHAR(16) NOT NULL,
	ROLE_ID BIGINT NOT NULL,
    LAST_MODIFICATION_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(USER_ID, ROLE_ID)
);

CREATE UNIQUE INDEX X_USER_ROLE_ASSIGNMENT
   ON T_USER_ROLE_ASSIGNMENT
   (USER_ID, ROLE_ID);

/*
 * Enthält alle User und deren Passwörter die Authentisierung über ein JDCB-Realm
 */   
CREATE VIEW V_JAAS_USERS 
AS SELECT USER_ID, PASSWORD
FROM T_CREDENTIAL;

/*
 * Enthält die Zuordnungen der Rollen zu allen Users für die Authentisierung über ein JDBC-Realm
 */   
CREATE VIEW V_JAAS_ROLES 
AS SELECT RA.USER_ID AS USER_ID, R.ROLE_NAME AS ROLE_NAME 
FROM T_USER_ROLE_ASSIGNMENT RA
JOIN T_ROLE R ON R.ROLE_ID = RA.ROLE_ID;
  