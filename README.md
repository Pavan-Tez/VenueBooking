this consists of 3 microservices 
  -Inventory Servcice (Done)
  -Booking Service (In Progress)
  -Order Service (In Progress)

Inventory Service has the redgate flyway for DB migration and Docker setup for DB 

Pre-requisits
-------------
MySql 
MySql Workbench(optional)
Docker
Docker Desktop
Spring-Boot 3 or higher 

Start-up
--------
clone the repo and 

cd ./InventoryService
mvn clean install
docker compose up
docker ps(to check if started)
(check in docker desktop to check if the container running)
(check mysql workbench and add connection using 3306 or 3307)
mvn spring-boot:run
