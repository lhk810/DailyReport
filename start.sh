PROJECT_DIR=`git rev-parse --show-toplevel`
cd ${PROJECT_DIR}

case $1 in
    server)
        if [ $# -lt 3 ]; then
            echo "usage: $0 $1 MYSQL_ROOT_PASSWORD SPRING_PASSWORD"
            exit 1
        fi

        MYSQL_ROOT_PASSWORD=${1}
        SPRING_PASSWORD=${2}
        export MYSQL_ROOT_PASSWORD
        export SPRING_PASSWORD

        cd ${PROJECT_DIR}/report-spring
        ./gradlew bootJar
        docker build -t lhk810/daily-report-spring .

        cd ${PROJECT_DIR}

        envsubst < "docker/init.sql.in" > "docker/init.sql"
        docker-compose -f docker/docker-compose-server.yml up -d --build
        ;;
    client)
        docker-compose -f docker/docker-compose-client.yml up -d --build
        ;;
esac
