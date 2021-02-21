FROM openjdk:11-oracle
ENTRYPOINT ["java","-Dspring.profiles.active=qa","-jar","ip-info-0.0.1.jar"]
COPY target/ip-info-0.0.1.jar .