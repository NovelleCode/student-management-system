# Student Management System

API application for performing CRUD operations on H2 in-memory database using Payara Server.

## Endpoints

### POST http://localhost:8080/student-management-system/api/v1/students/  
Creates a new student and adds it to the database.

#### Request Body
```json
{
  "firstName": "Shaina", 
  "lastName": "Beckstead", 
  "email": "shainabeckstead@mail.com", 
  "phoneNumber": "0701234567"
}
```  
Phone number is optional.

#### Responses
- 201 - Created
```json
{
  "email": "shainabeckstead@mail.com",
  "firstName": "Shaina",
  "id": 1,
  "lastName": "Beckstead",
  "phoneNumber": "0701234567"
}
```
In the HTTP Response Header you can also find the url location of the newly added student, in this case: 	http://localhost:8080/student-management-system/api/v1/students/1 

- 409 - Conflict
```json
{
  "httpStatusCode": 409,
  "message": "Student with this information already exists in database."
}
```

###GET http://localhost:8080/student-management-system/api/v1/students/  
Gets all students from the database.

#### Responses
- 200 - OK
```json
{
  "httpStatusCode": 200,
  "message": "No students seem to be registered yet."
}
```
or
```json
[
  {
    "email": "shainabeckstead@mail.com",
    "firstName": "Shaina",
    "id": 1,
    "lastName": "Beckstead",
    "phoneNumber": "0701234567"
  },
  {
    "email": "jabarriedson@mail.com",
    "firstName": "Jabarri",
    "id": 2,
    "lastName": "Edson",
    "phoneNumber": "0707654321"
  }
]
```

### GET http://localhost:8080/student-management-system/api/v1/students/{id}
Gets student by student id by add the id value in the url.  
e.g. `http://localhost:8080/student-management-system/api/v1/students/1`

#### Responses
- 200 - OK
```json
{
  "email": "shainabeckstead@mail.com",
  "firstName": "Shaina",
  "id": 1,
  "lastName": "Beckstead",
  "phoneNumber": "0701234567"
}
```
- 404 - Not found
```json
{
  "httpStatusCode": 404,
  "message": "No student found with id: 3"
}
```

### GET http://localhost:8080/student-management-system/api/v1/students/get-all-by-lastname
Gets all students by last name by adding query parameter lastName to the request.  
e.g. `http://localhost:8080/student-management-system/api/v1/students/get-all-by-lastname?lastName=Beckstead`
#### Responses
- 200 - OK
```json
[
  {
    "email": "shainabeckstead@mail.com",
    "firstName": "Shaina",
    "id": 1,
    "lastName": "Beckstead",
    "phoneNumber": "0701234567"
  }
]
```
- 404 - Not found
```json
{
  "httpStatusCode": 200,
  "message": "No student(s) found with lastname: lastname"
}
```

### PUT http://localhost:8080/student-management-system/api/v1/students/
Replaces student with the information provided in the request body. Returns request body in response body when status code is 200 OK.
#### Request body
```json
{
  "email": "shainacurrie@mail.com",
  "firstName": "Shaina",
  "id": 1,
  "lastName": "Currie",
  "phoneNumber": "0701234567"
}
```
#### Responses
- 200 - OK
```json
{
  "email": "shainacurrie@mail.com",
  "firstName": "Shaina",
  "id": 1,
  "lastName": "Currie",
  "phoneNumber": "0701234567"
}
```
- 404 - Not found
```json
{
  "httpStatusCode": 404,
  "message": "No student found with id: 3"
}
```

###PATCH http://localhost:8080/student-management-system/api/v1/students/email/{id}
Patches the student email by entering the students' id in the url and entering the new email as a query parameter in the url. 
Returns the updated information in the response body when status code is 200 OK.
e.g. `http://localhost:8080/student-management-system/api/v1/students/email/2?email=jabarriedson%40outlook.com`

#### Responses
- 200 - OK
```json
{
  "email": "jabarriedson@outlook.com",
  "firstName": "Jabarri",
  "id": 2,
  "lastName": "Edson",
  "phoneNumber": "0707654321"
}
```
- 404 - Not found
```json
{
  "httpStatusCode": 404,
  "message": "No student found with id: 3"
}
```

### DELETE http://localhost:8080/student-management-system/api/v1/students/{id}
Deletes the student with the given id in the url.  
e.g. `http://localhost:8080/student-management-system/api/v1/students/2`

#### Responses
- 204 - No Content
- 404 - Not found
```json
{
  "httpStatusCode": 404,
  "message": "No student found with id: 3"
}
```