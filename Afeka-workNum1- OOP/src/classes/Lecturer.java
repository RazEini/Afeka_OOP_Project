package classes;

public class Lecturer {
    private String lecturer_name;
    private String lecturer_id;
    public enum Degree {
        BACHELOR_DEGREE, MASTER_DEGREE, DR, PROFESSOR
    }
    private Degree lecturerDegree;
    private String degreeName;
    private int salary;
    private Department department;

    public Lecturer() {
        setName("Unknown");
        setId("000000000");
        this.degreeName = "Unknown";
        setSalary(0);
        this.department = new Department();
    }

    public Lecturer(String name, String id, String degreeName, int salary, Department department) {
        setName(name);
        setId(id);
        this.degreeName = degreeName;
        setSalary(salary);
        setDepartment(department);
    }

    public Lecturer(Lecturer other) {
        if (other != null) {
            setName(other.lecturer_name);
            setId(other.lecturer_id);
            this.degreeName = other.degreeName;
            this.lecturerDegree = other.lecturerDegree;
            setSalary(other.salary);
            setDepartment(other.department);
        }
    }

    public String getName() { return this.lecturer_name; }
    public String getId() { return this.lecturer_id; }
    public Degree getDegree() { return this.lecturerDegree; }
    public int getSalary() { return this.salary; }
    public String getDegreeName() { return this.degreeName; }
    public Department getDepartment() { return new Department(this.department); }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.lecturer_name = name;
        } else {
            this.lecturer_name = "Unknown";
            System.out.println("Error: Lecturer name cannot be empty.");
        }
    }

    public void setId(String id) {
        if (id != null && id.length() == 9 && id.matches("[0-9]+")) {
            this.lecturer_id = id;
        } else {
            this.lecturer_id = "000000000";
            System.out.println("Error: Invalid ID.");
        }
    }

    public void setDegree(String degreeStr) {
        if (degreeStr == null) return;
        String upper = degreeStr.toUpperCase().replace(" ", "_");
        for (Degree d : Degree.values()) {
            if (d.name().equals(upper)) {
                this.lecturerDegree = d;
                return;
            }
        }
        System.out.println("Error: Degree " + degreeStr + " not found.");
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public void setSalary(int salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            this.salary = 0;
            System.out.println("Error: Salary cannot be negative.");
        }
    }

    public void setDepartment(Department department) {
        if (department != null) {
            this.department = new Department(department);
        } else {
            this.department = new Department();
        }
    }

    @Override
    public String toString() {
        return "Lecturer: " + lecturer_name + " (" + lecturer_id + ")";
    }
}