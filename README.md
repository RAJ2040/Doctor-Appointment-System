# 🏥 Doctor Appointment System

A RESTful backend application to manage doctors, patients, and appointments.

## 🚀 Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Data JPA (Hibernate)
- MySQL 8+
- Maven
- Swagger UI (OpenAPI)
- JUnit + Mockito
- Git

---

## 📁 Project Structure

com.appointment.system
│
├── controller        → Handles HTTP requests (REST Controllers)
├── dto              → Data Transfer Objects used between layers
├── entity           → JPA Entities mapped to database tables
├── exception        → Custom exceptions and global error handling
├── repository       → Spring Data JPA Repositories
├── service          → Service layer interfaces
├── service.impl     → Implementations of service interfaces
├── config           → Configuration classes (e.g., Swagger)
└── Application.java → Main Spring Boot application entry point



---

## 🔌 API Endpoints

### 👨‍⚕️ Doctors
- `POST /doctors` – Add a doctor
- `GET /doctors` – List doctors (with pagination)
- `GET /doctors/{id}` – Get doctor by ID
- `PUT /doctors/{id}` – Update doctor
- `DELETE /doctors/{id}` – Delete doctor

### 👤 Patients
- `POST /patients` – Add a patient
- `GET /patients` – List patients (with pagination)
- `GET /patients/{id}` – Get patient by ID
- `PUT /patients/{id}` – Update patient
- `DELETE /patients/{id}` – Delete patient

### 📅 Appointments
- `POST /appointments` – Book an appointment
- `GET /appointments` – List appointments (with pagination)
- `GET /appointments/{id}` – Get appointment by ID
- `DELETE /appointments/{id}` – Cancel appointment

---

## 📄 Input Sample (JSON)

### Book Appointment
json

POST /appointments
{
  "doctorId": 1,
  "patientId": 2,
  "appointmentDateTime": "2025-05-17T14:00:00"
}

Prerequisites

Java 17+
Maven
MySQL (running on localhost)

MySQL Setup
Create the database: CREATE DATABASE doctor_db;

Update application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/doctor_db
spring.datasource.username=root
spring.datasource.password=your_password

Run the App:
mvn spring-boot:run

Run Unit Tests:
mvn test

API Documentation (Swagger):
http://localhost:8080/swagger-ui.html

SQL Dump
mysql -u root -p doctor_db < doctor_db.sql


Author
Muthuraj Nadar

📧 rajnadar2030@gmail.com
📞 +91 9833970019



