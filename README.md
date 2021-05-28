# Demo
Demo project

This is a reactive webclient based spring-boot application.

Prerequisites:
	Java 8, mvn
	
To run the app:

	1. Go the root directory and build the app using the command: mvn clean install
	2. Run the app using the command: mvn spring-boot:run
	
	Once successfully running,  use the URI: 
		localhost:8080/coding/exercise/quiz?amount=5&categories=11,12,13,14....
		
	*The query part (amount=5&categories=11,12,13,14....) is optional. The default values are 5 for amount and 11,12 for categories
