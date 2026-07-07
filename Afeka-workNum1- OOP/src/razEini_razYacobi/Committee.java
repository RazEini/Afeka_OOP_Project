package razEini_razYacobi;

import java.io.Serializable;
import java.util.ArrayList;

public class Committee<T extends Lecturer.Degree> implements Comparable<Committee>, Cloneable , Serializable {
    private String committee_name;
    private ArrayList<Lecturer> lecturers_Array;
    private Lecturer chairman;
    private int compareMode = 1;
    private T committeeDegree;

    public Committee(T degree) throws AdministrativeException {
        this.committeeDegree = degree;
        setCommitteeName("General");
        this.lecturers_Array = new ArrayList<>();
        this.chairman = null;
    }

    public Committee(String name, Lecturer[] lecturers, Lecturer chairman, T degree) throws AdministrativeException {
        this.committeeDegree = degree;
        setCommitteeName(name);
        setLecturers(lecturers);
        setChairman(chairman);
    }

    public Committee(Committee<T> other, boolean isClone) throws AdministrativeException {
        if (other == null) {
            throw new AdministrativeException("Error: Cannot copy a null committee.");
        }

        this.committeeDegree = other.committeeDegree;

        if (isClone) {
            setCommitteeName("new-" + other.committee_name);
        } else {
            setCommitteeName(other.committee_name);
        }

        this.lecturers_Array = new ArrayList<>(other.lecturers_Array);
        this.chairman = other.chairman;
        this.compareMode = other.compareMode;
    }

    public Lecturer getChairman() {
        return this.chairman;
    }

    public String getCommitteeName() {
        return this.committee_name;
    }

    public void setCommitteeName(String name) throws AdministrativeException {
        if (name != null && !name.trim().isEmpty()) {
            this.committee_name = name;
        } else {
            throw new AdministrativeException("Error: Committee name cannot be empty.");
        }
    }

    public void setLecturers(Lecturer[] lecturers) {
        this.lecturers_Array = new ArrayList<>();
        if (lecturers != null) {
            for (Lecturer l : lecturers) {
                if (l != null && l.getDegree().name().equalsIgnoreCase(this.committeeDegree.name())) {
                    this.lecturers_Array.add(l);
                }
            }
        }
    }

    public void setChairman(Lecturer chairman) throws AdministrativeException {
        if (chairman == null || chairman.getDegree() == null) {
            throw new AdministrativeException("Error: Invalid Chairman.");
        }
        if (chairman.getDegree() != Lecturer.Degree.DR && chairman.getDegree() != Lecturer.Degree.PROFESSOR) {
            throw new AdministrativeException("Error: Chairman must be a DR or PROFESSOR.");
        }
        chairman.addCommittee(this);
        this.chairman = chairman;
    }

    public void setCompareMode(int compareMode){
        this.compareMode = compareMode;
    }

    public boolean isLecturerExists(String lecturerName) {
        for (Lecturer l : lecturers_Array) {
            if (l != null && l.getName().equals(lecturerName)) {
                return true;
            }
        }
        return false;
    }

    public void addLecturer(Lecturer lecturer) throws AdministrativeException {
        if (lecturer == null) throw new AdministrativeException("Error: Cannot add a null lecturer.");
        if (isLecturerExists(lecturer.getName())) throw new AdministrativeException("Error: Lecturer " + lecturer.getName() + " already exists in this committee.");
        if (lecturer.getDegree() != this.committeeDegree) {
            throw new AdministrativeException("Error: Lecturer " + lecturer.getName() + " does not have the required degree for this committee (" + this.committeeDegree + ").");
        }
        lecturers_Array.add(lecturer);
    }

    public void deleteLecturer(Lecturer lecturer) throws AdministrativeException {
        if (lecturer == null) throw new AdministrativeException("Error: Lecturer cannot be null.");
        if (this.chairman != null) {
            if (this.chairman.getName().equalsIgnoreCase(lecturer.getName())) {
                throw new AdministrativeException("Error: Cannot delete " + lecturer.getName() + " because they are currently the Chairman of this committee.");
            }
        }

        lecturers_Array.removeIf(l -> l != null && l.getName().equalsIgnoreCase(lecturer.getName()));
    }

    public int sumOfArticles() {
        int sum = 0;
        for (Lecturer l : lecturers_Array) {
            if (l instanceof Doctor) {
                sum += ((Doctor) l).getNumOfArticles();
            }
        }
        return sum;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Committee<T> other = (Committee<T>) obj;

        if (this.committee_name == null) {
            return other.committee_name == null;
        }
        return this.committee_name.equals(other.committee_name);
    }

    @Override
    public int compareTo(Committee o) {
        if (compareMode == 1)
            return Integer.compare(this.lecturers_Array.size(), o.lecturers_Array.size());
        return Integer.compare(this.sumOfArticles(), o.sumOfArticles());
    }

    @Override
    public Committee<T> clone() {
        try {
            Committee<T> clone = (Committee<T>) super.clone();
            String newName = "new-" + clone.getCommitteeName();
            try {
                clone.setCommitteeName(newName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            clone.lecturers_Array = new ArrayList<>();
            for (Lecturer l : this.lecturers_Array) {
                if (l != null) {
                    if (l instanceof Professor) {
                        clone.lecturers_Array.add(new Professor((Professor) l));
                    } else if (l instanceof Doctor) {
                        clone.lecturers_Array.add(new Doctor((Doctor) l));
                    } else {
                        clone.lecturers_Array.add(new Lecturer(l));
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

    @Override
    public String toString() {
        String chairName = (chairman != null && chairman.getName() != null) ? chairman.getName() : "None";
        String info = "Committee: " + committee_name + "\n" +
                "Chairman: " + chairName + "\n" +
                "Members List:\n";

        if (lecturers_Array.isEmpty()) {
            info += "  - No members assigned yet.\n";
        } else {
            for (Lecturer l : lecturers_Array) {
                if (l != null) {
                    info += "  - " + l.getName();
                    if (chairman != null && l.getName().equalsIgnoreCase(chairman.getName())) {
                        info += " (Committee Chairman)";
                    }
                    info += "\n";
                }
            }
        }
        return info;
    }
}