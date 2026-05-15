package classes;

public class Committee {
    private String committee_name;
    private Lecturer[] lecturers_Array;
    private Lecturer chairman;

    public Committee() {
        setCommitteeName("General");
        this.lecturers_Array = new Lecturer[0];
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
        if (this.lecturers_Array == null) return new Lecturer[0];
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

    @Override
    public String toString() {
        String chairName = (chairman != null) ? chairman.getName() : "None";
        return "Committee: " + committee_name + " | Chairman: " + chairName + " | Members: " + lecturers_Array.length;
    }
}