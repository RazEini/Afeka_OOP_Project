package razEini_razYacobi;

public class Lecturer implements Comparable<Lecturer> {
    private String lecturer_name;
    private String lecturer_id;
    private int salary;
    private Department department;

    private Committee[] myCommittees;
    private int commCount;

    public enum Degree { BACHELOR_DEGREE, MASTER_DEGREE, DR, PROFESSOR }
    private Degree lecturerDegree;

    public Lecturer() {
        setName("Unknown");
        setId("000000000");
        setSalary(0);
        this.department = null;
        this.myCommittees = new Committee[1];
        this.commCount = 0;
        this.lecturerDegree = Degree.BACHELOR_DEGREE;
    }

    public Lecturer(String name, String id, int salary, Department department, Degree degree) {
        setName(name);
        setId(id);
        setSalary(salary);
        setDepartment(department);
        this.myCommittees = new Committee[1];
        this.commCount = 0;
        this.lecturerDegree = degree;
    }

    public Lecturer(Lecturer other) {
        if (other != null) {
            setName(other.lecturer_name);
            setId(other.lecturer_id);
            this.lecturerDegree = other.lecturerDegree;
            setSalary(other.salary);
            this.department = other.department;
            this.commCount = other.commCount;
            this.myCommittees = new Committee[other.myCommittees.length];
            for (int i = 0; i < other.commCount; i++) {
                this.myCommittees[i] = other.myCommittees[i];
            }
        }
    }

    public void addCommittee(Committee c) {
        if (commCount == myCommittees.length) {
            Committee[] temp = new Committee[myCommittees.length * 2];
            for (int i = 0; i < commCount; i++) temp[i] = myCommittees[i];
            myCommittees = temp;
        }
        myCommittees[commCount++] = c;
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

    public void removeCommittee(String committeeName) {
        if (committeeName == null || commCount == 0) return;
        Committee[] temp = new Committee[myCommittees.length];
        int j = 0;
        boolean found = false;
        for (int i = 0; i < commCount; i++) {
            if (myCommittees[i] != null && !myCommittees[i].getCommitteeName().equalsIgnoreCase(committeeName)) {
                temp[j] = myCommittees[i];
                j++;
            } else {
                found = true;
            }
        }
        if (found) {
            myCommittees = temp;
            commCount--;
        }
    }

    @Override
    public int compareTo(Lecturer other) {
        if (other == null) return 1;

        int thisArticles = 0;
        int otherArticles = 0;

        // בדיקה האם האובייקט הנוכחי (this) הוא Doctor או Professor
        if (this instanceof Doctor) {
            thisArticles = ((Doctor) this).getNumOfArticles();
        }

        // בדיקה האם האובייקט השני (other) הוא Doctor או Professor
        if (other instanceof Doctor) {
            otherArticles = ((Doctor) other).getNumOfArticles();
        }

        // השוואה בין כמויות המאמרים
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
        if (commCount > 0) {
            info += "- Committees: ";
            for (int i = 0; i < commCount; i++) {
                info += myCommittees[i].getCommitteeName() + (i < commCount - 1 ? ", " : "");
            }
        } else {
            info += "- Committees: None";
        }
        return info;
    }
}