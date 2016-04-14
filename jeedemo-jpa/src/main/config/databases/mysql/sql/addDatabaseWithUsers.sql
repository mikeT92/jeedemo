CREATE DATABASE jeedemo_db CHARACTER SET = 'utf8';
CREATE USER 'jeedemo-admin'@'localhost' IDENTIFIED BY 'admin123';
GRANT USAGE ON *.* TO 'jeedemo-admin'@'localhost';
GRANT ALL ON jeedemo_db.* TO 'jeedemo-admin'@'localhost';
CREATE USER 'jeedemo-connect'@'localhost' IDENTIFIED BY 'connect123';
GRANT INSERT, SELECT, UPDATE, DELETE ON jeedemo_db.* TO 'jeedemo-connect'@'localhost';
FLUSH PRIVILEGES;
