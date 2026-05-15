package classes;

public class Committee {
    private String committee_name;
    private Lecturer[] lecturers_Array;
    private Lecturer chairman;
    private int lecturerCount = 0;

    public Committee() {
        setCommitteeName("General");
        this.lecturers_Array = new Lecturer[1];
        this.chairman = new Lecturer();
    }

    public Committee(String name, Lecturer[] lecturers, Lecturer chairman) {
        setCommitteeName(name);
        setLecturers(lecturers);
        setChairman(chairman);
    }

    public Committee(Committee other) {
        if (other != null) {
            setCommitteeName(other.committee_name);
            setLecturers(other.lecturers_Array);
            setChairman(other.chairman);
        }
    }

    public String getCommitteeName() {
        return this.committee_name;
    }

    public Lecturer[] getLecturers() {
        if (this.lecturers_Array == null) return new Lecturer[1];
        Lecturer[] copy = new Lecturer[this.lecturers_Array.length];
        for (int i = 0; i < this.lecturers_Array.length; i++) {
            copy[i] = new Lecturer(this.lecturers_Array[i]);
        }
        return copy;
    }

    public Lecturer getChairman() {
        return new Lecturer(this.chairman);
    }

    public void setCommitteeName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.committee_name = name;
        } else {
            this.committee_name = "General Committee";
            System.out.println("Error: Committee name cannot be empty.");
        }
    }

    public void setLecturers(Lecturer[] lecturers) {
        if (lecturers != null) {
            this.lecturers_Array = new Lecturer[lecturers.length];
            for (int i = 0; i < lecturers.length; i++) {
                if (lecturers[i] != null) {
                    this.lecturers_Array[i] = new Lecturer(lecturers[i]);
                }
            }
        } else {
            this.lecturers_Array = new Lecturer[0];
        }
    }

    public void setChairman(Lecturer chairman) {
        if (chairman != null && chairman.getDegree() != null) {
            String degree = chairman.getDegree().name();
            if (degree.equals("DR") || degree.equals("PROFESSOR")) {
                this.chairman = new Lecturer(chairman);
            } else {
                System.out.println("Error: Chairman must be a DR or PROFESSOR.");
            }
        } else {
            System.out.println("Error: Invalid Chairman.");
        }
    }

    public boolean isLecturerExists(String lecturerName) {
        if  (this.lecturers_Array.length == 0) return false;
        for (int i = 0; i < this.lecturers_Array.length; i++) {
            if (this.lecturers_Array[i] == null)
                return false;
            if (this.lecturers_Array[i].getName().equals(lecturerName)) {
                return true;
            }
        }
        return false;
    }

    public void addLecturer(Lecturer lecturer) {
        System.out.println(lecturerCount + ", " + lecturers_Array.length);
        if (lecturerCount == lecturers_Array.length) {
            if (lecturers_Array.length == 0)
                lecturers_Array = new Lecturer[1];
            Lecturer[] temp = new Lecturer[lecturers_Array.length * 2];
            for (int i = 0; i < lecturerCount; i++) temp[i] = lecturers_Array[i];
            lecturers_Array = temp;
        }
        lecturers_Array[lecturerCount++] = lecturer;
    }

    public void deleteLecturer(Lecturer lecturer) {
        Lecturer[] temp = new Lecturer[lecturers_Array.length-1];
        int j = 0;
        for (int i = 0; i < lecturerCount; i++) {
            if (!lecturers_Array[i].getName().equalsIgnoreCase(lecturer.getName())) {
                temp[j] = lecturers_Array[i];
                j++;
            }
        }
        lecturers_Array = temp;
        lecturerCount--;
    }

    @Override
    public String toString() {
        String chairName = (chairman != null) ? chairman.getName() : "None";
        return "Committee: " + committee_name + " | Chairman: " + chairName + " | Members: " + lecturerCount;
    }
}