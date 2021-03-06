/* 
 * V1__createTaskModel.sql
 * Erstellt alle Datenbank-Artefakte für das fachliche Modul tasks.
 */

CREATE SEQUENCE S_TASK AS BIGINT
	MINVALUE 1000
	START WITH 1000
	CACHE 10
	NO CYCLE;
	
/*
 * Enthält alle Tasks der JEEDEMO Applikation
 */
CREATE TABLE T_TASK (
	TASK_ID BIGINT NOT NULL,
    SUBJECT VARCHAR(80) NOT NULL ,
    DESCRIPTION VARCHAR(1024) NOT NULL ,
    CATEGORY INT NOT NULL ,
    PRIORITY INT NOT NULL ,
    LIFECYCLE_STATE INT NOT NULL ,
    SUBMISSION_DATE TIMESTAMP NULL , 
    SUBMITTER_USER_ID VARCHAR(16) ,
    DUE_DATE TIMESTAMP NULL ,
    COMPLETION_RATE INT NOT NULL ,
    COMPLETION_DATE TIMESTAMP NULL ,
    COMPLETER_USER_ID VARCHAR(16) ,
    RESPONSIBLE_USER_ID VARCHAR(16) ,
    AFFECTED_PROJECT_ID VARCHAR(16),
    AFFECTED_APPLICATION_ID VARCHAR(8),
    AFFECTED_MODULE VARCHAR(32),
    AFFECTED_RESOURCE VARCHAR(256),
    ESTIMATED_EFFORT INT,
    EXPECTED_EFFORT INT,
    ACTUAL_EFFORT INT,
    OPT_LOCK_VERSION BIGINT NOT NULL,
    CREATED_BY VARCHAR(16) NOT NULL ,
    CREATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    LAST_MODIFIED_BY VARCHAR(16) NOT NULL,
    LAST_MODIFIED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX X_TASK ON T_TASK (TASK_ID);

ALTER TABLE T_TASK ADD PRIMARY KEY USING INDEX X_TASK;
