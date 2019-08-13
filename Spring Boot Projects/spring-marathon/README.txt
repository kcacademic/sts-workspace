mvn clean install
mvn dockerfile:build

docker push kchandrakant/myrepo:latest
docker run -p 9001:9001 kchandrakant/myrepo:latest

curl -X POST -H "Content-Type: application/json" -H "Cache-Control: no-cache" -d "@marathon.json" "http://localhost:8080/v2/apps"