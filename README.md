# Library management system

This project uses Spring boot.

## Requirements
- JDK 17
- Maven 4 or higher

## Packaging the application 

Steps to running the application

- Clone this repository
- Run the below command to download the necessary dependencies ,run  tests and  build the jar file

```shell script
  ./mvnw clean install
```


## Running the application

The application can be run using:

```shell script
java -jar path/to/your/jar-file.jar

```

Alternatively you can run the application directly after cloning the repo by running th command

```shell script
./mvnw spring-boot:run
```

## Dependencies
Dependencies required to run application
Certainly, here are the dependency names with dashes instead of numbers and without quotation marks:

- spring-boot-starter-jdbc
- spring-boot-starter-validation
- spring-boot-starter-web
- h2
- lombok
- spring-boot-starter-test
- spring-boot-starter-data-jpa
- spring-boot-starter-security
- spring-security-test

## Api endpoints
### Patron
> **_NOTE:_**  Patron is the User so to authenticate your request you need to create a patron account.
 
  ### Request
Add a patron
```

POST http://localhost:8080/api/patron 
Content-Type: application/json

{
"name": "John Doe",
"phoneNumber": "123-456-7890",
"email": "johndoe@example.com",
"password":"1234",
"role":"ADMIN"
}
```

### Response
```
HTTP/1.1 201 Created
Status: 201 Created
Connection: close
Content-Type: application/json
Location: /thing/1
Content-Length: 36


{
"id": "f333d795-a177-4907-99ff-f3c16ba4e6bd",
"name": "John Doe",
"phoneNumber": "123-456-7890",
"email": "johndoe@examplse.com",
"password": "$2a$10$42qPClyuN.jCkiaACnO1euTqX9hndm4/GStIi6eTuyPH65bfU1coO",
"books": null,
"createdAt": "2024-05-12T15:09:42.453962",
"updatedAt": "2024-05-12T15:09:42.454001",
"role": "ADMIN",
"enabled": true,
"username": "johndoe@example.com"
}
```
> **_NOTE:_**  if patron already exists you get a response of 409
```
HTTP/1.1 409
Status: 409 Conflict
Connection: close
Content-Type: application/json
```

### Request
Update a patron
```
PUT http://localhost:8080/api/patron/{id}
Content-Type: application/json
Authorization: Basic am9obmRvZUBleGFtcGxlLmNvbToxMjM0

{
"name": "John Doe",
"phoneNumber": "123-456-7890",
"email": "johndoe@example.com",
"password":"1234",
"role":"ADMIN"
}
```

### Response
```
HTTP/1.1 200 Success
Date: Thu, 24 Feb 2011 12:36:30 GMT
Status: 200 Success
Connection: close
Content-Type: application/json
Location: /thing/1


{
"id": "f333d795-a177-4907-99ff-f3c16ba4e6bd",
"name": "John Doe",
"phoneNumber": "123-456-7890",
"email": "johndoe@examplse.com",
"password": "$2a$10$42qPClyuN.jCkiaACnO1euTqX9hndm4/GStIi6eTuyPH65bfU1coO",
"books": null,
"createdAt": "2024-05-12T15:09:42.453962",
"updatedAt": "2024-05-12T15:09:42.454001",
"role": "ADMIN",
"enabled": true,
"username": "johndoe@example.com",

}
```
### Request
> **_NOTE:_**  if you add an id to the request you get that specific record else all records will be retrieved
Get all patrons or specific patron
```
GET http://localhost:8080/api/patron/{id} , http://localhost:8080/api/patron

Content-Type: application/json
Authorization: Basic am9obmRvZUBleGFtcGxlLmNvbToxMjM0

```
### Response
```
HTTP/1.1 200 Success
Status: 200 Success
Connection: close
Content-Type: application/json
Location: /thing/1
{
"id": "f333d795-a177-4907-99ff-f3c16ba4e6bd",
"name": "John Doe",
"phoneNumber": "123-456-7890",
"email": "johndoe@examplse.com",
"password": "$2a$10$42qPClyuN.jCkiaACnO1euTqX9hndm4/GStIi6eTuyPH65bfU1coO",
"books": null,
"createdAt": "2024-05-12T15:09:42.453962",
"updatedAt": "2024-05-12T15:09:42.454001",
"role": "ADMIN",
"enabled": true,
"username": "johndoe@example.com",

}
or

[
  {
  "id": "f333d795-a177-4907-99ff-f3c16ba4e6bd",
  "name": "John Doe",
  "phoneNumber": "123-456-7890",
  "email": "johndoe@examplse.com",
  "password": "$2a$10$42qPClyuN.jCkiaACnO1euTqX9hndm4/GStIi6eTuyPH65bfU1coO",
  "books": null,
  "createdAt": "2024-05-12T15:09:42.453962",
  "updatedAt": "2024-05-12T15:09:42.454001",
  "role": "ADMIN",
  "enabled": true,
  "username": "johndoe@example.com",
  
  }
]
```
### Request
Delete patron
```
DELETE http://localhost:8080/api/patron/{id} 

Content-Type: application/json
Authorization: Basic am9obmRvZUBleGFtcGxlLmNvbToxMjM0

```
### Response
```
patron deleted successfully

```

## Book

### Request
Add a book
```
POST http://localhost:8080/api/book
Content-Type: application/json
Authorization: Basic am9obmRvZUBleGFtcGxlLmNvbToxMjM0

{
  "title": "Example Book",
  "author": "John Doe",
  "publicationYear": "2022",
  "isbn": "978-3-16-148410-0"
}

```

### Response
```
HTTP/1.1 201 Created
Status: 200 Success
Content-Type: application/json


{
    "id": "803778cd-5a1c-49a7-90ac-be4a57a0041a",
    "title": "Example Book",
    "author": "John Doe",
    "publicationYear": "2022",
    "patrons": null,
    "createdAt": "2024-05-12T15:21:49.879817",
    "updatedAt": "2024-05-12T15:21:49.879876",
    "isbn": "978-3-16-148410-0"
}
```

### Request
Update a book
```
PUT http://localhost:8080/api/book/{id}
Content-Type: application/json
Authorization: Basic am9obmRvZUBleGFtcGxlLmNvbToxMjM0

{
  "title": "Example Book",
  "author": "John Doe",
  "publicationYear": "2022",
  "isbn": "978-3-16-148410-0"
}

```

### Response
```
HTTP/1.1 200 Success
Status: 200 Success
Content-Type: application/json


{
    "id": "803778cd-5a1c-49a7-90ac-be4a57a0041a",
    "title": "Example Book",
    "author": "John Doe",
    "publicationYear": "2022",
    "patrons": null,
    "createdAt": "2024-05-12T15:21:49.879817",
    "updatedAt": "2024-05-12T15:21:49.879876",
    "isbn": "978-3-16-148410-0"
}
```

### Request
Get a book or all books
```
GET http://localhost:8080/api/book/{id} , http://localhost:8080/api/book
Content-Type: application/json
Authorization: Basic am9obmRvZUBleGFtcGxlLmNvbToxMjM0

{
  "title": "Example Book",
  "author": "John Doe",
  "publicationYear": "2022",
  "isbn": "978-3-16-148410-0"
}

```

### Response
```
HTTP/1.1 200 Success
Status: 200 Success
Content-Type: application/json


{
    "id": "803778cd-5a1c-49a7-90ac-be4a57a0041a",
    "title": "Example Book",
    "author": "John Doe",
    "publicationYear": "2022",
    "patrons": null,
    "createdAt": "2024-05-12T15:21:49.879817",
    "updatedAt": "2024-05-12T15:21:49.879876",
    "isbn": "978-3-16-148410-0"
}

or

[
  {
      "id": "803778cd-5a1c-49a7-90ac-be4a57a0041a",
      "title": "Example Book",
      "author": "John Doe",
      "publicationYear": "2022",
      "patrons": null,
      "createdAt": "2024-05-12T15:21:49.879817",
      "updatedAt": "2024-05-12T15:21:49.879876",
      "isbn": "978-3-16-148410-0"
  }
]
```
### Request
Delete a book 
```
DELETE http://localhost:8080/api/book/{id} 
Authorization: Basic am9obmRvZUBleGFtcGxlLmNvbToxMjM0

```

### Response
```
HTTP/1.1 200 Success
Status: 200 Success
Content-Type: text/plain

Book deleted
```

### Borrow book

## Request
```
POST http://localhost:8080/api/borrow/{bookId}/patron/{patronId}
Authorization: Basic am9obmRvZUBleGFtcGxlLmNvbToxMjM0

```

## Response

```
HTTP/1.1 200 Success
Status: 200 Success
Content-Type: text/plain

Book borrowed successfully
```
### Return a Book

## Request
```
PUT http://localhost:8080/api/borrow/{bookId}/patron/{patronId}
Authorization: Basic am9obmRvZUBleGFtcGxlLmNvbToxMjM0

```
## Response
```
HTTP/1.1 200 Success
Status: 200 Success
Content-Type: text/plain

Book returned successfully
```







