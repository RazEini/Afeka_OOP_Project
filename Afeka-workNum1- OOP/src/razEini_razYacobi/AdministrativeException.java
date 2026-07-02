package razEini_razYacobi;

public class AdministrativeException extends Exception {
    public AdministrativeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + getMessage();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AdministrativeException other = (AdministrativeException) obj;
        String message = getMessage();
        return message == null ? other.getMessage() == null : message.equals(other.getMessage());
    }
}