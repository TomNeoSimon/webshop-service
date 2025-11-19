# Webshop Service — Final Exam (Tom Neo Simon)

Final exam backend for the bookstore webshop. The backend is a serverless Java application built for AWS Lambda / API Gateway using the `aws-serverless-java-container` starter.

## Backend information
- Deployed as a serverless AWS Java function — no local backend deployment is required for the frontend to work.
- The API is reachable on the internet; start only the frontend to use the full app.
- The first time the frontend makes a request it may take some time to boot up

## How to run
The backend is already deployed so the frontend will communicate with the live API.

## Database information
The database is also deployed on AWS so there is no need to set up a local database.
The PostgreSQL database is reachable through the internet. The scheme of the database can be seen through the @Entity DB objects in this project