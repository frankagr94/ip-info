# ip-info
REST API para geolocalizar una IP y consultar información sobre su país de origen.

# Información
Tecnologías Usadas: para el desarrollo de la API, se utilizó JAVA 11 con Spring Boot Framework, SwaggerIO (io.springfox), para persistencia Hibernate conectado a MySQL, Docker, IntelliJ como IDE, Github y Alojamiento en AWS.

Requerimientos:
•	Docker
•	MySQL Server


#Instalación
docker network create ip-network

docker run -d \
--name mysql \
--network=ip-network \
-p 3305:3306 \
-e MYSQL_ROOT_PASSWORD=123456789 \
mysql:5.7.33

docker build -f ip-info/Dockerfile ip-info/ -t frankagr94/ip-info-api:v1

docker push frankagr94/ip-info-api:v1

docker run -d \
--name ip-info-api \
--network=ip-network \
-p 8080:8080 \
--link mysql \
-e SPRING_DATASOURCE_URL='jdbc:mysql://mysql/iptrace?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&createDatabaseIfNotExist=true&useUnicode=yes&characterEnconding=UTF-8' \
-e SPRING_DATASOURCE_USERNAME=root \
-e SPRING_DATASOURCE_PASSWORD=123456789 \
-e SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver \
-e CITY_LATITUDE=-34.6083 \
-e CITY_LONGITUDE=-58.3712 \
-e DISTANCE_MEASURE_UNIT=KM \
-e CURRENCY_PROCESS_ACTIVE=false \
-e CURRENCY_API_ACCESS_KEY=53653b935efd16cace9e945b66a4e053 \
-e CURRENCY_EXCHANGE=EUR \
-e CRON_TIME_EXCHANGE="0 1/1 0 * * ?" \
frankagr94/ip-info-api:v1

Parámetros del Docker Run de la Aplicación:
SPRING_DATASOURCE_URL: corresponde a la URL de conexión con todos los parámetros de conectividad que se necesiten.

SPRING_DATASOURCE_USERNAME: corresponde al usuario que se utilizará para conectar con la base de datos.

SPRING_DATASOURCE_PASSWORD: corresponde a la contraseña del usuario de la base de datos.

SPRING_DATASOURCE_DRIVER_CLASS_NAME: corresponde al driver de conexión que se utiliza, en el caso del ejercicio, se utiliza MYSQL (solo para futuras implementaciones con otros manejadores).

CITY_LATITUDE: corresponde a la latitud de la ciudad base que se configurará en la API, para el ejercicio se configura la latitud de Buenos Aires.

CITY_LONGITUDE: corresponde a la longitud de la ciudad base que se configurará en la API, para el ejercicio se configura la longitud de Buenos Aires.

DISTANCE_MEASURE_UNIT: corresponde a la unidad de medida que se usará dentro de la aplicación y a la cual se convertirán las distancias, por defecto serían kilómetros. Posibles valores: KM: Kilómetros, NM: Millas Náuticas, MI: Millas.

CURRENCY_PROCESS_ACTIVE: indica si el proceso de actualización de las monedas y tasas de cambio, estará activo o no.

CURRENCY_API_ACCESS_KEY: corresponde a la API Key de acceso para el servicio de data.fixer.io, el cual nos proporciona las monedas y tasas actualizadas.

CURRENCY_EXCHANGE: corresponde a la moneda base con la que trabajará la API, por defecto tenemos configurado el Euro (EUR) como moneda base. Para cambiar, buscar el código de la moneda a configurar.

CRON_TIME_EXCHANGE: corresponde a la frecuencia en la que se ejecutará el proceso de actualización de monedas y tasas, está en formato CRON y por defecto tiene un valor de cada 1 hora. 


Mas información, ver documentación...