package razEini_razYacobi;

public class Lecturer implements Comparable<Lecturer> {
    private String lecturer_name;
    private String lecturer_id;
    private int salary;
    private String degreeName;
    private Department department;

    private Committee[] myCommittees;
    private int commCount;

    public enum Degree { BACHELOR_DEGREE, MASTER_DEGREE, DR, PROFESSOR }
    private Degree lecturerDegree;

    private String[] articles;
    private int artCount;
    private String institution;

    public Lecturer() {
        setName("Unknown");
        setId("000000000");
        this.degreeName = "Unknown";
        setSalary(0);
        this.department = null;
        this.myCommittees = new Committee[1];
        this.commCount = 0;
        this.articles = new String[1];
        this.artCount = 0;
        this.institution = "Unknown";
    }

    public Lecturer(String name, String id, String degreeName, int salary, Department department) {
        setName(name);
        setId(id);
        this.degreeName = degreeName;
        setSalary(salary);
        setDepartment(department);
        this.myCommittees = new Committee[1];
        this.commCount = 0;
        this.articles = new String[1];
        this.artCount = 0;
        this.institution = "Unknown";
    }

    public Lecturer(Lecturer other) {
        if (other != null) {
            setName(other.lecturer_name);
            setId(other.lecturer_id);
            this.degreeName = other.degreeName;
            this.lecturerDegree = other.lecturerDegree;
            setSalary(other.salary);
            this.department = other.department;
            this.commCount = other.commCount;
            this.myCommittees = new Committee[other.myCommittees.length];
            for(int i=0; i<other.commCount; i++) {
                this.myCommittees[i] = other.myCommittees[i];
            }
            this.articles = new String[other.articles.length];
            this.artCount = other.artCount;
            this.institution = other.institution;
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

    public String getName() {
        return this.lecturer_name;
    }

    public String getId() {
        return this.lecturer_id;
    }

    public Degree getDegree() {
        return this.lecturerDegree;
    }

    public int getSalary() {
        return this.salary;
    }

    public void setName(String name) {
        this.lecturer_name = (name != null) ? name : "Unknown";
    }

    public void setSalary(int salary) {
        this.salary = (salary >= 0) ? salary : 0;
    }

    public void setId(String id) {
        if (id != null && id.length() == 9) this.lecturer_id = id;
        else this.lecturer_id = "000000000";
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
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) { this.department = department; }

    public void addArticles(String articles) {
        if (this.artCount == this.articles.length) {
            String[] temp = new String[this.articles.length * 2];
            for (int i = 0; i < this.articles.length; i++) temp[i] = this.articles[i];
            this.articles = temp;
        }
        this.articles[artCount++] = articles;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getInstitution() {
        return this.institution;
    }

    public int getNumOfArticles() {
        return this.artCount;
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
    public int compareTo(Lecturer o) {
        return Integer.compare(this.getNumOfArticles(), o.getNumOfArticles());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Lecturer other = (Lecturer) obj;

        if (this.lecturer_id == null) {
            return other.lecturer_id == null;
        }
        return this.lecturer_id.equals(other.lecturer_id);
    }

    @Override
    public String toString() {
        String info = "Lecturer Details:\n" +
                "- Name: " + lecturer_name + "\n" +
                "- ID: " + lecturer_id + "\n" +
                "- Salary: " + salary + "\n" +
                "- Degree: " + (lecturerDegree != null ? lecturerDegree : "None") + "\n";

        if (department != null) {
            info += "- Department: " + department.getDepartmentName() + "\n";
        } else {
            info += "- Department: None\n";
        }

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