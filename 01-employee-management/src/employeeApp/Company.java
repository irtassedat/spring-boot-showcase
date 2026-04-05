package employeeApp;

public class Company {
    private int id;
    private String name;
    private double giro;
    private String[] developerNames = new String[10]; // Sadece bir örnek boyut

    public Company(int id, String name, double giro) {
        this.id = id;
        this.name = name;
        this.giro = giro;
    }

    public void addEmployee(int index, String name) {
        if (index >= 0 && index < developerNames.length) {
            if (developerNames[index] == null) {
                developerNames[index] = name;
            } else {
                System.out.println("Bu index dolu.");
            }
        } else {
            System.out.println("Geçersiz index.");
        }
    }

    // Getter ve Setter metodları

    // toString() metodu
    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", giro=" + giro +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
