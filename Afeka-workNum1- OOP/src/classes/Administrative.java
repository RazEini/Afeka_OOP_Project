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

    public boolean isLecturersIdExists(String id) {
        for (int i = 0; i < lecturerCount; i++) {
            if (lecturers[i].getId().equalsIgnoreCase(id)) return true;
        }
        return false;
    }

    public static boolean isValidIsraeliID(String idStr) {
        if (idStr == null || idStr.length() != 9 || !idStr.matches("\\d+")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(idStr.charAt(i));
            int weight = (i % 2 == 0) ? 1 : 2;
            int stepProduct = digit * weight;

            if (stepProduct > 9) {
                stepProduct -= 9;
            }
            sum += stepProduct;
        }
        return (sum % 10 == 0);
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
            if (lecturers[i].getDepartment().equals(department)) {
                sum += lecturers[i].getSalary();
                countInDepartment++;
            }
        }

        if (countInDepartment == 0) return 0;

        return sum / countInDepartment;
    }

    // מתוקן: הוספת ריווח אסתטי וברור בין מרצה למרצה בהדפסה המלאה
    public String getAllLecturersFullData() {
        if (lecturerCount == 0) return "No lecturers registered.";
        String result = "";
        for (int i = 0; i < lecturerCount; i++) {
            result += lecturers[i].toString() + "\n";
            result += "\n"; // קו מפריד וריווח
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
        if (c != null && c.getChairman() != null) {
            if (ChairmanExists(c.getChairman())) {
                System.out.println("Error: Cannot add committee '" + c.getCommitteeName() +
                        "' because " + c.getChairman().getName() + " is already chairman of another committee.");
                return;
            }
        }

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
                if (committees[i].getChairman() != null && committees[i].getChairman().getName().equalsIgnoreCase(lecturerName)) {
                    System.out.println("Lecturer " + lecturerName + " is a chairman.");
                    return false;
                }

                if (committees[i].isLecturerExists(lecturerName)) {
                    System.out.println("Lecturer " + lecturerName + " is already exist.");
                    return false;
                }

                committees[i].addLecturer(l);
                l.addCommittee(committees[i]);
                System.out.println(committees[i].toString());
                return true;
            }
        }
        System.out.println("Committee " + committeeName + " does not exist.");
        return false;
    }

    public boolean deleteLecturerFromCommittee(String committeeName, String lecturerName) {
        Lecturer l = findLecturerByName(lecturerName);
        if (l == null) {
            System.out.println("Error: Lecturer " + lecturerName + " does not exist.");
            return false;
        }

        for (int i = 0; i < committeeCount; i++) {
            if (committees[i].getCommitteeName().equalsIgnoreCase(committeeName)) {

                if (committees[i].getChairman() != null && committees[i].getChairman().getName().equalsIgnoreCase(lecturerName)) {
                    System.out.println("Error: Cannot remove " + lecturerName + " because they are the Chairman of " + committeeName + ".");
                    return false;
                }

                if (committees[i].isLecturerExists(lecturerName)) {

                    committees[i].deleteLecturer(l);

                    l.removeCommittee(committeeName);

                    return true;
                } else {
                    System.out.println("Error: Lecturer " + lecturerName + " is not a member of committee " + committeeName + ".");
                    return false;
                }
            }
        }

        System.out.println("Error: Committee " + committeeName + " does not exist.");
        return false;
    }

    public boolean ChairmanExists(Lecturer l) {
        if (l == null) return false;
        for (int i = 0; i < committeeCount; i++) {
            if (committees[i].getChairman() != null &&
                    committees[i].getChairman().getName().equalsIgnoreCase(l.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean UpdateChairmanCommittee(String committeeName, String chairmanName) {
        Lecturer newChairman = findLecturerByName(chairmanName);
        if (newChairman == null) {
            System.out.println("Error: Lecturer " + chairmanName + " does not exist.");
            return false;
        }

        if (ChairmanExists(newChairman)) {
            System.out.println("Error: Lecturer " + chairmanName + " is already chairman of a committee.");
            return false;
        }

        for (int i = 0; i < committeeCount; i++) {
            if (committees[i].getCommitteeName().equalsIgnoreCase(committeeName)) {

                String degree = newChairman.getDegree().name();
                if (degree.equalsIgnoreCase("DR") || degree.equalsIgnoreCase("PROFESSOR")) {

                    Lecturer oldChairCopy = committees[i].getChairman();
                    if (oldChairCopy != null && oldChairCopy.getName() != null) {
                        Lecturer originalOldChairman = findLecturerByName(oldChairCopy.getName());
                        if (originalOldChairman != null) {
                            originalOldChairman.removeCommittee(committeeName);
                        }
                    }

                    if (committees[i].isLecturerExists(chairmanName)) {
                        committees[i].deleteLecturer(newChairman);
                        newChairman.removeCommittee(committeeName);
                    }

                    committees[i].setChairman(newChairman);
                    return true;
                } else {
                    System.out.println("Error: Lecturer " + chairmanName + " cannot be a chairman because they are not a DR or PROFESSOR.");
                    return false;
                }
            }
        }

        System.out.println("Error: Committee " + committeeName + " does not exist.");
        return false;
    }

    public boolean isDepartmentExists(String departmentName) {
        for (int i = 0; i < departmentCount; i++) {
            if (departments[i].getDepartmentName().equalsIgnoreCase(departmentName)) {
                return true;
            }
        }
        return false;
    }

    public boolean AddDepartment(Department department) {
        if (department == null) return false;
        if (isDepartmentExists(department.getDepartmentName())) return false;
        if (departmentCount == departments.length) {
            Department[] temp = new Department[departments.length * 2];
            for (int i = 0; i < departmentCount; i++) temp[i] = departments[i];
            departments = temp;
        }
        departments[departmentCount++] = department;
        return true;
    }

    public boolean addLecturerToDepartment(String departmentName, String lecturerName) {
        Lecturer l = findLecturerByName(lecturerName);
        if (l == null) return false;

        for (int i = 0; i < departmentCount; i++) {
            if (departments[i].getDepartmentName().equalsIgnoreCase(departmentName)) {

                Lecturer[] currentLecturers = departments[i].getLecturers();

                for (int j = 0; j < currentLecturers.length; j++) {
                    if (currentLecturers[j] != null && currentLecturers[j].getName() != null) {
                        if (currentLecturers[j].getName().equalsIgnoreCase(lecturerName)) {
                            System.out.println("Error: Lecturer " + lecturerName + " already exists in this department.");
                            return false;
                        }
                    }
                }

                int count = 0;
                for (int j = 0; j < currentLecturers.length; j++) {
                    if (currentLecturers[j] != null && currentLecturers[j].getName() != null) {
                        count++;
                    }
                }

                Lecturer[] newLecturersArray = new Lecturer[count + 1];
                int index = 0;
                for (int j = 0; j < currentLecturers.length; j++) {
                    if (currentLecturers[j] != null && currentLecturers[j].getName() != null) {
                        newLecturersArray[index++] = currentLecturers[j];
                    }
                }
                newLecturersArray[count] = l;

                departments[i].setLecturers(newLecturersArray);
                l.setDepartment(departments[i]);

                System.out.println("Successfully added lecturer " + lecturerName + " to department " + departmentName + ".");
                return true;
            }
        }
        return false;
    }
}