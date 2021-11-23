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
- **201 - Created**  
Student is successfully added to database. Returns the request body with the student id and the HTTP Response Header returns the url location of the newly added student, in this case: 	http://localhost:8080/student-management-system/api/v1/students/1
- **409 - Conflict**  
When student posted already exists in database.

### GET http://localhost:8080/student-management-system/api/v1/students/  
Gets all students from the database.

#### Responses
- **200 - OK**  
Returns all students in database or message that there are no students in database yet.

### GET http://localhost:8080/student-management-system/api/v1/students/{id}
Gets student by student id by adding the id in the url.  
e.g. `http://localhost:8080/student-management-system/api/v1/students/1`

#### Responses
- **200 - OK**  
Returns student information that match requested student id.
- **404 - Not found**  
Student with requested id does not exist in database.

### GET http://localhost:8080/student-management-system/api/v1/students/search
Gets all students by last name by adding query parameter lastName to the request.  
e.g. `http://localhost:8080/student-management-system/api/v1/students/search?lastName=Beckstead`
#### Responses
- **200 - OK**  
Returns a list of students that match the requested last name.
- **404 - Not found**  
No student with the requested last name exists in database. 

### PUT http://localhost:8080/student-management-system/api/v1/students/
Replaces student with the information provided in the request body.
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
- **200 - OK**  
Newly provided student information is merged in database and request body is returned in response body.
- **404 - Not found**  
Student id sent in request body is not found in database.

### PATCH http://localhost:8080/student-management-system/api/v1/students/{id}
Patches the students' email by entering the students' id in the url and entering the new email address as a query parameter in the url.
e.g. `http://localhost:8080/student-management-system/api/v1/students/1?email=jabarriedson%40outlook.com`

#### Responses
- **200 - OK**  
New email address is updated in the database and the student information is returned in the response body.
- **404 - Not found**  
Student with requested id does not exist in database.

### DELETE http://localhost:8080/student-management-system/api/v1/students/{id}
Deletes the student with the given id in the url.  
e.g. `http://localhost:8080/student-management-system/api/v1/students/1`

#### Responses
- **204 - No Content**  
Students is succesfully deleted from database.
- **404 - Not found**  
  Student with requested id does not exist in database.