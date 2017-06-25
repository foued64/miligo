FROM payara/server-full:latest

MAINTAINER zyra83@hotmail.com

ENV ADMIN_USER admin
ENV ADMIN_PASSWORD admin

COPY mysql-driver-jdbc.jar /opt/payara41/glassfish/domains/domain1/lib
COPY miligo.war /opt/payara41/glassfish/domains/domain1/autodeploy

# ne pas éclater les commandes après démarrage du domaine
RUN \
$PAYARA_PATH/bin/asadmin start-domain && \
$PAYARA_PATH/bin/asadmin --user $ADMIN_USER --passwordfile=/opt/pwdfile create-jdbc-connection-pool --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --restype javax.sql.DataSource --property user=root:password=rootroot:DatabaseName=MILIGO:ServerName=database-mysql:port=3306 miligoPool && \
$PAYARA_PATH/bin/asadmin --user $ADMIN_USER --passwordfile=/opt/pwdfile create-jdbc-resource --connectionpoolid miligoPool jdbc/miligo

EXPOSE 4848 8009 9009 8080 8181
