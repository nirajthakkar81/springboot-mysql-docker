From openjdk:11
copy ./target/sqltest-0.0.1-SNAPSHOT.jar sqltest-0.0.1-SNAPSHOT.jar
EXPOSE 8089
CMD ["java","-jar","sqltest-0.0.1-SNAPSHOT.jar"]