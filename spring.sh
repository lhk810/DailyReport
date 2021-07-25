PROJECT_DIR=`git rev-parse --show-toplevel`

case $1 in
    build)
        cd ${PROJECT_DIR}/report-spring
        ./gradlew bootJar
        docker build -t daily-report-spring .
        ;;
    deploy)
        if [ $# -lt 2 ]; then
            echo "usage $0 $1 springuser_password [mysql_host]"
            exit 1
        fi
        docker run -e SPRING_PASSWORD=$2 -e MYSQL_HOST=$3 -p 8080:8080 -t daily-report-spring
        ;;
    bootRun)
        if [ $# -lt 2 ]; then
            echo "usage $0 $1 springuser_password [mysql_host]"
            exit 1
        fi
        export SPRING_PASSWORD=$2
        export MYSQL_HOST=$3
        cd ${PROJECT_DIR}/report-spring
        ./gradlew bootRun
        ;;
esac        
