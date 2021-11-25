# Student Management System

API application for performing CRUD operations on H2 in-memory database using Payara Server.

## Setup
Deploy the application on localhost:8080. Then run the requests in the provided `setup.http` file to fill the database with students, subjects and teachers. 
_Note: The objects are not yet joined or coupled. See Subject Endpoints on how to add students and/or teachers to a subject._ 

## Student Endpoints

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

## Teacher Endpoints

### POST http://localhost:8080/student-management-system/api/v1/teachers
Creates a new teacher and adds it to the database.
#### Request body
```json
{
  "firstName": "Mary",
  "lastName": "Leonard"
}
```
#### Responses
- **201 - Created**  
  Teacher is successfully added to database. Returns the request body with the teacher id and the HTTP Response Header returns the url location of the newly added teacher.

### GET http://localhost:8080/student-management-system/api/v1/teachers
Gets all teachers from the database.
#### Responses
- **200 - OK**  
  Returns all teachers in database or message that there are no teachers in database yet.

### GET http://localhost:8080/student-management-system/api/v1/teachers/{id}
Gets teacher by teacher id by adding the id in the url.  
e.g. `http://localhost:8080/student-management-system/api/v1/teachers/7`

#### Responses
- **200 - OK**  
  Returns teacher information that match requested teacher id.
- **404 - Not found**  
  Teacher with requested id does not exist in database.


## Subject Endpoints

### POST http://localhost:8080/student-management-system/api/v1/subjects
Creates a new subject and adds it to the database.
#### Request body
```json
{
  "name": "Geography"
}
```
#### Responses
- **201 - Created**  
  Subject is successfully added to database. Returns the request body with the subject id and the HTTP Response Header returns the url location of the newly added subject.

### GET http://localhost:8080/student-management-system/api/v1/subjects
Gets all subjects from the database.
#### Responses
- **200 - OK**  
  Returns all subjects in database or message that there are no subjects in database yet.

### GET http://localhost:8080/student-management-system/api/v1/subjects/{id}
Gets subject by subject id by adding the id in the url.  
e.g. `http://localhost:8080/student-management-system/api/v1/subjects/9`

#### Responses
- **200 - OK**  
  Returns subject information that match requested subject id.
- **404 - Not found**  
  Teacher with requested id does not exist in database.

### POST http://localhost:8080/student-management-system/api/v1/subjects/{subjectId}/students/{studentId}
Adds a student to a subject by entering the subject id, /students/ followed by the student id.
e.g. `http://localhost:8080/student-management-system/api/v1/subjects/10/students/1`

#### Responses
- **200 - OK**  
  Returns the updated subject information including the newly added student.
- **404 - Not found**  
  Subject or student with requested id does not exist in database.


### POST http://localhost:8080/student-management-system/api/v1/subjects/{subjectId}/teachers/{teacherId}
Adds a teacher to a subject by entering the subject id, /teachers/ followed by the teacher id.
e.g. `http://localhost:8080/student-management-system/api/v1/subjects/8/teachers/6`

#### Responses
- **200 - OK**  
  Returns the updated subject information including the newly added teacher.
- **404 - Not found**  
  Subject or teacher with requested id does not exist in database.