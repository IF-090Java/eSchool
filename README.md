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

## Start application locally
### Software
Software required to run this application:
1. Java
2. MySQL Server
3. Git

#### Installing Java
Download installer appropriate for your operating system from [www.oracle.com](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html) and run it. Complete installation process by following instructions.

#### Installing MySQL Server
To install MySQL Server visit https://www.mysql.com/downloads/ and follow installation instructions for your operating system.

#### Installing Git
To instal Git visit https://git-scm.com/downloads and follow installation instructions for your operating system.

### Configuration
The application can be configured using environmental variables.
#### Database Configuration:
* DATASOURCE_URL - connection string to database to be used by application (by default it will connect to jdbc:mysql://localhost:3306/eschool?useUnicode=true&characterEncoding=utf8&createDatabaseIfNotExist=true&&autoReconnect=true&useSSL=false)
* DATASOURCE_USERNAME - username used to access database (default: root)
* DATASOURCE_PASSWORD - password used to access database (default: root)
#### Email configuration:
These variables are used by the application to send password recovery emails
* MAIL_HOST - mail server host (default: smtp.gmail.com)
* MAIL_PORT - mail server port (default: 587}
* MAIL_LOGIN - username for the account at the mail host
* MAIL_PASSWORD - password for the account (in case of Gmail it must be application password)
* ESCHOOL_APP_HOST - address where application is hosted
#### Security Configuration
* PASSWORD_ENCODING_KEY - secret text that is used to encrypt and decrypt passwords
* JWT_EXPIRATION - lifetime of jwt token in seconds (default value is one hour)
* ADMIN_USERNAME - initial administrator login login
* ADMIN_PASSWORD - initial administrator password. It should be encrypted with PASSWORD_ENCODING_KEY. To get encrypted password you can use one of this methods:
     * [this service](https://www.devglan.com/online-tools/jasypt-online-encryption-decryption) - 
 choose "Two Way Encryption(With Secret Text)" option provide raw password and encoding key and press encrypt
    * if you have jasypt in your maven repository you can use following command:
    ```
    java -cp ~/.m2/repository/org/jasypt/jasypt/1.9.2/jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="YOUR_DECRYPTED_PASSWORD" password=YOUR_ENCODIG_KEY algorithm=PBEWithMD5AndDES
    ```


### Run application
1. Clone the repository:
```
git clone https://github.com/IF-090Java/eSchool.git
``` 
2. To build and run application execute:

Windows:
```
./mvnw.cmd spring-boot:run
```
Linux:
```
./mvnw spring-boot:run
```
3. To access application home page open [localhost:8080](http://localhost:8080/)
