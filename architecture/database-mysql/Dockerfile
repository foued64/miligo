FROM mysql:latest

MAINTAINER zyra83@hotmail.com

ENV MYSQL_ROOT_PASSWORD rootroot

COPY miligo.sql /docker-entrypoint-initdb.d/miligo.sql
COPY my.cnf /etc/mysql/conf.d/config-file.cnf

EXPOSE 3306
