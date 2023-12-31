# Backend Engineer Assignment
In this assignment, you are expected to make changes to an existing API backend code. Please read the constraints and tasks carefully, make your changes, and follow submission guideline at the bottom of the page.

## Scenario:
eDelivery is a delivery startup which allows its users to register as delivery men or customers.

Customer gives an order from the online shop and delivery man picks the order and drives it to the customer.

At the end of delivery, delivery man sends a request to the server with his/her id, customer id, order id, the distance in km and the start and end time of delivery.

## Application Constraints:
- Users are using eDelivery mobile app, assume that the API is only consumed by mobile app
- User can not be both customer and delivery man at the same time. Only one must be chosen at registration
- The delivery man earns commission for each order. `Commission = OrderPrice * 0.05 + Distance * 0.5`
- The delivery man is not allowed to deliver multiple orders at the same time

## Tasks:
1. Codebase contains functional or logical bugs. Find these issues and fix them,
2. Mobile team asked you for a new API endpoint to display top 3 delivery men who earn the most commission in given time interval, they also want to show average commission as well.
3. Customer support team wants to be notified when a delivery is not done in 45 minutes. Create the scheduled task to check and notify CS team asynchronously. You are not required to implement notification, you can just print the message.

## Evaluation Criteria:
- Code quality and Applying Best Practices
- Feature implementation
- Bug fixes
- Git commit structure
- You do not need to secure endpoints in scope of this assignment

## Development Environment:
- GIT for version control
- Gradle
- Java 8
- Postgresql
- Liquibase

## To run existing project with Docker
1. Application is already dockerized. You can run following command in root folder for setting up Postgres database.
- ```docker compose up```
2. Run application from Gradle or ide.

# Submission
Please open a Pull/Merge request to this repository with everything you have prepared.

- Make sure that project is building correctly.
- Make sure code is working properly.

