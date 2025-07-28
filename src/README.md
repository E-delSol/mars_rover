# 🚀 Mars Rover - Technical Challenge

Del Sol Mars Rover is a REST API built with Java and Spring Boot that lets you manage a simulated Mars environment. You can create maps, obstacles, and rovers, and send commands to control their movement.

---

## 📚 Description

This API allows you to:

- Create and delete **maps**, **obstacles**, and **rovers**
- View all maps, obstacles, and rovers, or search by ID
- Control a rover's movement on a map (including obstacle detection and edge wrapping)

---
## 🛠 Technologies Used

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **MySQL** (relational database)
- **JUnit 5** and **Mockito** (for testing)
- **Lombok**
- **Maven**

---
## 📦 Installation

### 1. Prerequisites

Make sure you have installed:

- Java 17 or higher
- Maven
- MySQL
- An IDE like IntelliJ IDEA (recommended)

---
### 2. Clone the Repository

```bash
git clone https://github.com/E-delSol/mars_rover.git
cd del-sol-mars-rover
```

### 3. Configure the Database

#### Step 1: Create the MySQL Database

Connect to your MySQL server and run this command:

```sql
CREATE DATABASE mars_rover_db;
```

#### Step 2: Set Database Credentials

Open the file `src/main/resources/application.properties` and update:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mars_rover_db
spring.datasource.username=your_user // default "root"
spring.datasource.password=your_password // default ""
```

Replace `your_user` and `your_password` with your real MySQL credentials.

#### Step 3: Check Connection

Run the app. If the configuration is correct, Spring Boot will connect to the database and create all tables.

If there's an error, check:

- MySQL is running
- Credentials are correct
- Port 3306 is open

---

### 4. Build the Project

```bash
mvn clean install
```

---

### 5. Run the App

```bash
mvn spring-boot:run
```

The API will be available at:  
📍 `http://localhost:8080`

---

## ✅ Tests

To run the tests and check coverage:

```bash
mvn test
```

This project includes 68 tests that cover:

- Valid and invalid service cases
- Business logic (movement, direction, obstacles)
- REST controllers

---

## 🧭 API Endpoints

### 🗺️ Maps

|Method|Endpoint|Description|
|---|---|---|
|GET|`/maps`|Get all maps|
|GET|`/maps/{id}`|Get map by ID|
|POST|`/maps`|Create a map|
|DELETE|`/maps/{id}`|Delete a map|

## 🤖 Rovers

|Method|Endpoint|Description|
|---|---|---|
|GET|`/rovers`|Get all rovers|
|GET|`/rovers/{id}`|Get a rover by ID|
|POST|`/rovers`|Create a new rover|
|DELETE|`/rovers/{id}`|Delete a rover|
|POST|`/rovers/{id}/commands`|Send commands to a rover|

## 🚧 Obstacles

|Method|Endpoint|Description|
|---|---|---|
|GET|`/obstacles/{mapId}`|Get obstacle by mapId|
|POST|`/obstacles`|Create a new obstacle|
|DELETE|`/obstacles/{id}`|Delete an obstacle|

---

## 🗺️ Rover Movement & Commands

Available commands for a rover:

- `f`: move forward
- `b`: move backward
- `l`: turn left
- `r`: turn right

The rover moves using cardinal directions (`N`, `E`, `S`, `W`).  
Map edges are wrapping (like a torus).

---

## 🧱 Project Structure

```bash
src ├── main
│   ├── java
│   │   └── com.pecado.del_sol_mars_rover
│   │       ├── controller
│   │       ├── service
│   │       ├── model
│   │       ├── dto
│   │       └── repository
│   └── resources
│       └── application.properties
└── test
	└── java
		 └── com.pecado.del_sol_mars_rover
			  └── (unit tests)
```

## 🧱 Architecture Diagram

```mermaid
flowchart TD
    subgraph Presentation Layer
        A1[MapController]
        A2[RoverController]
        A3[ObstacleController]
    end

    subgraph Service Layer
        B1[MapServiceImpl]
        B2[RoverServiceImpl]
        B3[ObstacleServiceImpl]
    end

    subgraph Repository Layer
        C1[MapRepository]
        C2[RoverRepository]
        C3[ObstacleRepository]
    end

    subgraph Domain Layer
        D1[Map]
        D2[Rover]
        D3[Obstacle]
        D4[Direction enum]
    end

    subgraph Database
        DB[MySQL Database]
    end

    %% Controller to Services
    A1 --> B1
    A2 --> B2
    A3 --> B3

    %% Services to Repositories
    B1 --> C1
    B2 --> C2
    B3 --> C3

    %% Services use domain
    B1 --> D1
    B2 --> D2
    B2 --> D4
    B2 --> D1
    B3 --> D3

    %% Repositories access domain
    C1 --> D1
    C2 --> D2
    C3 --> D3

    %% Database connections
    C1 --> DB
    C2 --> DB
    C3 --> DB
```

---

### 🗺️ Class Diagram

```mermaid
classDiagram
    class Map {
        Long id
        String name
        Integer width
        Integer height
        Integer wrapX(Integer x)
        Integer wrapY(Integer y)
        List~Rover~ roverList
        List~Obstacle~ obstacleList
    }

    class Rover {
        Long id
        Integer x
        Integer y
        Direction direction
        moveForward()
        moveBackward()
        turnLeft()
        turnRight()
    }

    class Obstacle {
        Long id
        Integer x
        Integer y
    }

    class Direction {
        <<enum>>
        N
        E
        S
        W
        Direction turnLeft()
        Direction turnRight()
    }

    Map "1" --> "*" Rover : contains
    Map "1" --> "*" Obstacle : contains
    Rover "1" --> "1" Map : belongs to
    Obstacle "1" --> "1" Map : belongs to
    Rover --> Direction : uses
```

### 🚦 Rover Command Flow

```mermaid
flowchart TD
    A[Start: Receive commands, get Rover/Map] --> B{Is direction null?}
    B -- Yes --> C[Throw exception: direction not set]
    B -- No --> D{More commands?}

    D -- Yes --> E[Read next command]
    E --> F{Is 'f' or 'b'?}
    
    F -- Yes --> G[Get next position with getNextPosition]
    G --> H{Is there an obstacle at next position?}
    H -- Yes --> I[Stop processing]
    H -- No --> J{Is it 'f'?}
    J -- Yes --> K[rover.moveForward]
    J -- No --> L[rover.moveBackward]
    K --> M[Apply wrapping: wrapX/wrapY]
    L --> M
    M --> D

    F -- No --> N{Is 'l' or 'r'?}
    N -- Yes --> O{Is it 'l'?}
    O -- Yes --> P[rover.turnLeft]
    O -- No --> Q[rover.turnRight]
    P --> D
    Q --> D

    N -- No --> R[Throw exception: invalid command]
    R --> D

    D -- No --> S[Save final rover state]
    S --> T[End]
```

---

## 📘 API Documentation - Swagger UI

The application includes interactive API documentation generated with **Swagger** using `springdoc-openapi`.

### ▶️ View Swagger Documentation

When the application is running, you can open the Swagger UI at:

```bash
http://localhost:8080/swagger-ui.html
```

Or alternatively:

```bash
http://localhost:8080/swagger-ui/index.html
```

This will open an interactive interface where you can test all endpoints (GET, POST, DELETE, etc.), see their descriptions, parameters, and expected responses.

---

## ✍️ Author

Created by **Enrique del Sol** as part of a technical challenge.

---

## 📄 License

This project is under the MIT License.