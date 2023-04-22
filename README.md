# <img src="http://mattballo.com/mindfruit/logo.png" width="40" /> MINDFRUIT 

## About

Mindfruit is a platform that allows you to share your ideas with the world. Plant the seeds of your thoughts and let your mind bear fruit on Mindfruit. With the Mindfruit API, you can create, edit, and publish your ideas, collaborate with others, and reach a wider audience. To use the Mindfruit API, please note the following:

## Installation

To install the Mindfruit app, use the following docker command: `docker-compose up`. Note that Spring Boot may fail a few times until the MySQL database is properly set up.

## How to use it?

To use endpoints, it's neccessary to be authenticated:
- To log in (http://localhost:8080/api/v1/login), use email address from the external API as the username and phone number from the same API as the password.
- Use generated JWT Token

After running application, list of available endpoints can be found here:
- **Swagger**: http://localhost:8080/api/v1/swagger-ui/index.html

## Specification

The application is built with the following technologies (make sure to have them installed):

- Spring Boot 3.0.5
- Java 17 + Maven
- MySQL
- Hibernate
- Springdoc OpenAPI + Swagger
- Spring Security
- Docker Compose

### Post Schema

The post consists of the following fields:

- id: integer
- userId: integer
- title: string
- body: string

### Functional Requirements

The Mindfruit API includes the following functional requirements:

- Create post: validated based on userID using an external API.
- Get post by id or userId: if the post is not found in the system, try to get it from an external API.
- Delete post
- Edit post

### Additional Functionalities

- Authentication
- Containerization using Docker

## Links

- **External API**: https://jsonplaceholder.typicode.com/
- **Swagger**: http://localhost:8080/api/v1/swagger-ui/index.html