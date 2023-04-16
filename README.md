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
1. Product Service for creating and viewing products. It acts as a product catalog and it communicates with a MongoDB database.
2. Order Service for ordering products. It communicates with a MySQL database.
3. Inventory Service for checking if product is in stock or not. Also communicates with a MySQL database.
4. Notification Service sends notifications after the order is places. It is stateless and does not have a database.
- The Order Service communicates with the Inventory Service and Product Service synchronously to check the availability of products before placing an order. It also communicates with the Notification Service asynchronously to send notifications after a successful order placement.
- API Gateway provides a single entry point for users to communicate with the microservices. It acts as a gatekeeper for sending requests from users to different services. This way, users do not have to directly communicate with the host names or IP addresses of the microservices.

# Solution Architecture 🔍
![screenshot](https://github.com/zoltanvin/mini-shopify/blob/main/assets/high_level_architecture.png)


# Clone And Use 🔨

Prerequisites
- Make sure you have Java 17, Maven, Spring Boot, Spring Cloud, Kibana and a compatible IDE (e.g. IntelliJ IDEA, Eclipse) installed.

How to run the application using Docker
- Run `mvn clean package -DskipTests` to build the applications and create the docker image locally.
- Run `docker-compose up -d` to start the applications.

How to run the application without Docker
- Run `mvn clean verify -DskipTests` by going inside each folder to build the applications.
- After that run `mvn spring-boot:run` by going inside each folder to start the applications.


# Technologies Used 💡

- Spring Boot: for building microservices quickly and easily.
- Spring Cloud: for implementing microservices architectural patterns, such as service discovery, configuration management, and circuit breakers.
- MongoDB: for storing product data in the Product Service.
- MySQL: for storing order and inventory data in the Order Service and Inventory Service, respectively.
- RabbitMQ and Kafka: for implementing asynchronous communication between microservices.
- API Gateway: for providing a unified entry point for users to access different microservices.


</br>
⭐ Star me on GitHub — it helps!
