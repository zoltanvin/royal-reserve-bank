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
  <a href="#configuration-%EF%B8%8F">Configuration</a> •
  <a href="#technologies-used-">Technologies Used</a> •
  <a href="#api-documentation-">API Documentation</a> •
  <a href="#testing-">Testing</a> •
  <a href="#contributing-">Contributing</a> •
  <a href="#license-">License</a> •
  <a href="#troubleshooting-and-support-">Troubleshooting and Support</a>
</p>

# About 🚀

The Royal Reserve Bank is a digital banking solution built using microservices architecture, focused on using the latest Spring Cloud technologies along with Spring Boot to implement various architectural patterns such as service discovery, centralized configuration, distributed tracing, circuit breaker, centralized logging, cache-aside and event-driven architecture.
<p>
It consists of several independent modules that work together to provide a scalable and fault-tolerant system. The microservices communicate with each other through well-defined APIs and leverage databases for data storage to perform various banking activities, such as account creation, money transfers, asset management and sending email or SMS notifications.

# Microservices 📋

1. **Account API:** The Account API module provides functionalities for managing accounts. It acts as an account catalog and communicates with a MongoDB database. It allows customers to create new accounts, retrieve account details, update account information, and perform operations such as balance inquiries and transaction history retrieval.
<!--  -->
2. **Transaction API:** The Transaction API module handles the processing of financial transactions. It communicates with a PostgreSQL database to store transaction records. Customers can initiate transfers through this API. The Transaction API ensures data consistency by communicating synchronously with the Account API and Asset Management API to check the availability of funds and assets before processing any transaction.
<!--  -->
3. **Asset Management API:** The Asset Management API module communicates with a MySQL database that maintains asset data. This API enables customers to query asset availability, such as checking stocks, bonds, or other financial instruments. The Transaction API interacts with the Asset Management API to ensure that sufficient assets are available for transactions.
<!--  -->
4. **Notification API:** The Notification API module is responsible for sending notifications to customers. It is a stateless microservice that does not have its own database. Instead, it communicates asynchronously with other modules to send notifications after specific events, such as successful transactions or account updates. The Notification API leverages messaging system named Apache Kafka to ensure reliable message delivery and scalable notification processing.
<!--  -->
5. **API Gateway:** The API Gateway module provides a single entry point for customers to interact with the microservices. It acts as a reverse proxy, routing customer requests to the appropriate microservice. The API Gateway handles authentication, authorization, and security concerns, ensuring that only authorized requests reach the microservices. The API Gateway improves the overall system performance and provides a unified interface for clients.
<!--  -->
6. **Config Server:** The Config Server module centralizes the configuration management for the microservices. It allows the microservices to retrieve their configuration details from a centralized location, simplifying the configuration process. The Config Server supports features such as environment-specific configurations, and dynamic configuration updates without requiring service restarts. It enhances the maintainability and scalability of the microservices architecture.
<!--  -->
7. **Discovery Server:** The Discovery Server is responsible for service discovery within the microservices architecture. It acts as a central registry where each microservice registers itself upon startup. The Discovery Server enables microservices to locate and communicate with each other without hard-coded dependencies. It uses technologies like Netflix Eureka to provide service discovery capabilities.

- The different modules in the Royal Reserve Bank project work together to provide a comprehensive banking solution. They communicate with each other through well-defined APIs and leverage databases to store and retrieve relevant data. The microservices architecture enables modularity, scalability, and fault tolerance, allowing each module to be developed, deployed, and scaled independently.

# Solution Architecture 🔍

The Royal Reserve Bank project is designed using a microservices architecture, which is a pattern that structures an application as a collection of loosely coupled services. Each service represents a specific business capability and can developed independently. The microservices communicate with each other through well-defined APIs, enabling them to work together to provide a scalable banking solution.
<!--  -->
![image](https://github.com/zoltanvin/royal-reserve-bank/blob/main/assets/high_level_architecture.png)

## Architecture Patterns

The Royal Reserve Bank project leverages various architectural patterns to ensure scalability, fault tolerance, and maintainability of the system. The key architectural patterns used in the project include:

- **Service Discovery:** The microservices use the Discovery Server for service registration and discovery. This pattern enables microservices to locate and communicate with each other dynamically without the need for hard-coded endpoints. It improves the system's resilience and scalability by allowing services to adapt to changes in the environment and handle load balancing efficiently.
<!--  -->
- **Centralized Configuration:** The Config Server provides centralized management of configuration settings for all microservices. This pattern simplifies the configuration process and allows for dynamic updates without the need to restart services. It enhances the maintainability of the microservices and makes it easier to manage configurations in different environments.
<!--  -->
- **Distributed Tracing:** The project uses Zipkin, a distributed tracing system, to track and analyze the flow of requests across microservices. Distributed tracing allows developers and system administrators to understand the performance and behavior of requests as they travel through multiple microservices. By instrumenting the microservices with tracing code, Zipkin collects timing and tracing data, which can be used to identify bottlenecks, troubleshoot issues, and optimize the system's performance.
<!--  -->
- **Event-driven Architecture:** The Notification API module utilizes Apache Kafka, a distributed streaming platform, for asynchronous communication. Kafka provides a reliable and scalable messaging system that decouples the sender and receiver of messages. When a microservice needs to send a notification, it publishes a message to a specific Kafka topic. The Notification API, acting as a consumer, receives the message and processes it accordingly. Asynchronous communication allows the system to handle notification processing separately from the main request flow, improving performance and scalability.
<!--  -->
- **Database per Microservice:** Each microservice in the Royal Reserve Bank project has its own dedicated database. The Account API communicates with a MongoDB database, the Transaction API uses PostgreSQL, and the Asset Management API interacts with a MySQL database. This approach of having a database per microservice ensures data isolation and autonomy. It allows each microservice to manage its own data schema, optimize database operations based on specific requirements, and scale independently as needed.
<!--  -->
- **Cache-aside Pattern:** Caching plays a crucial role in enhancing performance and reducing database load in microservices architecture. The Account API utilizes Redis template, while the Asset and Transaction API leverage Spring Cache. When a request requires data, the microservice first checks the cache. If the data is available, it is retrieved from the cache, avoiding the need to fetch it from the database. This caching strategy efficiently reduces response times and enhances the overall performance of the system.
<!--  -->
- **Circuit Breaker Pattern:** The Circuit Breaker pattern is used to handle faults and failures in distributed systems. It acts as a safeguard to prevent cascading failures when a microservice is unavailable or experiencing high latency. The Circuit Breaker is implemented using resilience4j library in the Transaction API. If it detects failures or slow responses, it "opens the circuit" and redirects subsequent requests to a fallback mechanism or returns an error response directly. This helps to protect the overall system from overloading and allows it to gracefully degrade when dependencies are unavailable.
<!-- - **Centralized Logging:** In a microservices architecture, it's crucial to have a centralized logging system to collect and analyze logs from various microservices. Centralized logging enables easy monitoring, troubleshooting, and analysis of the system's behavior. The microservices in the Royal Reserve Bank project send their logs to ELK platforms (Elasticsearch, Logstash, and Kibana). These platforms allow developers and operations teams to search, filter, and visualize logs, making it easier to detect and diagnose issues, track system performance, and ensure compliance with security and operational requirements. -->
- **Containerization and Orchestration:** The microservices are containerized using Docker, allowing them to run in isolated environments with their dependencies. Containerization provides consistency in deploying microservices across different environments.
<!-- The containerized microservices are then orchestrated using a container orchestration platform called Kubernetes. Kubernetes manages the deployment, scaling, and monitoring of the microservices, ensuring high availability and fault tolerance. -->
- **Security:** The Royal Reserve Bank project incorporates security measures throughout the architecture. The API Gateway handles authentication and authorization, ensuring that only authenticated and authorized requests reach the microservices. The microservices themselves follow secure coding practices and implement appropriate security controls, such as input validation, encryption of sensitive data, and protection against common vulnerabilities.

# Clone And Use 🔨

## To use the Reserve Bank application, follow these steps:

1. Ensure that all the microservices are running.
2. Refer to the API documentation for detailed information on available endpoints, request/response formats, and authentication requirements.
3. Interact with the API using tools such as cURL or Postman. For example, to create a new bank account, send a POST request to `http://localhost:8080/accounts` with the required parameters in the request body.

### To run the microservices within Docker:

- Clone the repository and navigate to the project directory.
- Start the microservices using Docker Compose by running the command `docker-compose up -d`.

### To run the microservices in your local environment:

- Ensure that you have Java 17, Maven, and a compatible IDE (such as [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) or [Eclipse](https://www.eclipse.org/downloads/)) installed and properly configured.
- Clone the repository and navigate to the project directory.
- Start only the supporting services in Docker using Docker Compose by running the command `docker-compose docker-compose-infrastructure-services.yml up -d`.
- To launch the applications, run each application in its respective module. Note that the config-server module should be started first.

### To build the applications:

- Start the config-server application.
- Open a terminal or command prompt and navigate to the project's root directory.
- Run the command `mvn clean install -pl !config-server` to build all the applications.

# Configuration ⚙️

The [config-files](https://github.com/zoltanvin/royal-reserve-bank/tree/main/config-files) directory and the [Docker Compose YAML files](https://github.com/zoltanvin/royal-reserve-bank/blob/main/docker-compose.yml) already contain the necessary configurations for each microservice. You can find configuration files for MongoDB, PostgreSQL, Grafana, MySQL, and other services. These files define settings such as usernames, passwords, connection URLs, access tokens, and other specific configurations required by each service.

<!--  -->
In the Docker Compose YAML file, you will find the service definitions for each container, including environment variables and volume mappings that specify the necessary configuration details.
<!--  -->
It is important to mention that if you encounter any conflicts with port numbers or network configurations already in use, you will need to modify the Docker Compose configuration to align with your specific setup and requirements.
<!--  -->
To configure Grafana, use an import from the running Prometheus configuration and set up a dashboard with dashboard ID 11378, you can follow these steps:

- Ensure that you have Grafana and Prometheus running in Docker.
- Open your web browser and access the Grafana dashboard by navigating to `http://localhost:3000` (or the appropriate URL if you have a different setup).
- Log in to Grafana using your username and password set in the Docker Compose YAML file. The default credentials are (admin/password).
- Once logged in, go to the Configuration section in the left-hand menu.
- In the Configuration section, select the Data Sources option.
- Click on the "Add data source" button to add a new data source for Prometheus.
- Provide the necessary configuration for Prometheus, including the URL and authentication details if required. Save the configuration.
- After adding the Prometheus data source, go back to the main dashboard and click on the plus icon on the left-hand menu to create a new dashboard.
- In the "New Dashboard" view, click on the "Import" button.
- In the "Import via panel JSON" section, add ID `11378`.
- Review the imported dashboard and make any necessary modifications or adjustments.
- You can now view and interact with the imported dashboard, which will display the metrics and visualizations based on the Prometheus data source.

# Technologies Used 💡

The Royal Reserve Bank project utilizes the following technologies and frameworks:

- **Java 17:** for implementing the microservices.
- **Spring Boot:** for simplifying the development and deployment of microservices by using pre-configured templates and tools.
- **Spring Cloud:** for implementing microservices architectural patterns, such as service discovery, configuration management, and circuit breakers.
- **Docker:** for containerization of microservices, making it easy to package and deploy the microservices across different environments.
- **API Gateway:** for managing and routing API requests from users to different microservices and providing a unified entry point.
- **JPA:** for implementing the Object Relational Mapping in the microservices, allowing Java applications to interact with databases and perform CRUD.
- **MongoDB:** for storing account data in the Account API allowing flexibility and scalability in handling large amounts of unstructured data.
- **PostgreSQL:** for storing transaction data in the Transaction API.
- **MySQL:** for storing asset data in the Asset Management API.
- **Kafka:** for implementing asynchronous communication between microservices.
- **Auth0:** for implementing authentication and authorization functionalities in the microservices, allowing secure access control.
- **Grafana:** for monitoring and logging the microservices, providing insights into the performance and health of the microservices.
- **Zipkin:** for distributed tracing in microservices architecture, allowing to track and analyze the flow of requests and performance analysis.
- **Redis:** for caching frequently accessed data in the microservices, improving performance by reducing the load on the underlying data sources.

For a complete list of dependencies and versions, please refer to the `pom.xml` files in the respective module directories.

# API Documentation 📚

The Reserve Bank API documentation provides detailed information about the available endpoints, their functionalities, request/response formats, and authentication requirements. The documentation is generated from the Javadoc comments within the source code.
<!-- -->
To access the full API documentation, please run `mvn javadoc:aggregate` from the root directory. The generated Javadoc will be found in the target/site/apidocs folder. For convenience, I have exported the documentation to the javadoc folder in the root directory.

<!-- add section about swagger doc here -->

# Testing ✅

The project includes comprehensive unit tests and integration tests for each microservice. Additionally, you can use Postman to interact with the Royal Reserve Bank APIs and perform manual testing. The project provides a Postman collection and environment file for easy import and configuration.

### Automated Testing

To run the tests, follow these steps:

- Start the config-server application.
- Open a terminal or command prompt and navigate to the project's root directory.
- To run all the unit tests, use the command `mvn test`.
- If you specifically want to run the integration tests, use the command `mvn failsafe:integration-test`.
- Alternatively, if you prefer to run only the unit tests, use the command `mvn test -Dgroups="!integration"`.

### Manual Testing with Postman

To perform manual testing using Postman, follow these steps:

- Install Postman if you haven't already.
- Import the provided Postman [collection](https://github.com/zoltanvin/royal-reserve-bank/blob/main/postman-collection.json) and [environment](https://github.com/zoltanvin/royal-reserve-bank/blob/main/postman-environment.json) JSON files located in the root directory.
- Select the imported "Royal Reserve Bank" collection and choose the desired request from the available endpoints.
- Set the imported "Royal Reserve Bank" environment as the active environment in Postman.
- Run the "Authorization" POST request to receive authorization tokens.
- Customize the request parameters, headers, and body as required.
- Click the "Send" button to execute the request and observe the response.
- Analyze the response data and verify that it matches the expected behavior.

Remember to ensure that the Royal Reserve Bank microservices are running before executing the requests in Postman to ensure successful communication with the APIs.

<!--
# Deployment

The project can be deployed to various environments such as development, staging, and production. Please refer to the individual module directories for instructions on deploying each microservice.

# Continuous Integration/Deployment

The project uses a CI/CD pipeline configured with Jenkins. Any changes pushed to the main branch trigger an automatic build and deployment process to the staging environment. The CI/CD configuration details can be found in the Jenkinsfile in the project root directory.
 -->

# Contributing 🤝

Contributions to the Reserve Bank project are welcome! If you encounter any bugs, have feature requests, or would like to contribute code, please feel free to submit a pull request. I appreciate your contributions and kindly ask you to follow the standard pull request guidelines.

# License 🌐

The Royal Reserve Bank project is licensed under the [MIT License.](https://github.com/zoltanvin/royal-reserve-bank/blob/main/LICENSE)

# Troubleshooting and Support ❓

I'm here to help! If you have any questions, feedback, or need assistance, please feel free to reach out to me. You can send me a message via email [here.](mailto:zoltanvin@protonmail.com)

<p>
⭐ Star me on GitHub — it helps!
