


# Nursery Plant Application  
In this Application Admin can register a customer , planter ,Plant and seeds and Admin can delete all of these. A customer can register himself/herself and login and after login customer can see all present Planter, Plant and seeds and if customer want to purchase anything then customer can place a order as well.
# Objevtive:
The objective to create this project was to implement all things which we have learned , how to contribute in a collaborative project as a team player and face some challenges which can be occur in real time project.We made a Backend Application to perform CRUD operation and made RestFul APIs Service Using java, SpringBoot, lombok and swagger-UI for Nursery Plant Application.

# E-R Diagram for the application:




![ERDiagram](https://github.com/nitinpal0211/mucho-interest-4403/blob/main/Plant_Nursery_Application/Er_And_Images/erDiagram.png?raw=true)


# Modules:

![](https://github.com/nitinpal0211/mucho-interest-4403/blob/main/Plant_Nursery_Application/Er_And_Images/Class%20Module.jpeg)

## Tech Stacks:

- Java
- Spring Boot
- Maven
- Swagger-Ui
- Lombok
- MySQL
- SpringData Jpa
- Hibernate


## Features And Functinalities:

- Admin/Customer registration and Login.
- Admin can Perform The All Crud operation on Plants,Planters,Seeds and customers or Manage them.
- Customer can See or Buy Seeds, Plants and Planters.
- Customer can place a order.


##  Backend of The Application 

- For Data Security We Build SignUp - Login For customer and Admin.  
- Stored the data In MySQL and that Can be access By only Authenticated User.
- Proper Exception Handling.
- Proper Input Validation. 

## Installation and Run 

You can clone this repo and start the serve on localhost.
Before running the API server, we should update the database config inside the application.properties file.
Update the port number, username and password as per your local database config.


  ```
   server.port=8888 
   
   spring.datasource.url=jdbc:mysql://localhost:3306/sb201db
   
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   
   spring.datasource.username=**mysql username**
   
   spring.datasource.password=**YourPassword**
   
   spring.jpa.hibernate.ddl-auto=update 
   
   spring.jpa.show-sql=true
   
   spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER
   ```
   
   


## API Root Endpoint 

Link: http://localhost:8888/swagger-ui/

## API Module Endpoints

### Admin Login-Logout
- `POST  /Admin/login` Logging in as a Admin with valid Email and Pass.
- `GET   /Admin/logout/{key}` Logout as a Admin with valid Key or Session token.

### admin-customer-controller
- `POST /Admin/customers/{key}`       To Add a Customer with valid Session token 
- `GET  /Admin/customers/{key}`       To View All Customers with valid Session token
- `GET  /Admin/customers/{customerId}/{key}/`                       To view Customer With customerId with valid sesion token
- `GET  /Admin/customers/{customerEmail}/{customerPassword}/{key}`  To validate Customer with Proper Email and pass of customer
- `PUT     /Admin/customers/{key}`             To Update Customer With Proper session token
- `DELETE  /Admin/customers/{email}/{key}`  To delete customer With proper Session Token  

## Presenatation Link:

Link: 

## Roles & Responsibilities :-

All Work of Admin and Customer Management and LogIn Auth done by [Shimbhu Kumawat](https://github.com/Shimbhu77)

All Work of Plant management Done By [Nitin Kumar](https://github.com/nitinpal0211) 

All Work of Planter and Er Dia Done By [Shreyash Ahuja](https://github.com/loki025)

All Work of Seed management Done by [Akash Kumar](https://github.com/akash07032001)

All work of order Management Done By [Chetan Patil](https://github.com/Chetan8788)

## Learning :-

We all became proficient in reading the code of team members.

We got the better Idea and perspective of Different Layers Like: Presentation Layer, Service Layer and Data Access layer.

We get good idea about Team management and team Collaboration. 

our skills in Java And Spring boot became more better.

Our code writing style became cleaner as we had to think while writing code that other team members will be reading. 


## Team - Members:

👤 [Nitin Kumar - Team Leader](https://github.com/nitinpal0211)

👤 [Shimbhu Kumawat](https://github.com/Shimbhu77)

👤 [Shreyash Ahuja](https://github.com/loki025)

👤 [Chetan Patil](https://github.com/Chetan8788)

👤 [Akash Kumar](https://github.com/akash07032001)

