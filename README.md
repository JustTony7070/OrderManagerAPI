# REST API - Spring Boot

REST API built with Spring Boot for managing **products**, **users**, and **orders**.  
The project uses Spring Data JPA for persistence with a MySQL database.

## Technologies
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Maven

---

## Features

The application allows to:
- Manage products (full CRUD operations)
- Register users
- Create and manage orders linked to users and products
- Automatically calculate order totals

---

## Endpoints

### Product

- `GET /products` → get all products  
- `GET /products/{id}` → get product by id  
- `POST /products` → create a product  
- `PUT /products/{id}` → full update  
- `PATCH /products/{id}` → partial update  
- `DELETE /products/{id}` → delete product  

---

### User

- `GET /users` → get all users  
- `GET /users/{id}` → get user by id  
- `POST /users` → create user  

---

### Order

- `POST /orders` → create an order  
- `GET /orders/{id}` → get order details  
- `PUT /orders/{id}/cancel` → cancel order  
- `GET /orders/user/{userId}` → get user orders  

---

## Notes

- Entity relationships are managed using JPA (One-to-Many, Many-to-Many)
- `totalPrice` is automatically calculated
- APIs tested using Postman