package classes;

public class Administrative {
    private Lecturer[] lecturers;
    private int lecturerCount;
    private Department[] departments;
    private int departmentCount;
    private Committee[] committees;
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

    public boolean addLecturer(String name, String id, String degree, int salary, String degreeName) {
        if (isLecturerExists(name)) return false;

        if (lecturerCount == lecturers.length) resizeLecturers();

        Lecturer l = new Lecturer(name, id, degreeName, salary, null);
        l.setDegree(degree);
        lecturers[lecturerCount++] = l;
        return true;
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
}