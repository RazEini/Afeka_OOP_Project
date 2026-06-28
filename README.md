# College Administrative Management System

A comprehensive Object-Oriented console application designed to manage the administrative operations of an academic institution. The system robustly handles the structural hierarchy of departments, committees, and various academic staff members while ensuring data integrity.

## Authors
* **Raz Eini**
* **Raz Yakoby**

## System Architecture

Below is the UML class diagram representing the system's architecture and object relations:

![Class Diagram](Afeka_OOP_Project\assets\class%20diagram%20pic.png)

## User Interface & Features

The system operates via a robust Command Line Interface (CLI) interactive menu. It features strict input validation (defensive programming) to prevent system crashes from invalid user inputs (e.g., negative numbers, strings containing digits where names are expected, invalid IDs). 

### Global Navigation
* **The `back` Command:** At any prompt within the sub-menus, the user can type `back` to immediately abort the current operation and return to the main menu safely.

### Menu Options
1. **Add Lecturer:** Registers a new staff member (Validates degree: BACHELOR, MASTER, DR, PROFESSOR).
2. **Add Committee:** Creates a new committee (Enforces that the Chairman must be a Doctor or Professor and not already chairing another committee).
3. **Add Member to Committee:** Assigns existing lecturers to committees.
4. **Update Committee Chairman:** Replaces the current chairman with a valid alternative.
5. **Remove Member from Committee:** Removes a lecturer from a specific committee.
6. **Add Department:** Registers a new academic department.
7. **Assign Lecturer to Department:** Links a lecturer to a specific department.
8. **College Salary Report:** Calculates and displays the average salary of all staff.
9. **Department Salary Report:** Calculates the average salary for a specific department.
10. **Lecturers Roster:** Displays full data for all registered lecturers.
11. **Committees Roster:** Displays full data for all active committees.
12. **Add Article:** Adds publication records to a specific Doctor/Professor.
13. **Compare Lecturers (Articles):** Compares two advanced staff members based on their publication count.
14. **Compare Committees (Dynamic):** Compares two committees dynamically either by the **number of members** or by the **total number of articles** published by its members.
15. **Clone Committee:** Creates a deep/shallow duplicate of an existing committee.

## Technical Concepts Applied

* **Object-Oriented Programming (OOP):** Deep utilization of Inheritance, Polymorphism, Encapsulation, and Composition across the administrative entities.
* **Custom Exception Handling:** * `AdministrativeException` for logical system errors (e.g., adding an existing user).
  * `GoBackException` (a private static inner class) uniquely used as a control-flow mechanism to return to the main menu gracefully from any nested prompt.
* **Dynamic Interfaces (`Comparable`):** The `Committee` class implements `Comparable` dynamically, changing its comparison logic based on a state variable (Members count vs. Articles count).
* **Prototyping (`Cloneable`):** Implementation of the `clone()` method for safe object duplication.
* **Defensive Programming:** Extensive `while` loops and string parsing techniques in the `Main` class to validate inputs before instantiating objects or calling methods.
