# College Administrative Management System
 
![Java](https://img.shields.io/badge/Java-11%2B-orange?logo=openjdk)
![Build](https://img.shields.io/badge/build-javac-blue)
![License](https://img.shields.io/badge/license-MIT-green)
 
A comprehensive Object-Oriented Java console application designed to manage the administrative operations of an academic institution (departments, committees, and academic staff). Built with defensive input validation, custom exceptions, and serializable persistence to ensure data integrity and a robust CLI user experience.
 
## Authors
 
- Raz Eini
- Raz Yakoby


## Class Diagram

![Class Diagram](assets/class%20diagram%20pic.png)

## Table of Contents
 
- [System Architecture](#system-architecture)
- [Domain Model](#domain-model)
- [Directory Structure](#directory-structure)
- [Interactive Features (CLI Menu)](#interactive-features-cli-menu)
- [Getting Started](#getting-started)
- [Example Session](#example-session)
- [License](#license)
## System Architecture
 
The system uses a clean object-oriented domain model incorporating inheritance, generic type safety, and polymorphic behaviors.
 
### Core Technical Highlights
 
**1. Advanced Generics & Type Safety**
 
`Committee` Degree Binding: `Committee` is parameterized over `Lecturer.Degree`:
 
```java
public class Committee<T extends Lecturer.Degree> implements Comparable<Committee<?>>, Cloneable, Serializable
```
 
Constructors and `setLecturers()` strictly enforce that a member's degree matches the committee's required bound `T`.
 
**2. Exception Handling & Control Flow**
 
- `AdministrativeException`: Handles domain-level logical errors (e.g., duplicate assignments, missing chairman).
- `GoBackException`: A top-level exception triggered when a user inputs `back`. Integrated with `InputHelper.readLine()` to provide safe, stack-unwinding cancellation out of nested prompts.
**3. Design Patterns & Interfaces**
 
- **Dynamic Comparisons (`Comparable`)**: `Committee` supports runtime criteria switching via `setCompareMode()`, comparing instances either by total member count or total publication count.
- **Prototyping (`Cloneable`)**: `Committee.clone()` generates a deep copy of the committee and its members (using copy constructors across `Professor`, `Doctor`, and `Lecturer`), prefixing the cloned instance name with `"new-"`.
- **Persistence**: Objects are serialized to and deserialized from `college_data.dat` via `ObjectOutputStream` and `ObjectInputStream` helpers (`saveSerialization` / `loadSerialization`).
## Domain Model
 
`Lecturer` is the base type of the academic staff hierarchy, extended by `Doctor` and `Professor`, each carrying its own degree-specific behavior and salary rules. `Committee<T extends Lecturer.Degree>` binds to one of these degrees at the type level, so a `Committee<Professor>` can only ever accept professors as members — the compiler enforces it, not just runtime checks.
 
```
Lecturer
 ├── Doctor
 └── Professor
 
Committee<T extends Lecturer.Degree>
 └── members: List<T>
```
 
A full UML class diagram is available at `assets/class diagram pic.png`.
 
## Directory Structure
 
```
.
├── assets/
│   └── class diagram pic.png
├── src/
│   ├── Administrative.java
│   ├── Committee.java
│   ├── Department.java
│   ├── Lecturer.java
│   ├── Doctor.java
│   ├── Professor.java
│   ├── Article.java
│   ├── InputHelper.java
│   ├── GoBackException.java
│   ├── AdministrativeException.java
│   └── Main.java
├── college_data.dat         # Auto-generated persistence file
└── README.md
```
 
## Interactive Features (CLI Menu)
 
The interactive CLI provides the following administrative capabilities:
 
| ID | Action | Description |
|----|--------|--------------|
| 1 | Add Lecturer | Register a standard Lecturer, Doctor, or Professor |
| 2 | Add Committee | Create a generic committee bound to a specific degree |
| 3 | Add Member to Committee | Assign an eligible lecturer to a committee |
| 4 | Update Committee Chairman | Assign or replace committee chairmanship |
| 5 | Remove Member from Committee | Unassign a lecturer from a committee |
| 6 | Add Department | Register an academic department |
| 7 | Assign Lecturer to Department | Link a lecturer to a department |
| 8 | Average Salary (College) | Calculate overall average salary across all lecturers |
| 9 | Average Salary (Department) | Calculate average salary within a specific department |
| 10 | Display All Lecturers | Print complete roster and attributes |
| 11 | Display All Committees | Print committee details, chairmen, and members |
| 12 | Add Article to Lecturer | Record a published research article |
| 13 | Compare Articles | Compare article publication counts between two lecturers |
| 14 | Compare Committees | Compare two committees dynamically by members or total articles |
| 15 | Clone Committee | Deep-copy a committee and its nested members |
| 0 | Exit | Save system state to `college_data.dat` and terminate |
 
## Getting Started
 
### Prerequisites
 
- Java Development Kit (JDK): Version 11 or higher installed.
### Compilation & Execution
 
Clone the repository:
 
```bash
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name
```
 
Compile the source files:
 
```bash
javac -d bin src/*.java
```
 
Run the application:
 
```bash
java -cp bin Main
```
 
## Example Session
 
```
=== College Administrative Management System ===
1.  Add Lecturer
2.  Add Committee
3.  Add Member to Committee
...
0.  Exit
 
> Select an option: 1
> Enter degree (Lecturer/Doctor/Professor): Doctor
> Enter name: Dana Cohen
> Enter salary: 14500
Doctor 'Dana Cohen' added successfully.
```
 
Typing `back` at any prompt safely unwinds out of the current operation via `GoBackException`, returning you to the main menu without corrupting in-progress data.
 
## License
 
This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.
 

