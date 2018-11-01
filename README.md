# eSchool

**eSchool** is a school management web application based on Spring Boot and developed as graduation project at SoftServe IT Academy.

## Core Features
#### Administrator functionality:
* Add students and teachers to database
* Create classes and add students into them
* Add subjects studied at school
* Assign teachers and subjects to classes 

#### Teachers functionality
* View and edit mark book
* Assign home tasks
* Add notes for student
* View school performance chart

#### Students functionality: 
* View their school diary with schedule
* View received marks and notes form teacher
* View assigned home tasks

## Start application locally with Docker
1. Clone the repository:
```
git clone https://github.com/IF-090Java/eSchool.git
``` 
2. Install latest version of the Docker
3. Make sure files from 'script' directory are executable 
4. To start application locally execute the following from the root directory of the project:
```
./scripts/start.sh
```  
5. To stop and clear application artifacts execute the following from the root directory of the project:
```
./scripts/stop.sh
```
6. To access application home page open [localhost:8080](http://localhost:8080/)
