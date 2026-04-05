package employeeApp;

public class Employee {
    private int id;
    private String fullName;
    private String email;
    private String password;
    private String[] healthplans = new String[5]; //örnekboyut

    public Employee(int id, String fullName, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public void addHealthplan(int index, String name) {
        if (index >= 0 && index < healthplans.length) {
            if (healthplans[index] == null) {
                healthplans[index] = name;
            } else {
                System.out.println("Bu index dolu.");
            }
        } else {
            System.out.println("Geçersiz index.");
        }
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + "********" + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
