# ip-info
REST API with endpoint to retrieve information about an IP and Stats

#Instalation
docker network create ip-network

docker run -d \
--name mysql \
--network=ip-network \
-p 3305:3306 \
-e MYSQL_ROOT_PASSWORD=123456789 \
mysql:5.7.33

docker build -f ip-info/Dockerfile ip-info/ -t frank/ip-info-api:v

docker push frank/ip-info-api:v

docker run -d \
--name ip-info-api \
--network=ip-network \
-p 8080:8080 \
--link mysql \
-e SPRING_DATASOURCE_URL=jdbc:mysql://mysql/iptrace?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&createDatabaseIfNotExist=true&useUnicode=yes&characterEnconding=UTF-8 \
-e SPRING_DATASOURCE_USERNAME=root \
-e SPRING_DATASOURCE_PASSWORD=123456789 \
-e SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver \
-e CITY_LATITUDE=-34.6083 \
-e CITY_LONGITUDE=-58.3712 \
-e DISTANCE_MEASURE_UNIT=KM \
-e CURRENCY_API_ACCESS_KEY=53653b935efd16cace9e945b66a4e053 \
-e CURRENCY_EXCHANGE=EUR \
-e CRON_TIME_EXCHANGE="0 1/1 0 * * ?" \
frank/ip-info-api:v