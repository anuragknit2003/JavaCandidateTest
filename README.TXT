To Run the app , please edit the application.properties and add the database properties.
The Application uses Spring Data JPA and expects certain table definitions. Please refer to test/resources/database-setup.sql for schema.

The code is tested using in-memory HSQL DB. Please refer to com.stepstone.quiz.controller.QuestionControllerTest