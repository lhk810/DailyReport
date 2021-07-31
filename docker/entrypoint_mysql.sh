sh /docker-entrypoint.sh

mysqld --user=root

mysqladmin -u root -p password $MYSQL_ROOT_PASSWORD

mysql -uroot -p$MYSQL_ROOT_PASSWORD mysql -e "create database daily_task;"
mysql -uroot -p$MYSQL_ROOT_PASSWORD mysql -e "create user 'springuser'@'%' identified by '$SPRING_PASSWORD';"
mysql -uroot -p$MYSQL_ROOT_PASSWORD mysql -e "ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '$MYSQL_ROOT_PASSWORD';"
mysql -uroot -p$MYSQL_ROOT_PASSWORD mysql -e "ALTER USER 'springuser'@'%' IDENTIFIED WITH mysql_native_password BY '$SPRING_PASSWORD';"
mysql -uroot -p$MYSQL_ROOT_PASSWORD mysql -e "GRANT ALL PRIVILEGES ON daily_task.* TO 'springuser'@'%';"
