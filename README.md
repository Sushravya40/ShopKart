# ShopKart

ShopKart is a full-stack e-commerce application. It consists of a Java/Spring Boot backend and a React/Vite frontend.

## Project Structure

* `/backend` - The Java Spring Boot REST API
* `/frontend` - The React (Vite) User Interface

## Prerequisites

* Java 17+
* Node.js & npm
* MySQL

## Getting Started

### 1. Database Setup
Ensure you have MySQL running locally. Create a database named `Project`. 
Update the `backend/src/main/resources/application.properties` file with your database username and password.

### 2. Backend Setup
1. Open a terminal and navigate to the `backend` directory.
2. Run the Spring Boot application using Maven:
   ```bash
   ./mvnw spring-boot:run
   ```
   The backend API will start on `http://localhost:8080`.

### 3. Frontend Setup
1. Open a new terminal and navigate to the `frontend` directory.
2. Install the dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   npm run dev
   ```
   The frontend will be available at `http://localhost:5173` (or the port specified by Vite).

## Features
* User Authentication & Authorization (JWT)
* Product Browsing & Search
* Shopping Cart Management
* Order Processing & Payment Integration (Razorpay)
* Admin Dashboard for Managing Products and Users
