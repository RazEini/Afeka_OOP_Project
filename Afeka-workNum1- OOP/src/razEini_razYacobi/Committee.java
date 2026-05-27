package razEini_razYacobi;

public class Committee {
    private String committee_name;
    private Lecturer[] lecturers_Array;
    private Lecturer chairman;
    private int lecturerCount = 0;

    public Committee() {
        setCommitteeName("General");
        this.lecturers_Array = new Lecturer[1];
        this.chairman = null;
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
            this.chairman = other.chairman;
        }
    }

    public Lecturer getChairman() {
        return this.chairman;
    }

    public String getCommitteeName() {
        return this.committee_name;
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
                chairman.addCommittee(this);
                this.chairman = chairman;
            } else {
                System.out.println("Error: Chairman must be a DR or PROFESSOR.");
            }
        } else {
            System.out.println("Error: Invalid Chairman.");
        }
    }

    public boolean isLecturerExists(String lecturerName) {
        if (this.lecturers_Array.length == 0) return false;
        for (int i = 0; i < lecturerCount; i++) {
            if (this.lecturers_Array[i] != null && this.lecturers_Array[i].getName().equals(lecturerName)) {
                return true;
            }
        }
        return false;
    }

    public void addLecturer(Lecturer lecturer) {
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
        if (this.chairman != null && lecturer != null) {
            if (this.chairman.getName().equalsIgnoreCase(lecturer.getName())) {
                System.out.println("Error: Cannot delete " + lecturer.getName() + " because they are currently the Chairman of this committee.");
                return;
            }
        }

        Lecturer[] temp = new Lecturer[lecturers_Array.length];
        int j = 0;
        boolean removed = false;

        for (int i = 0; i < lecturerCount; i++) {
            if (lecturers_Array[i] != null) {
                if (!lecturers_Array[i].getName().equalsIgnoreCase(lecturer.getName())) {
                    temp[j] = lecturers_Array[i];
                    j++;
                } else {
                    removed = true;
                }
            }
        }

        lecturers_Array = temp;

        if (removed) {
            lecturerCount--;
        }
    }

    @Override
    public String toString() {
        String chairName = (chairman != null && chairman.getName() != null) ? chairman.getName() : "None";
        String info = "Committee: " + committee_name + "\n" +
                "Chairman: " + chairName + "\n" +
                "Members List:\n";

        if (lecturerCount == 0) {
            info += "  - No members assigned yet.\n";
        } else {
            for (int i = 0; i < lecturerCount; i++) {
                if (lecturers_Array[i] != null) {
                    info += "  - " + lecturers_Array[i].getName();
                    if (chairman != null && lecturers_Array[i].getName().equalsIgnoreCase(chairman.getName())) {
                        info += " (Committee Chairman)";
                    }
                    info += "\n";
                }
            }
        }
        return info;
    }
}