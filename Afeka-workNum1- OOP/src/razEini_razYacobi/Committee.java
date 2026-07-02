package razEini_razYacobi;

public class Committee implements Comparable, Cloneable {
    private String committee_name;
    private Lecturer[] lecturers_Array;
    private Lecturer chairman;
    private int lecturerCount = 0;
    private int compareMode = 1;

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

    public Committee(Committee other, boolean isClone) {
        if (other != null) {
            if (isClone) {
                setCommitteeName("new-" + other.committee_name);
            } else {
                setCommitteeName(other.committee_name);
            }
            setLecturers(other.lecturers_Array);
            this.lecturerCount = other.lecturerCount;
            this.chairman = other.chairman;
            this.compareMode = other.compareMode;
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
            throw new IllegalArgumentException("Error: Committee name cannot be empty.");
        }
    }

    public void setLecturers(Lecturer[] lecturers) {
        if (lecturers != null) {
            this.lecturers_Array = new Lecturer[lecturers.length * 2];
            this.lecturerCount = 0;
            for (int i = 0; i < lecturers.length; i++) {
                if (lecturers[i] != null) {
                    this.lecturers_Array[this.lecturerCount++] = lecturers[i];
                }
            }
        } else {
            this.lecturers_Array = new Lecturer[1];
            this.lecturerCount = 0;
        }
    }

    public void setChairman(Lecturer chairman) {
        if (chairman == null || chairman.getDegree() == null) {
            throw new IllegalArgumentException("Error: Invalid Chairman.");
        }
        String degree = chairman.getDegree().name();
        if (degree.equals("DR") || degree.equals("PROFESSOR")) {
            chairman.addCommittee(this);
            this.chairman = chairman;
        } else {
            throw new IllegalArgumentException("Error: Chairman must be a DR or PROFESSOR.");
        }
    }

    public void setCompareMode(int compareMode){
        this.compareMode = compareMode;
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
                throw new IllegalArgumentException("Error: Cannot delete " + lecturer.getName() + " because they are currently the Chairman of this committee.");
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
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Committee other = (Committee) obj;

        if (this.committee_name == null) {
            return other.committee_name == null;
        }
        return this.committee_name.equals(other.committee_name);
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

    public int sumOfArticles() {
        int sum = 0;
        for (int i = 0; i < lecturerCount; i++) {
            if (lecturers_Array[i] instanceof Doctor) {
                sum += ((Doctor) lecturers_Array[i]).getNumOfArticles();
            }
        }
        return sum;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Committee other)) {
            throw new ClassCastException("Object must be of type Committee");
        }

        if (compareMode == 1)
            return Integer.compare(this.lecturerCount, other.lecturerCount);
        return Integer.compare(this.sumOfArticles(), other.sumOfArticles());
    }

    @Override
    public Committee clone() {
        try {
            Committee clone = (Committee) super.clone();
            String newName = "new " + clone.getCommitteeName();
            clone.setCommitteeName(newName);
            clone.lecturers_Array = new Lecturer[this.lecturers_Array.length];
            for (int i = 0; i < this.lecturers_Array.length; i++) {
                if (this.lecturers_Array[i] != null) {
                    if (this.lecturers_Array[i] instanceof Professor) {
                        clone.lecturers_Array[i] = new Professor((Professor) this.lecturers_Array[i]);
                    } else if (this.lecturers_Array[i] instanceof Doctor) {
                        clone.lecturers_Array[i] = new Doctor((Doctor) this.lecturers_Array[i]);
                    } else {
                        clone.lecturers_Array[i] = new Lecturer(this.lecturers_Array[i]);
                    }
                }
            }
            if (this.chairman instanceof Professor) {
                clone.chairman = new Professor((Professor) this.chairman);
            } else if (this.chairman instanceof Doctor) {
                clone.chairman = new Doctor((Doctor) this.chairman);
            } else {
                clone.chairman = (this.chairman != null) ? new Lecturer(this.chairman) : null;
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}