package classes;

public class Administrative {
    private Lecturer[] lecturers;
    private int lecturerCount;
    private Department[] departments;
    private int departmentCount;
    public Committee[] committees;
    private int committeeCount;
    private String collegeName;

    public Administrative(String collegeName) {
        this.collegeName = collegeName;
        this.lecturers = new Lecturer[1];
        this.departments = new Department[1];
        this.committees = new Committee[1];
        this.lecturerCount = 0;
        this.departmentCount = 0;
        this.committeeCount = 0;
    }

    public String getCollegeName() { return this.collegeName; }

    private void resizeLecturers() {
        Lecturer[] newArr = new Lecturer[lecturers.length * 2];
        for (int i = 0; i < lecturerCount; i++) newArr[i] = lecturers[i];
        lecturers = newArr;
    }

    public boolean isLecturerExists(String name) {
        for (int i = 0; i < lecturerCount; i++) {
            if (lecturers[i].getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    public void addLecturer(String name, String id, String degree, int salary, String degreeName) {
        if (lecturerCount == lecturers.length) resizeLecturers();

        Lecturer l = new Lecturer(name, id,degreeName, salary, null);
        l.setDegree(degree);
        lecturers[lecturerCount++] = l;
    }

    public Lecturer findLecturerByName(String name) {
        for (int i = 0; i < lecturerCount; i++) {
            if (lecturers[i].getName().equalsIgnoreCase(name)) return lecturers[i];
        }
        return null;
    }

    public boolean addDepartment(String deptName) {
        for (int i = 0; i < departmentCount; i++) {
            if (departments[i].getDepartmentName().equalsIgnoreCase(deptName)) return false;
        }

        if (departmentCount == departments.length) {
            Department[] newArr = new Department[departments.length * 2];
            for (int i = 0; i < departmentCount; i++) newArr[i] = departments[i];
            departments = newArr;
        }

        Department d = new Department();
        d.setDepartmentName(deptName);
        departments[departmentCount++] = d;
        return true;
    }

    public double getAverageSalary() {
        if (lecturerCount == 0) return 0;
        double sum = 0;
        for (int i = 0; i < lecturerCount; i++) sum += lecturers[i].getSalary();
        return sum / lecturerCount;
    }

    public String getAllLecturersFullData() {
        if (lecturerCount == 0) return "No lecturers registered.";
        String result = "";
        for (int i = 0; i < lecturerCount; i++) {
            result += lecturers[i].toString() + "\n";
        }
        return result;
    }

    public boolean isCommitteeExists(String name) {
        for (int i = 0; i < committeeCount; i++) {
            if (committees[i].getCommitteeName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    public void addCommittee(Committee c) {
        if (committeeCount == committees.length) {
            Committee[] temp = new Committee[committees.length * 2];
            for (int i = 0; i < committeeCount; i++) temp[i] = committees[i];
            committees = temp;
        }
        committees[committeeCount++] = c;
    }

    public boolean addLecturerToCommittee(String committeeName, String lecturerName) {

        Lecturer l = findLecturerByName(lecturerName);

        if (l == null) {
            return false;
        }

        for (int i = 0; i < committeeCount; i++) {
            if (committees[i].getCommitteeName().equalsIgnoreCase(committeeName)) {
                String degree = l.getDegree().name();
                if (!degree.equalsIgnoreCase("DR") && !degree.equalsIgnoreCase("Professor")) {
                    if (committees[i].isLecturerExitst(lecturerName)) {
                        System.out.println("Lecturer " + lecturerName + " is already exitst.");
                        return false;
                    }

                    committees[i].addLecturer(l);
                    System.out.println(committees[i].toString());
                    return true;

                } else {
                    System.out.println("Lecturer " + lecturerName + " is has a DR or Professor.");
                    return false;
                }
            }
        }
        System.out.println("Lecturer " + lecturerName + " is not exitst.");
        return false;
    }
}