package classes;

public class Lecturer {
    public String name;
    public String id;
    public enum  degree {
       bachelor_degree , master_degree, DR, Professor
    }
    public String degreeName;
    public Department department;

    public Lecturer(){
        this.name = "";
        this.id = "";
        this.degreeName = "";
        department = new Department();
    }

    public Lecturer(String name, String id, String degreeName, Department department){
        this.name = name;
        this.id = id;
        this.degreeName = degreeName;
        department = new Department(department);
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setDegree(String degree) {
        this.degreeName = degree;
    }

    public void SetDepartment(Department department){
        this.department = department;
    }

    public String getName(){
        return this.name;
    }

    public String getId(){
        return this.id;
    }

    public String getDegreeName(){
        return this.degreeName;
    }

    public Department getDepartment(){
        return this.department;
    }

}
