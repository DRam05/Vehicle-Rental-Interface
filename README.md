# Vehicle Rental Management System

A Java-based vehicle rental management system developed as a university course project. This application simulates the operations of a vehicle rental agency, including vehicle reservations, rental transactions, pricing based on time periods, and role-based user interfaces for employees and managers.

---

## Technologies

- Java (Object-Oriented Programming)
- Linked Lists
- Java Swing (GUI Development)
- Java Collections Framework
  - ArrayList
  - HashMap
- Exception Handling
  - Custom checked exceptions
- Date & Time / Time-Based Logic

---

## Features

### Vehicle Class Hierarchy
- A base `Vehicle` class defines shared vehicle attributes and behaviors.
- Subclasses (`Car`, `SUV`, `Minivan`) extend the base class with specialized behavior.

### Reservation and Rental System
- Vehicles can be reserved and rented for specific time periods.
- Reservation details and rental details are tracked separately.

### Transaction Management
- All rentals are recorded as transactions.
- Transactions are stored, retrieved, and managed through a centralized system.

### Pricing and Rate Calculation
- Rental prices are determined using current vehicle rates.
- Time periods affect pricing through configurable rate logic.

### Role-Based User Interfaces
- An employee GUI for handling rentals and reservations.
- A manager GUI for administrative and management tasks.

### Exception-Driven Validation
- Custom exceptions ensure invalid operations are prevented, including:
  - Reserving unavailable vehicles
  - Accessing vehicles that do not exist
  - Renting unreserved vehicles

---

## The Process

- Since this was a university project, a UML diagram was provided and used to guide development, along with two files containing starter code. All remaining files were created from scratch.

- A base abstract `Vehicle` class was created, including constructors, getters, and setter methods. This class was then extended by `Car`, `SUV`, and `Minivan` subclasses to represent different vehicle types, each with their own constructors and `toString` methods.

- Next, the `Transaction` and `Transactions` classes were implemented using a linked list model, with each class containing constructors, getters, and setters for managing nodes and traversal.

- The `Reservation` class was then implemented to track vehicle status and store all necessary information for reserved vehicles.

- Custom exceptions were added to enforce business rules and prevent invalid system states, such as:
  - Invalid VIN numbers
  - Reserving an already reserved vehicle
  - Unreserving a vehicle that is not currently reserved

- Finally, Swing-based GUIs were implemented to provide separate interfaces for employees and managers, each supporting their own set of operations.

---

## What I Learned

- Designing large object-oriented systems with many interacting classes
- Applying inheritance and polymorphism in real-world simulations
- Separating GUI logic from backend logic
- Managing application state across multiple components

---

## Author

**Diego Ramos**  
COSC 237 -- Fall 2025
