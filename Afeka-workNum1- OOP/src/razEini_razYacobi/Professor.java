package razEini_razYacobi;

import java.io.Serializable;

public class Professor extends Doctor implements Serializable {
    private String institution;

    public Professor() {
        super();
        this.institution = "Unknown";
    }

    public Professor(String name, String id, int salary, Department department, Degree degree, String institution) throws AdministrativeException {
        super(name, id, salary, department, degree);
        setInstitution(institution);
    }

    public Professor(Professor other) {
        super(other);
        if (other != null) {
            this.institution = other.institution;
        }
    }

    public void setInstitution(String institution) throws AdministrativeException {
        if (institution == null || institution.trim().isEmpty()) throw new AdministrativeException("Error: Institution name cannot be null or empty.");
        this.institution = institution;
    }

    public String getInstitution() {
        return this.institution;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj) || !(obj instanceof Professor other)) {
            return false;
        }
        if (institution == null) {
            return other.institution == null;
        }
        return institution.equals(other.institution);
    }

    @Override
    public String toString() {
        return super.toString() + "\n- Granting Institution: " + institution;
    }
}