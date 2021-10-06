CREATE DATABASE prefieromizona DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE USER 'prefieromizona'@'localhost' IDENTIFIED BY 'prefieromizona';

GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,CREATE TEMPORARY TABLES,DROP,INDEX,ALTER ON prefieromizona.* TO 'prefieromizona'@'localhost';