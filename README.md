# Weather Forecas app
## Description
The app contains and stores weather forecast information. After selected the area, the weather forecast for the area is returned. Also the forecast record can be saved for later review.
## Technical properties
The app is built under SringBoot 3 MVC. It uses encoded security. It connects to MySQL database. The forecast information is fetched from https://api.meteo.lt/ API. 
## Pages description
### http://localhost:8080/login 
The page logs in the user that has credentials stored in DB.
### http://localhost:8080/
The page offers to select the city area and displays weather forecast information. In the forecast information table is functionality for saving actual forecast record into DB.
The page has minimal user information and logout button.
### http://localhost:8080/stored-forecasts
The page displays stored weather forecast information. 
The page also contains back to home page link and logout.
### http://localhost:8080/login?logout
The page is displayed after user logs out.
