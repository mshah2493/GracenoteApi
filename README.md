# GraceNoteTakeHomeProjectApi		

This is a REST API for storing and fetching data from PostgreSQL database. It is a springboot application which uses JPA hibernate and graphQL to communicate with the database (the graphql queries are written in the schema file under `resources/schema`). Sample unit tests have been implemented for an endpoint to show how to test springboot API (more can be implemented for end to end/unit).

The ER diagram is included under the `src/main/resources/db` folder. DDL is included under the `src/main/resources/db` folder (no need to execute to create tables, the code does it for us).

All the endpoints have been tested with postman. The postman collection file is included under `src/main/resources/postman`.

## Source Code
Javadoc comments are included for each method in every class. The modules and classes are described as follows.

### 1) Application : com.gracenote.api:
	GraceNoteTakeHomeProjectAPIApplication.java = this is the main springboot application class

### 2) Controllers: com.gracenote.api.controller
	MovieController.java : This class contains endpoints to save and fetch data from multiple relational DB tables (table names: `movie`, `theater`, `theater_chain`, `movie_shows`).
	MovieRecordsController.java : This class contains endpoints to save and fetch data from a single DB table (table name: `movie_records`).
	
### 3) DataFetchers :  com.gracenote.api.datafetchers
	The DataFetcher is a graphQL engine that uses data fetchers to resolve / fetch a logical field into a runtime object that will be sent back as part of the overall graphQL ExecutionResult.
	
	MovieDataFetcher.java : This class executes graphQL queries by resolving fields into runtime object for the `movie_shows` table. 
	MovieDataFetcher.java : This class executes graphQL queries by resolving fields into runtime object for the `movie_record` table.

### 4) FileIO : com.gracenote.api.fileio
	ExcelFileIO.java : This class contains methods to read CSV files and create data objects.
	
### 5) Message : com.gracenote.api.message
	ResponseMessage.java : This class contains messages that can be passed with the ResponseEntity. Messages can be anything (success/failure/exception).
	
### 6) Model : com.gracenote.api.model
	Movie.java : This entity class contains values for the `movie` table. 
	MovieRecord.java : This entity class contains values for the `movie_records` table. 
	MovieShow.java : This entity class contains values for the `movie_shows` table.
	Theater.java : This entity class contains values for the `theater` table.
	TheaterChain.java : This entity class contains values for the `theater_chain` table. 
	
### 7) Repository: com.gracenote.api.repository
	MovieRecordsRepository.java : This interface extends the JpaRepository for basic CRUD operations for the `movie_records` table.
	MovieRepository.java : This interface extends JpaRepository for basic CRUD operations for the `movie` table.
	MovieShowsRepository.java : This interface extends JpaRepository for basic CRUD operations for the `movie_shows` table.
	TheaterChainRepository.java : This interface extends JpaRepository for basic CRUD operations for the `theater_chain` table.
	TheaterRepository.java : This interface extends JpaRepository for basic CRUD operations for the `theater` table.
	
###  8) Service: com.gracenote.api.service
	GraphQLService.java : This class loads the GraphQL schema file and binds them to respective data fetchers.
	MovieRecordsService.java : This is a service class for the `MovieRecordsController` class that saves and fetches data from the `movie_records` table.
	

## Execution
1. Create a PostgreSQL database locally with the name "gracenote"
2. Import the project into Eclipse 
3. Provide the connection URL, username and password in the `application.properties` file under `src/main/resources`.
4. Run the following command: `mvn clean install`
5. Run the project as a springboot app. 
6. Import the postman collection file (see `src/main/resources/postman`) into postman to test the endpoints.

## Endpoints
1. http://localhost:8080/gracenote/api/movies/upload-movies - upload a CSV file to multiple tables
2. http://localhost:8080/gracenote/api/movie-records/upload-records - upload a CSV file file to a table
3. http://localhost:8080/gracenote/api/movie-records/allrecords - fetch records from a table using native query
4. http://localhost:8080/gracenote/api/movies/moviesonjoin - fetch records from multiple tables using native query
5. http://localhost:8080/gracenote/api/movie-records/records - fetch records from a table using graphQL
6. http://localhost:8080/gracenote/api/movies/movies - fetch records from multiple tables using graphQL