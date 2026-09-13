# Backend Development: Practice 1

This repository contains the first practical backend development assignment. While the original university curriculum specified PHP, this project was intentionally implemented using Java and Spring Boot to demonstrate proficiency in modern, enterprise-grade backend development, containerization, and clean MVC architecture.

## Tech Stack
- **Language**: Java 17
- **Framework**: Spring Boot 3.x (Web, JDBC, Thymeleaf)
- **Database**: MySQL 8.4
- **Containerization**: Docker, Docker Compose
- **Build Tool**: Maven

## Project Architecture
The application is built as a cohesive Spring Boot project following the Model-View-Controller (MVC) pattern. The code is strictly separated into logical layers to ensure reusability and maintainability:

- **Controllers**: Handle HTTP requests, validate input parameters, and route to the appropriate services.
- **Services**: Contain the core business logic (bitwise decoding, sorting algorithms, OS command execution).
- **Models**: Represent the data structures (e.g., User entity).
- **Templates**: Thymeleaf HTML files for rendering the dynamic UI.

## Features
This project implements four distinct web endpoints, demonstrating a progression from basic database integration to advanced algorithmic and system-level tasks:

1. **User Dashboard** (`/`):
    - Connects to the MySQL database running in Docker.
    - Fetches and displays a list of users from the database.
    - Shows runtime environment information (Java version, OS, uptime).
2. **Drawer Service** (`/drawer`):
    - Accepts a single integer parameter (0-255).
    - Uses bitwise operations (`&`, `>>`) to decode shape, color, and size.
    - Dynamically generates and renders an SVG image based on the decoded parameters.
3. **Sort Service** (`/sort`):
    - Accepts a comma-separated string of integers via URL parameters.
    - Implements the Merge Sort algorithm recursively.
    - Displays both the original and the sorted arrays.
4. **Server Info Service** (`/server-info`):
    - Executes native Unix commands (`whoami`, `ps`, `df`, `uname`) securely via Java `ProcessBuilder`.
    - Displays real-time server administrative information.