case $1 in
    start)
        if [ $# -ne 2 ]; then
            echo "usage: $0 $1 MYSQL_ROOT_PASSWORD"
            exit 1
        fi
        docker run --rm --name mysql -e MYSQL_ROOT_PASSWORD=$2 -v mysql-volume:/var/lib/mysql -p 3306:3306 mysql:8
        ;;
    init)
        if [ $# -ne 3 ]; then
            echo "usage: $0 $1 MYSQL_ROOT_PASSWORD springuser_password"
            exit 1
        fi
        SPRING_PASSWORD=$3
        export SPRING_PASSWORD
        docker exec mysql mysql -uroot -p$2 mysql -e "create database daily_task;"
        docker exec mysql mysql -uroot -p$2 mysql -e "create user 'springuser'@'%' identified by '$3';"
        docker exec mysql mysql -uroot -p$2 mysql -e "ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '$2';"
        docker exec mysql mysql -uroot -p$2 mysql -e "ALTER USER 'springuser'@'%' IDENTIFIED WITH mysql_native_password BY '$3';"
        docker exec mysql mysql -uroot -p$2 mysql -e "GRANT ALL PRIVILEGES ON daily_task.* TO 'springuser'@'%';"
        ;;
    bash-shell)
        docker exec -it mysql bash
        ;;
esac
