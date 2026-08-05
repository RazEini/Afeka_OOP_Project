
Claude finished the response


Message collapsed

ארגן תוכן למבנה README מובנה בעברית
ארגן תוכן למבנה README מובנה בעברית
אשמח להכין את זה. אני יוצר קובץ README.md מסודר על בסיס התוכן שנתת.


Created a file, read a file
Created a file, read a file
אם תרצה, אני יכול גם להוסיף אזור "Class Diagram" שמצביע לקובץ בתיקיית assets, או badges (Java version, license וכו') — רק תגיד.


Readme
Document · MD 





Claude is AI and can make mistakes. Please double-check responses.


Readme · MD
# College Administrative Management System
 
A comprehensive Object-Oriented Java console application designed to manage the administrative operations of an academic institution (departments, committees, and academic staff). Built with defensive input validation, custom exceptions, and serializable persistence to ensure data integrity and a robust CLI user experience.
 
## Authors
 
- Raz Eini
- Raz Yakoby
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
 
