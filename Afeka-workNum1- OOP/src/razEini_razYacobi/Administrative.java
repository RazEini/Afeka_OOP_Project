package razEini_razYacobi;

import razEini_razYacobi.Lecturer.Degree;

public class Administrative {
    private Lecturer[] lecturers;
    private int lecturerCount;
    private Department[] departments;
    private int departmentCount;
    public Committee[] committees;
    private int committeeCount;
    private final String collegeName;

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

    public boolean isLecturersIdExists(String id) {
        for (int i = 0; i < lecturerCount; i++) {
            if (lecturers[i].getId().equalsIgnoreCase(id)) return true;
        }
        return false;
    }

    public static boolean isValidID(String idStr) {
        return idStr != null && idStr.length() == 9;
    }

    public void addLecturer(String name, String id, String degreeStr, int salary, String institution) {
        if (lecturerCount == lecturers.length) resizeLecturers();

        Degree degree = Degree.BACHELOR_DEGREE;
        String upper = degreeStr.toUpperCase().replace(" ", "_");
        for (Degree d : Degree.values()) {
            if (d.name().equals(upper)) {
                degree = d;
                break;
            }
        }

        Lecturer l;
        if (degree == Degree.PROFESSOR) {
            l = new Professor(name, id, salary, null, degree, institution);
        } else if (degree == Degree.DR) {
            l = new Doctor(name, id, salary, null, degree);
        } else {
            l = new Lecturer(name, id, salary, null, degree);
        }

        lecturers[lecturerCount++] = l;
    }

    public Lecturer findLecturerByName(String name) {
        for (int i = 0; i < lecturerCount; i++) {
            if (lecturers[i].getName().equalsIgnoreCase(name)) return lecturers[i];
        }
        return null;
    }

    public Committee findCommitteeByName(String name) {
        for (int i = 0; i < committeeCount; i++) {
            if (committees[i].getCommitteeName().equalsIgnoreCase(name)) return committees[i];
        }
        return null;
    }

    public double getAverageSalary() {
        if (lecturerCount == 0) return 0;
        double sum = 0;
        for (int i = 0; i < lecturerCount; i++) sum += lecturers[i].getSalary();
        return sum / lecturerCount;
    }

    public double getAverageSalaryByDepartment(String department) {
        double sum = 0;
        int countInDepartment = 0;
        for (int i = 0; i < lecturerCount; i++) {
            if (lecturers[i].getDepartment() != null && lecturers[i].getDepartment().getDepartmentName().equalsIgnoreCase(department)) {
                sum += lecturers[i].getSalary();
                countInDepartment++;
            }
        }
        if (countInDepartment == 0) return 0;
        return sum / countInDepartment;
    }

    public String getAllLecturersFullData() {
        if (lecturerCount == 0) return "No lecturers registered.";
        String result = "";
        for (int i = 0; i < lecturerCount; i++) {
            result += (lecturers[i].toString() + "\n\n");
        }
        return result;
    }

    public String getAllCommitteesFullData() {
        if (committeeCount == 0) return "No committees registered.";
        String result = "";
        for (int i = 0; i < committeeCount; i++) {
            result += committees[i].toString() + "\n";
        }
        return result;
    }

    public boolean isCommitteeExists(String name) {
        for (int i = 0; i < committeeCount; i++) {
            if (committees[i].getCommitteeName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    public void addCommittee(Committee c) throws AdministrativeException {
        if (c != null && c.getChairman() != null) {
            if (ChairmanExists(c.getChairman())) {
                throw new AdministrativeException("Error: Cannot add committee '" + c.getCommitteeName() +
                        "' because " + c.getChairman().getName() + " is already chairman of another committee.");
            }
        }

        if (committeeCount == committees.length) {
            Committee[] temp = new Committee[committees.length * 2];
            for (int i = 0; i < committeeCount; i++) temp[i] = committees[i];
            committees = temp;
        }
        committees[committeeCount++] = c;
    }

    public void addLecturerToCommittee(String committeeName, String lecturerName) throws AdministrativeException {
        Committee targetCommittee = findCommitteeByName(committeeName);
        Lecturer l = findLecturerByName(lecturerName);

        if (targetCommittee == null && l == null) {
            throw new AdministrativeException("Error: Both committee \"" + committeeName + "\" and lecturer \"" + lecturerName + "\" do not exist.");
        } else if (targetCommittee == null) {
            throw new AdministrativeException("Error: Committee \"" + committeeName + "\" does not exist.");
        } else if (l == null) {
            throw new AdministrativeException("Error: Lecturer \"" + lecturerName + "\" does not exist.");
        }

        if (targetCommittee.getChairman() != null && targetCommittee.getChairman().getName().equalsIgnoreCase(lecturerName)) {
            throw new AdministrativeException("Error: Lecturer " + lecturerName + " is already the chairman of this committee.");
        }

        if (targetCommittee.isLecturerExists(lecturerName)) {
            throw new AdministrativeException("Error: Lecturer " + lecturerName + " already exists in this committee.");
        }

        for (int i = 0; i < committeeCount; i++) {
            if (committees[i].isLecturerExists(lecturerName)) {
                throw new AdministrativeException("Error: Lecturer " + lecturerName + " is already a member of committee '" + committees[i].getCommitteeName() + "'.");
            }
        }

        targetCommittee.addLecturer(l);
        l.addCommittee(targetCommittee);
    }

    public void deleteLecturerFromCommittee(String committeeName, String lecturerName) throws AdministrativeException {
        Committee targetCommittee = findCommitteeByName(committeeName);
        Lecturer l = findLecturerByName(lecturerName);

        if (targetCommittee == null && l == null) {
            throw new AdministrativeException("Error: Both committee \"" + committeeName + "\" and lecturer \"" + lecturerName + "\" do not exist.");
        } else if (targetCommittee == null) {
            throw new AdministrativeException("Error: Committee \"" + committeeName + "\" does not exist.");
        } else if (l == null) {
            throw new AdministrativeException("Error: Lecturer \"" + lecturerName + "\" does not exist.");
        }

        if (targetCommittee.getChairman() != null && targetCommittee.getChairman().getName().equalsIgnoreCase(lecturerName)) {
            throw new AdministrativeException("Error: Cannot remove " + lecturerName + " because they are the Chairman of " + committeeName + ".");
        }

        if (targetCommittee.isLecturerExists(lecturerName)) {
            targetCommittee.deleteLecturer(l);
            l.removeCommittee(committeeName);
        } else {
            throw new AdministrativeException("Error: Lecturer " + lecturerName + " is not a member of committee " + committeeName + ".");
        }
    }

    public boolean ChairmanExists(Lecturer l) {
        if (l == null || l.getName() == null) return false;
        for (int i = 0; i < committeeCount; i++) {
            Lecturer currentChair = committees[i].getChairman();
            if (currentChair != null && currentChair.getName() != null &&
                    currentChair.getName().equalsIgnoreCase(l.getName())) {
                return true;
            }
        }
        return false;
    }

    public void updateChairmanCommittee(String committeeName, String chairmanName) throws AdministrativeException {
        Lecturer newChairman = findLecturerByName(chairmanName);
        if (newChairman == null) {
            throw new AdministrativeException("Error: Lecturer " + chairmanName + " does not exist.");
        }

        if (ChairmanExists(newChairman)) {
            throw new AdministrativeException("Error: Lecturer " + chairmanName + " is already chairman of a committee.");
        }

        Committee targetCommittee = findCommitteeByName(committeeName);
        if (targetCommittee == null) {
            throw new AdministrativeException("Error: Committee " + committeeName + " does not exist.");
        }

        Degree degree = newChairman.getDegree();
        if (degree == Degree.DR || degree == Degree.PROFESSOR) {
            Lecturer originalOldChairman = targetCommittee.getChairman();
            if (originalOldChairman != null) {
                originalOldChairman.removeCommittee(committeeName);
            }

            if (targetCommittee.isLecturerExists(chairmanName)) {
                targetCommittee.deleteLecturer(newChairman);
                newChairman.removeCommittee(committeeName);
            }

            targetCommittee.setChairman(newChairman);
        } else {
            throw new AdministrativeException("Error: Lecturer " + chairmanName + " cannot be a chairman because they are not a DR or PROFESSOR.");
        }
    }

    public boolean isDepartmentExists(String departmentName) {
        for (int i = 0; i < departmentCount; i++) {
            if (departments[i].getDepartmentName().equalsIgnoreCase(departmentName)) {
                return true;
            }
        }
        return false;
    }

    public void AddDepartment(Department department) {
        if (department == null) return;
        if (isDepartmentExists(department.getDepartmentName())) return;
        if (departmentCount == departments.length) {
            Department[] temp = new Department[departments.length * 2];
            for (int i = 0; i < departmentCount; i++) temp[i] = departments[i];
            departments = temp;
        }
        departments[departmentCount++] = department;
    }

    public void addLecturerToDepartment(String departmentName, String lecturerName) throws AdministrativeException {
        Lecturer l = findLecturerByName(lecturerName);
        if (l == null) {
            throw new AdministrativeException("Error: Lecturer " + lecturerName + " does not exist.");
        }

        Department targetDept = null;
        for (int i = 0; i < departmentCount; i++) {
            if (departments[i].getDepartmentName().equalsIgnoreCase(departmentName)) {
                targetDept = departments[i];
                break;
            }
        }

        if (targetDept == null) {
            throw new AdministrativeException("Error: Department " + departmentName + " does not exist.");
        }

        if (targetDept.isLecturerExists(lecturerName)) {
            throw new AdministrativeException("Error: Lecturer " + lecturerName + " already exists in this department.");
        }

        if (l.getDepartment() != null) {
            throw new AdministrativeException("Error: Lecturer " + lecturerName + " already belongs to another department: "
                    + l.getDepartment().getDepartmentName());
        }

        Lecturer[] currentLecturers = targetDept.getLecturers();
        int count = 0;
        for (Lecturer currentLecturer : currentLecturers) {
            if (currentLecturer != null && currentLecturer.getName() != null) {
                count++;
            }
        }

        Lecturer[] newLecturersArray = new Lecturer[count + 1];
        int index = 0;
        for (Lecturer currentLecturer : currentLecturers) {
            if (currentLecturer != null && currentLecturer.getName() != null) {
                newLecturersArray[index++] = currentLecturer;
            }
        }

        newLecturersArray[count] = l;
        targetDept.setLecturers(newLecturersArray);
        l.setDepartment(targetDept);
    }

    public void addArticleToLecturer(String name, String article) throws AdministrativeException {
        Lecturer l = findLecturerByName(name);
        if (l == null) {
            throw new AdministrativeException("Error: Lecturer " + name + " does not exist.");
        }

        if (l instanceof Doctor) {
            ((Doctor) l).addArticles(article);
        } else {
            throw new AdministrativeException("Error: Lecturer " + name + " is not DR or PROFESSOR. Cannot add articles.");
        }
    }
}