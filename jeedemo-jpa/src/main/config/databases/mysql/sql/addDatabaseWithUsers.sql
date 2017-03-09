CREATE DATABASE IF NOT EXISTS jeedemo_db CHARACTER SET = 'utf8';
CREATE USER 'jeedemo-admin' IDENTIFIED BY 'admin123';
GRANT USAGE ON *.* TO 'jeedemo-admin';
GRANT ALL ON jeedemo_db.* TO 'jeedemo-admin';
CREATE USER 'jeedemo-connect' IDENTIFIED BY 'connect123';
GRANT INSERT, SELECT, UPDATE, DELETE ON jeedemo_db.* TO 'jeedemo-connect';
FLUSH PRIVILEGES;
