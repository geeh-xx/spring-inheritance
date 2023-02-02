<img src="readme/icon.png" align="right" />

# Awesome README [![Awesome](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg)](https://github.com/sindresorhus/awesome)
> The best README(or trying to do) what are you going to see today.
Codelitt: Challenge made for the challenge of building a crud using rest full api.

## Getting Started

For this challenge was used the design pattern strategy and inheritance for define the entities and solid principes
For the package definition an adaptation of hexagonal architecture was made.

A factory class was created which, given the member's type, decides its specification.

this project is running on http://java-spring-interview-project-rangel-so.us-east-1.elasticbeanstalk.com/swagger-ui/index.html

### Prerequisites

The following technologies were used im this project


```
Spring Boot - for development a api restful.
Spring Data - for persistence.
H2 - for in memory database.
Liquibase - manage database changes
feign - for create quest http
Swagger - for api documentation.
terraform - iac to provide resources
docker - for create a container for app
aws - public cloud
rds - to database
junit - to create unit tests
```
an iac folder was created that contains the terraform used in the creation of the ebs
the docker file for creating the image

### Usage

to use the project locally just clone and run it in your ide
the local profile is activated by default and the h2 memory bank is used,
in this mode ALL DATA IS LOST WHEN RESTART THE APPLICATION

to change the profile just inform the respective profile

```
spring.profiles.active=local

spring.profiles.active=dev
```

The dev profile now is running on aws using elastic beanstalk and using rds for database.

The swagger is running on local address:
```
localhost:8080/swagger-ui/index.html
```

and dev environment in the public address:

```
http://java-spring-interview-project-rangel-so.us-east-1.elasticbeanstalk.com/swagger-ui/index.html
```

An Json example for the save a member

```
POST
http://java-spring-interview-project-rangel-so.us-east-1.elasticbeanstalk.com/api/v1/member
```


```javascript
{
    "idMember": 0,
    "name": "string",
    "salary": 4.50,
    "type": "EMPLOYEE",
    "contractDuration": 2,
    "role": "SOFTWARE_ENGINEER",
    "tags": [
    "string"
    ],
    "country": "brasil"
}
```



### TODO
- An exception handler to better define a standard error model.
- Authentication and permission of APIs using jwt and spring security
- search takes tags already registered indexing in a slatic search
