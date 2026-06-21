package razEini_razYacobi;

public class Professor extends Doctor {
    private String institution;

    public Professor() {
        super();
        this.institution = "Unknown";
    }

    public Professor(String name, String id, int salary, Department department, Degree degree, String institution) {
        super(name, id, salary, department, degree);
        setInstitution(institution);
    }

    public Professor(Professor other) {
        super(other);
        if (other != null) {
            this.institution = other.institution;
        }
    }

    public void setInstitution(String institution) {
        this.institution = (institution != null) ? institution : "Unknown";
    }

    public String getInstitution() {
        return this.institution;
    }

    @Override
    public String toString() {
        return super.toString() + "\n- Granting Institution: " + institution;
    }
}