# AlchemyTec
AlchemyTec - Back End Coding Challenge

## Installation

### 1. Create MySQL or MariaDB database "alchemytest"
Go to project directory backend-coding-challenge/ and run command  

    mysql -u root -p < ./sql/01-create-db.sql       

Update db host in backend-coding-challenge/src/main/resources/application.properties if you don't use localhost

### 2. Build Maven project
Go to project directory backend-coding-challenge/ and run command   

    mvn clean package

Web application saved to backend-coding-challenge/target/alchemytec.war

### 3. Start Jetty and run webapp on http://localhost:8888/alchemytec/ 
Go to project directory backend-coding-challenge/ and run command   

    mvn jetty:run

Web application can be deployed to container like Jetty 9 or WildFly 9.
Container should supports Servlet 3.0 API and Java 8.

## JSON API
API WADL <http://localhost:8888/alchemytec/v2/api-docs>     
API UI <http://localhost:8888/alchemytec/swagger-ui.html>     

### Get all expenses

    curl http://localhost:8888/alchemytec/expenses
    
### Get expense by ID

    curl http://localhost:8888/alchemytec/expenses/2
    
### Create expense

    curl -X POST --header "Content-Type: application/json" --header "Accept: application/json" -d "{
      \"amount\": 100,
      \"date\": 1446415200000,
      \"reason\": \"Reason\",
      \"vat\": 20
    }" "http://localhost:8888/alchemytec/expenses"
    
### Update expense

    curl -X PUT --header "Content-Type: application/json" --header "Accept: application/json" -d "{
      \"id\": 11,
      \"amount\": 100,
      \"date\": 1446415200000,
      \"reason\": \"New Reason\",
      \"vat\": 20
    }" "http://localhost:8888/alchemytec/expenses/11"
    
### Delete expense

    curl -X DELETE --header "Accept: application/json" "http://localhost:8888/alchemytec/expenses/11"



Goal
====
Produce a simple web-app backend to complement the supplied front-end code.

Mandatory Work
--------------
Fork this repository. Starting with the provided HTML, CSS, and JS, create a Java-based REST API that:

1. Saves expenses as entered to a database.
2. Retrieves them for display on the page. 
3. Add a new column to the table displaying the VAT amount for each expense.
4. Alter the README to contain instructions on how to build and run your app.

VAT is the UK’s sales tax. It is 20% of the value of the expense, and is included in the amount entered by the user.

Give our account `alchemytec` access to your fork, and send us an email when you’re done. Feel free to ask questions if anything is unclear, confusing, or just plain missing.

Extra Credit
------------
Calculate the VAT client-side as the user enters a new expense, before they save the expense to the database.

Questions
---------
##### What frameworks can I use?
That’s entirely up to you, as long as they’re OSS. We’ll ask you to explain the choices you’ve made. Please pick something you're familiar with, as you'll need to be able to discuss it.

##### What application servers can I use?
Anyone you like, as long as it’s available OSS. You’ll have to justify your decision. We use dropwizard and Tomcat internally. Please pick something you're familiar with, as you'll need to be able to discuss it.

##### What database should I use?
MySQL or PostgreSQL. We use MySQL in-house.

##### What will you be grading me on?
Elegance, robustness, understanding of the technologies you use, tests, security. 

##### Will I have a chance to explain my choices?
Feel free to comment your code, or put explanations in a pull request within the repo. If we proceed to a phone interview, we’ll be asking questions about why you made the choices you made. 

##### Why doesn’t the test include X?
Good question. Feel free to tell us how to make the test better. Or, you know, fork it and improve it!
