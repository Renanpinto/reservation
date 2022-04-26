# Booking reservation API

###### Powered By Renan Pinto

<p align="center">
 <a href="#-about">About</a> •
 <a href="#-funcionalities">Funcionalities</a> •
 <a href="#-how-to-run-the-project">How to run the project</a> •
 <a href="#-technologies">Technologies</a> 
</p>

## 💻 About

Project made for booking hotel reservation

## ⚙️ Funcionalities

- [x] Booking reservation Crud
- [x] Get unavailable dates to book

## 🚀 How to run the project

### Prerequisites

Before running the project you'll need:

- [Git](https://git-scm.com)
- [Java 13](https://openjdk.java.net/projects/jdk/13/)
- [Maven](https://maven.apache.org/install.html)

#### 🎲 Running the project

```bash
# Clone this repo
$ git clone https://github.com/Renanpinto/reservation.git

# Change you java version to use java 13
$ sudo update-alternatives --config java (Linux)

# Install the dependencies
$ mvn clean install

# Run the application
$ Go to HotelReservationApplication.java and clic on Run if your using an IDE
# Or 
$ cd target/
$ java -jar reservation-0.0.1-SNAPSHOT.jar

# Server will start on port:8080
```

Once the server is up you can access our swagger into http://localhost:8080/swagger-ui.html#/

#### 🎲 Tests

- To testing, I'm using [Spock](https://spockframework.org/spock/docs/2.0/index.html) with groovy
  language to be easier do testing in BDD format

Test coverage:

- I'm using Jacoco to check our coverage into usecases package, coverage is set as 100% line
  coverage.

```bash
# Jacoco Verify
$ mvn clean verify

# Unit tests
$ mvn test
``` 

#### 🎲 Database

- In order to be easier to run the project locally I'm
  using [H2Database](https://www.h2database.com/html/main.html), which is an in memory database
- When the server is up the migration into
  file [resources/data.sql](https://github.com/Renanpinto/reservation/blob/main/src/main/resources/data.sql)
  will run
- You can access the database through url http://localhost:8080/h2-console/ with username=sa and
  empty password

## 🛠 Technologies

The following tools were used to build the project:

- **[Java 13](https://openjdk.java.net/projects/jdk/13/)**
- **[H2Database](https://www.h2database.com/html/main.html)**
- **[Spock](https://spockframework.org/spock/docs/2.0/index.html)**
- **[Swagger](https://swagger.io/docs/specification/about/)**

## 🛠 Documentation
### Swagger
![Captura de tela de 2022-04-26 18-14-11](https://user-images.githubusercontent.com/31354349/165393625-153c334a-8258-42c4-8a3d-fbb8b3ac02b6.png)


### H2 database
![Captura de tela de 2022-04-26 18-13-54](https://user-images.githubusercontent.com/31354349/165393679-a1a7dee9-9c4c-495a-9fd4-e892bfe09878.png)



