# College Administrative Management System

A comprehensive Object-Oriented console application designed to manage the administrative operations of an academic institution (departments, committees, and academic staff). The system uses defensive input validation and custom exceptions to preserve data integrity and provide a robust CLI experience.

## Authors
* **Raz Eini**
* **Raz Yakoby**

## System Architecture

Below is the UML class diagram representing the system's architecture and object relations:

<img src="assets/class diagram pic.png" alt="Class Diagram" width="100%">

## Source Highlights / Implementation Notes

- Collections: The implementation uses java.util.ArrayList for collections internally (e.g., Administrative.lecturers, Department.lecturers_Array, Committee.lecturers_Array). The UML reflects ArrayList usage (not fixed-size arrays) and the project does not use manual counters for collection sizes.
- Committee degree binding: Committee is a generic type parameterized by a Lecturer.Degree, declared as Committee<T extends Lecturer.Degree>. Committee constructors and setLecturers enforce that members' degrees match the committee's required degree.
- Cloning: Committee.clone() returns a Committee<T>. The clone method prefixes the cloned committee name with "new-" and clones members via the available copy constructors (Professor, Doctor, Lecturer), providing a deep-copy-like behavior of members and chairman.
- Persistence: Main serializes/deserializes the Administrative object to/from "college_data.dat" using ObjectOutputStream/ObjectInputStream. The Main class includes private static helpers (saveSerialization and loadSerialization) to handle persistence.
- Input and GoBack behavior:
  - `InputHelper` is a top-level helper class that centralizes reading lines from System.in. `InputHelper.readLine()` will throw a `GoBackException` when the user types the word `back`.
  - `GoBackException` is a top-level class (not an inner class) that extends `Exception` and is used as a control-flow mechanism to return to the main menu from nested prompts.
- Public fields: `Administrative.committees` is implemented as a public `ArrayList<Committee>` in the source (not hidden). This is an implementation detail to be aware of.
- Degree enum: `Lecturer` contains a nested enum `Degree` with values `BACHELOR_DEGREE`, `MASTER_DEGREE`, `DR`, `PROFESSOR`.
- Defensive validation: `Main` performs strict validation on input (IDs, names, salaries, department names, etc.) and uses `InputHelper.readLine()` to allow safe cancellation ("back") from nested prompts.

## User Interface & Features

The system operates via an interactive CLI main menu offering the following operations (matches the implemented menu):
1. Add Lecturer
2. Add Committee
3. Add Member to Committee
4. Update Committee Chairman
5. Remove Member from Committee
6. Add Department
7. Assign Lecturer to Department
8. Display Average Salary of All College Lecturers
9. Display Average Salary of a Specific Department
10. Display All Lecturers Information
11. Display All Committees Information
12. Add Article to Lecturer
13. Compare number of articles between two Lecturers
14. Compare committees by number of members or by number of articles
15. Clone a Committee
0. Exit (on exit, Administrative data will be serialized to "college_data.dat")

## Technical Concepts Applied
* Object-Oriented Programming (OOP): Inheritance (Doctor, Professor), Polymorphism, Encapsulation, Composition (departments, committees).
* Custom Exception Handling: AdministrativeException for logical errors; GoBackException (top-level) for user-initiated "back" control flow handled via InputHelper.
* Dynamic Interfaces (Comparable): Committee implements Comparable, dynamically switching compare criteria by setCompareMode().
* Prototyping (Cloneable): Committee implements clone(), cloning members with copy constructors.
* Defensive Programming: Extensive validation logic within Main and other classes.

Note: README was updated to reflect actual implementation details: `InputHelper` is a top-level class and `GoBackException` is top-level (not an inner class of `Main`), Committee is generic, and collections use `ArrayList`.
