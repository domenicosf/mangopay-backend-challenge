Certainly! Here's an updated version of the `README.md` with the details you requested:

```markdown
# VeryCoolApp

Welcome to VeryCoolApp! This application allows users to manage and share their creative ideas.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 17
- Maven
- Git

## Getting Started

To get started with VeryCoolApp, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/domenicosf/mangopay-backend-challenge.git
   ```

2. Navigate to the project directory:

   ```bash
   cd verycoolapp
   ```

3. Build the project:

   ```bash
   mvn clean install
   ```

## Running the Application

To run the application, execute the following command:

```bash
mvn spring-boot:run
```

The application will start, and you can access it at `http://localhost:8080`.

## Running Tests

To run unit and integration tests, use the following command:

```bash
mvn test
```

## Testing APIs Using Swagger

VeryCoolApp provides an interactive API documentation using Swagger. Follow these steps to access the documentation:

1. Start the application (if not already running).
2. The API should be used in the following order:
   1. Create a user filling the name and email.
   2. Register an idea for the user created. Fill the fields: sourceUrl, description and day ("dd-MM-yyyy").
3. Open a web browser and go to:

   ```
   http://localhost:8080/swagger-ui.html
   ```

3. Explore and test the available endpoints using the Swagger UI interface. You can input sample data and execute API requests directly from the documentation.

## Accessing the H2 Console

VeryCoolApp uses H2 as an in-memory database. To access the H2 Console and view the database content, follow these steps:

1. Start the application (if not already running).
2. Open a web browser and go to:
   ```
   http://localhost:8080/h2-console
   ```
3. Enter the following information in the H2 login page:
   * JDBC URL: jdbc:h2:mem:verycoolapp 
   * Username: sa 
   * Password: (Leave it empty)
4. Click the "Connect" button.
5. You will now have access to the H2 Console where you can query and explore the database.

## Contributing

Contributions are welcome! Feel free to fork the repository and submit pull requests.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```

In this version of the `README.md`, I've adjusted the instructions to mention running the application using `mvn spring-boot:run` and running tests using `mvn test`. Additionally, I've explained how to access and use Swagger for testing the APIs.