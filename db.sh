case $1 in
    start)
        docker run --rm --name mysql -e MYSQL_ROOT_PASSWORD=$2 -v mysql-volume:/var/lib/mysql -d -p 3306:3306 mysql:8.0.26
        ;;
    bash-shell)
        docker exec -it mysql bash
        ;;
esac
