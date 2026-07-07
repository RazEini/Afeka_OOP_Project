package razEini_razYacobi;

import java.io.Serializable;
import java.util.ArrayList;

public class Lecturer implements Comparable<Lecturer> , Serializable {
    private String lecturer_name;
    private String lecturer_id;
    private int salary;
    private Department department;
    private ArrayList<Committee> myCommittees;

    public enum Degree { BACHELOR_DEGREE, MASTER_DEGREE, DR, PROFESSOR }
    private Degree lecturerDegree;

    public Lecturer() {
        setName("Unknown");
        setId("000000000");
        setSalary(0);
        this.department = null;
        this.myCommittees = new ArrayList<>();
        this.lecturerDegree = Degree.BACHELOR_DEGREE;
    }

    public Lecturer(String name, String id, int salary, Department department, Degree degree) {
        setName(name);
        setId(id);
        setSalary(salary);
        setDepartment(department);
        this.myCommittees = new ArrayList<>();
        this.lecturerDegree = degree;
    }

    public Lecturer(Lecturer other) {
        if (other != null) {
            setName(other.lecturer_name);
            setId(other.lecturer_id);
            this.lecturerDegree = other.lecturerDegree;
            setSalary(other.salary);
            this.department = other.department;
            this.myCommittees = new ArrayList<>(other.myCommittees);
        }
    }

    public void addCommittee(Committee c) throws AdministrativeException {
        if (c == null) throw new AdministrativeException("Error: Cannot add a null committee.");
        myCommittees.add(c);
    }

    public String getName() { return this.lecturer_name; }
    public String getId() { return this.lecturer_id; }
    public Degree getDegree() { return this.lecturerDegree; }
    public int getSalary() { return this.salary; }
    public Department getDepartment() { return this.department; }

    public void setName(String name) { this.lecturer_name = (name != null) ? name : "Unknown"; }
    public void setSalary(int salary) { this.salary = (salary >= 0) ? salary : 0; }
    public void setDepartment(Department department) { this.department = department; }

    public void setId(String id) {
        if (id != null && id.length() == 9) this.lecturer_id = id;
        else this.lecturer_id = "000000000";
    }

    public void removeCommittee(String committeeName) throws AdministrativeException {
        if (committeeName == null || myCommittees.isEmpty()) {
            throw new AdministrativeException("Error: Committee name cannot be null.");
        }
        myCommittees.removeIf(c -> c != null && c.getCommitteeName().equalsIgnoreCase(committeeName));
    }

    @Override
    public int compareTo(Lecturer obj) {
        if (obj == null) return 1;

        int thisArticles = 0;
        int otherArticles = 0;

        if (this instanceof Doctor) {
            thisArticles = ((Doctor) this).getNumOfArticles();
        }

        if (obj instanceof Doctor) {
            otherArticles = ((Doctor) obj).getNumOfArticles();
        }

        return Integer.compare(thisArticles, otherArticles);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Lecturer other = (Lecturer) obj;
        if (this.lecturer_id == null) return other.lecturer_id == null;
        return this.lecturer_id.equals(other.lecturer_id);
    }

    @Override
    public String toString() {
        String info = "Lecturer Details:\n" +
                "- Name: " + lecturer_name + "\n" +
                "- ID: " + lecturer_id + "\n" +
                "- Salary: " + salary + "\n" +
                "- Degree: " + (lecturerDegree != null ? lecturerDegree : "None") + "\n";
        info += "- Department: " + (department != null ? department.getDepartmentName() : "None") + "\n";

        if (!myCommittees.isEmpty()) {
            info += "- Committees: ";
            for (int i = 0; i < myCommittees.size(); i++) {
                info += myCommittees.get(i).getCommitteeName() + (i < myCommittees.size() - 1 ? ", " : "");
            }
        } else {
            info += "- Committees: None";
        }
        return info;
    }
}