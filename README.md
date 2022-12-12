## Instructions

The goal of this exercise is to create a backend using Java with the Spring Boot framework.

### The Task

In this task, we are building backend of an application that helps us managing our team.

### Features and Requirements

We need to offer a REST CRUD with the following features:

- A member has:
- - A Name
- - A Salary per year
- - type: it can be either an employee or a contractor.
- - -  If it's a contractor, we want to store the duration of the contract as an integer.
- - - If it's an employee, we need to store their role, for instance: Software Engineer, Project Manager and so on.
- A member can be tagged, for instance: C#, Angular, General Frontend, Seasoned Leader and so on. (Tags will likely be used as filters later, so keep that in mind)
- A member lives in a Country. When we receive the request to create the member we should receive the "country" attribute, from it we should fetch the currency of the country that you should get from https://restcountries.com/, see the following example: https://restcountries.com/v3.1/name/brasil, where "brasil" is the name of the country. we need to store the currency together with the country information so our HR team knows which currency to pay the member.


### Notes:

1. You need to use the latest version of Spring Boot.
2. Make sure to provide a tutorial on how to run your application.
3. You need to use the Postgres database.
4. The structure of your data is completely up to you. 

## Evaluation
| Functionality     |                                                                | Possible Points |
|-------------------|----------------------------------------------------------------|-----------------|
|                   | Matches the proposed requirements                              |              20 |
|                   | Implements REST correctly                                      |              10 |
|                   | Separation of business logic and persistence layers            |              15 |
|                   | Input validations                                              |               5 |
|                   | Live documentation (Swagger, or other options)                 |              10 |
| **Code Quality**  |                                                                |                 |
|                   | Code formatting, readability, maintainability, etc             |              10 |
|                   | Folders and files structure                                    |               5 |
| **DevOps**        |                                                                |                 |
|                   | Docker image to build/run the project                          |              10 |
| **Documentation** |                                                                |                 |
|                   | Documentation about the work done, how to run the project, etc |               5 |
| **Testing**       |                                                                |                 |
|                   | Has tests for the main flows                                   |              10 |
| **Total**         |                                                                |             100 |


### Bonus Points:
1. If you deploy the application in any server and share the link with us
2. If provide thoughts on what you could improve on your code given more time and incentives

## F.A.Q.

### Is it necessary build a frontend?
No, this is a simply backend exercise.

### How do you evaluate the exercise?
For every exercise we have two senior backend engineers from our team reviewing the code and the functionality and giving a score for each line item as shown in the previous table.

### How can I deliver the exercise?
To deliver the exercise, you should clone this repository and work on a new branch. When you'll consider it completed, just push the branch and open a Merge Request.

### Will I have access to the evaluation?
By default we only send the result, however you can feel free to request the full evaluation and we will share it with you as well as the final score.
