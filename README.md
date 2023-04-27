<h1 align="center">
  <br>
  <a href="https://github.com/zoltanvin/mini-shopify"><img src="https://github.com/zoltanvin/mini-shopify/blob/main/assets/logo.png" alt="Mini Shopify" width="200"></a>
  <br>
  Mini Shopify
  <br>
</h1>

<h3 align="center">The E-commerce platform</a></h3>
<p align="center">
  <a href="#about-">About</a> •
  <a href="#microservices-">Microservices</a> •
  <a href="#solution-architecture-">Architecture</a> •
  <a href="#clone-and-use-">Clone And Use</a> •
  <a href="#technologies-used-">Technologies Used</a>
</p>

# About 🚀

This project is an online shop application using microservices architecture, focused on using the latest Spring Cloud technologies along with Spring Boot to implement various architectural patterns such as service discovery, centralized configuration, distributed tracing, circuit breaker, centralized logging and event-driven architecture.

The project involves building several microservices, including a product service, order service, inventory service and notification service. These services communicate with each other synchronously and asynchronously to perform tasks such as creating and viewing products, placing orders, checking inventory and sending email notifications. The project also utilizes popular message queues like RabbitMQ and Kafka for asynchronous communication.

The solution architecture of the project includes the use of MongoDB and MySQL databases for data storage, API Gateway as a gateway for user requests, and surrounding services that enable the microservice architecture.

Utilized log analysis and monitoring tools such as Elasticsearch, Logstash and Kibana for searching, analyzing, and visualizing logs in the central repository.

# Microservices 📋

1. **Product API** for creating and viewing products. It acts as a product catalog and it communicates with a MongoDB database.
2. **Order API** for ordering products. It communicates with a MySQL database.
3. **Inventory API** for checking if product is in stock or not. Also communicates with a MySQL database.
4. **Notification API** sends notifications after the order is places. It is stateless and does not have a database.
5. **API Gateway** provides a single entry point for users to communicate with the microservices. It acts as a gatekeeper for sending requests from users to different services. This way, users do not have to directly communicate with the host names or IP addresses of the microservices.

- The Order API communicates with the Inventory API and Product API synchronously to check the availability of products before placing an order. It also communicates with the Notification API asynchronously to send notifications after a successful order placement.

# Solution Architecture 🔍

![image](https://github.com/zoltanvin/mini-shopify/blob/main/assets/high_level_architecture.png)


<details>
  <summary>More detail</summary>

  ![image](https://github.com/zoltanvin/mini-shopify/blob/main/assets/logical_architecture.png)

</details>

# Clone And Use 🔨

Prerequisites

- Make sure you have Java 17, MongoDB, Docker, MySQL, Maven, Spring Boot, Spring Cloud, Kibana and a compatible IDE (e.g. IntelliJ IDEA, Eclipse) installed and configured.

To run the application using Docker:

- Build the application by running `mvn clean package -DskipTests` and create the docker image locally.
- Start the applications using Docker Compose by running `docker-compose up -d`.

To run the application without Docker:

- Build the application by running `mvn clean verify -DskipTests` inside each folder.
- Start the applications by running `mvn spring-boot:run` inside each folder.

# Technologies Used 💡

- **Spring Boot:** for simplifying the development and deployment of microservices by using pre-configured templates and tools.
- **Spring Cloud:** for implementing microservices architectural patterns, such as service discovery, configuration management, and circuit breakers.
- **MongoDB:** for storing product data in the Product Service allowing flexibility and scalability in handling large amounts of unstructured data.
- **MySQL:** for storing order and inventory data in the Order Service and Inventory Service.
- **RabbitMQ and Kafka:** for implementing asynchronous communication between microservices.
- **API Gateway:** for managing and routing API requests from users to different microservices and providing a unified entry point.
- **JPA:** for implementing the Object Relational Mapping in the microservices, allowing Java applications to interact with databases and perform CRUD.
- **Docker:** for containerization of microservices, making it easy to package and deploy the microservices across different environments.
- **Grafana:** for monitoring and logging the microservices, providing insights into the performance and health of the microservices.

<details>
  <summary>Notes for myself</summary>

docker pull postgres
docker run --name order-api -p 5432:5432 -e POSTGRES_USER=root -e POSTGRES_PASSWORD=admin -d postgres

docker pull mysql
docker run --name inventory-api -p 3306:3306 -e MYSQL_ROOT_PASSWORD=pwd -e MYSQL_USER=admin -d mysql

docker exec -it inventory-api  mysql -uroot -p
CREATE USER 'admin'@'172.17.0.1' IDENTIFIED BY 'pwd';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'172.17.0.1' WITH GRANT OPTION;
flush privileges;
exit

docker pull container-registry.oracle.com/database/free
docker run -d --name=inv-api -p 1521:1521 -e ORACLE_SID=oracle -e ORACLE_PWD=pwd container-registry.oracle.com/database/free
-p 5500:5500

docker pull mcr.microsoft.com/mssql/server
docker run --name inv -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=passworD!" -e "MSSQL_PID=Developer" -p 1433:1433 -d mcr.microsoft.com/mssql/server
docker run --name inv -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=passworD!" -e "MSSQL_PID=Developer" -e "MSSQL_USER=user" -e "MSSQL_PASSWORD=passworD!" -p 1433:1433 -d mcr.microsoft.com/mssql/server
docker exec -it inv /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P passworD!
GRANT CONTROL SERVER TO user

docker run -p 8181:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:21.1.0 start-dev

docker run --name zipkin -d -p 9411:9411 openzipkin/zipkin

docker compose up -d
</details>

</br>
⭐ Star me on GitHub — it helps!
