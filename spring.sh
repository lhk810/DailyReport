PROJECT_DIR=`git rev-parse --show-toplevel`

case $1 in
    build)
        cd ${PROJECT_DIR}/report-spring
        export SPRING_PASSWORD=$2
        ./gradlew bootBuildImage --imageName=daily-report-spring
        ;;
    deploy)
        if [ $# -lt 2 ]; then
            echo "usage $0 $1 springuser_password [mysql_host]"
            exit 1
        fi
        docker run -e SPRING_PASSWORD=$2 -p 8080:8080 -t daily-report-spring
        ;;
esac        
