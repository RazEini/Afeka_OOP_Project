package classes;

import java.util.Locale;

public class Lecturer {
    public String name;
    public String id;
    public enum degree {
       bachelor_degree , master_degree, DR, Professor
    }
    private degree lecturerDegree;
    public String degreeName;
    public int salary;
    public Department department;

    public Lecturer(){
        this.name = "";
        this.id = "";;
        this.degreeName = "";
        this.salary = 0;
        department = new Department();
    }
    public Lecturer(String name, String id, String degreeName, int salary){
        this(name, id,  degreeName, salary, new Department());
    }

    public Lecturer(String name, String id, String degreeName, int salary, Department department){
        this.name = name;
        this.id = id;
        this.degreeName = degreeName;
        this.salary = salary;
        this.department = new Department(department);
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setDegree(String lecturerDegree) {
        this.lecturerDegree = degree.valueOf(lecturerDegree.toUpperCase());
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
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

    public degree getDegree(){
        return this.lecturerDegree;
    }

    public String getDegreeName(){
        return this.degreeName;
    }

    public Department getDepartment(){
        return this.department;
    }

}
