<h1 align="center">
  <br>
  <a href="https://github.com/zoltanvin/royal-reserve-bank"><img src="https://github.com/zoltanvin/royal-reserve-bank/blob/main/assets/logo.png" alt="Royal Reserve Bank" width="300"></a>
  <br>
  Royal Reserve Bank
  <br>
</h1>

<h3 align="center">Experience the first-class service</a></h3>
<p align="center">
  <a href="#about-">About</a> •
  <a href="#microservices-">Microservices</a> •
  <a href="#solution-architecture-">Solution Architecture</a> •
  <a href="#clone-and-use-">Clone And Use</a> •
  <a href="#technologies-used-">Technologies Used</a>
</p>

# About 🚀

The Royal Reserve Bank is a digital banking platform built using microservices architecture, focused on using the latest Spring Cloud technologies along with Spring Boot to implement various architectural patterns such as service discovery, centralized configuration, distributed tracing, circuit breaker, centralized logging and event-driven architecture.

The platform is built using multiple microservices, including the Account API, Transaction API, Asset Management API and Notification API. These microservices communicate with each other synchronously and asynchronously to perform various banking activities, such as account creation, money transfers, asset management and sending email or SMS notifications. The project also uses Kafka message queue for asynchronous communication.

The solution architecture of the project includes the use of MongoDB, PostgreSQL and MySQL databases for data storage, API Gateway as a gateway for user requests, and surrounding services that enable the microservice architecture.

Utilized log analysis and monitoring tools such as Elasticsearch, Logstash and Kibana for searching, analyzing, and visualizing logs in the central repository.

# Microservices 📋

1. **Account API** for managing accounts. It acts as an account catalog and communicates with a MongoDB database.
2. **Transaction API** for processing transactions. It communicates with a PostgreSQL database.
3. **Asset Management API** for checking the availability of assets. It communicates with a MySQL database.
4. **Notification API** sends notifications to customers. It is stateless and does not have a database.
5. **API Gateway** provides a single entry point for customers to communicate with the microservices. It routes customer requests to the appropriate microservice and handles authentication, authorization, and security.

- The Transaction API communicates with the Account API and Asset Management API synchronously to check the availability of funds and assets before processing any transaction. It also communicates with the Notification API asynchronously to send notifications after a successful transaction.
# Solution Architecture 🔍

![image](https://github.com/zoltanvin/royal-reserve-bank/blob/main/assets/high_level_architecture.png)

# Clone And Use 🔨

To run the applications within Docker:

- Clone the repository and navigate to the project directory.
- Start the microservices using Docker Compose by running the command `docker-compose up -d`.

To run the application in your local environment:

- Ensure that you have Java 17, Maven, and a compatible IDE (such as IntelliJ IDEA or Eclipse) installed and properly configured.
- Start only the supporting services in Docker using Docker Compose by running the command `docker-compose docker-compose-infrastructure-services.yml up -d`.
- To launch the applications, run each application in its respective module. Note that the config-server module should be started first.
  </br>
- Build the applications by first running the config-server application, then execute the command `mvn clean install -pl !config-server`.
- To generate the documentation, run `mvn javadoc:aggregate` from the root directory. The generated Javadoc can be found in the target/site/apidocs folder. For convenience, I have exported the documentation to the javadoc folder.

# Technologies Used 💡

- **Spring Boot:** for simplifying the development and deployment of microservices by using pre-configured templates and tools.
- **API Gateway:** for managing and routing API requests from users to different microservices and providing a unified entry point.
- **Spring Cloud:** for implementing microservices architectural patterns, such as service discovery, configuration management, and circuit breakers.
- **Docker:** for containerization of microservices, making it easy to package and deploy the microservices across different environments.
- **MongoDB:** for storing account data in the Account API allowing flexibility and scalability in handling large amounts of unstructured data.
- **PostgreSQL:** for storing transaction data in the Transaction API.
- **MySQL:** for storing asset data in the Asset Management API.
- **JPA:** for implementing the Object Relational Mapping in the microservices, allowing Java applications to interact with databases and perform CRUD.
- **Kafka:** for implementing asynchronous communication between microservices.
- **Auth0:** for implementing authentication and authorization functionalities in the microservices, allowing secure access control.
- **Grafana:** for monitoring and logging the microservices, providing insights into the performance and health of the microservices.
- **Zipkin:** for distributed tracing in microservices architecture, allowing to track and analyze the flow of requests and performance analysis.

</br>
⭐ Star me on GitHub — it helps!
