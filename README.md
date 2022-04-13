First Fetch Docker Image of My SQL 5.6 for Mac M1 add platform option

docker pull mysql:5.6 --platform linux/x86_64


Launch mysql 5.6 container using following command

docker container run --name mysqldbcontainer  -p 3306:3306  -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=springbootdb  -e MYSQL_USER=sqladmin -e MYSQL_PASSWORD=password -d --platform linux/x86_64 mysql:5.6

Edit application.properties file of your Spring Boot application and modify the host name of jdbc url as container name which is mysqldbcontainer in this case

spring.datasource.url=jdbc:mysql://mysqldbcontainer:3306/springbootdb

Verify the comtainer name of launched mysql on your local

docker container ls

Copy the container id and use the following command to inspect mysql in your mysql docker container

docker container exec -it my_sql_comtainer_id bash

Launch mysql in this container using following command

mysql -u sqladmin -p password


Go to the created database which is springbootdb in this case using following command

use springbootdb

Build your spring boot jar by skipping tests as it will not understand the hostname as container name in application.properties

mvn clean install -DskipTests

Edit the Dockerfile in the root path of this project and add the jar name in copy step which will be generated in target folder


Build Docker Image by going to the root folder of your application

docker build -t mydemo-docker .


Launch the Spring Boot Docker container and link it with mysql container using following command

docker run -d -p 8089:8089 --name mydemo-docker --link mysqldbcontainer:mysql mydemo-docker


Verify the logs of launched Spring Boot application using following command 

docker container logs mydemo-docker





