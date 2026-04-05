package employeeApp;

/**
 * Uygulamanın amacı Java'da nesne tabanlı programlamanın temellerinin oluşturulmasıdır.
 *
 */
public class Main
{

    /**
     * Uygulamadaki tüm işlemler bu metotda yapılmalı
     */
    private static void workWithData ()
    {
        Company company = new Company(1, "XYZ Corp", 500000);
        Employee employee = new Employee(1, "John Doe", "john.doe@example.com", "password123");
        Healthplan healthPlan = new Healthplan(1, "Basic Plan", Plan.BASIC);

        System.out.println(company.toString());
        System.out.println(employee.toString());
        System.out.println(healthPlan.toString());

        company.addEmployee(0, "Jane Doe");
        employee.addHealthplan(0, "Premium Plan");
    }

    /**
     * Bu projenin ana(main) metodu. Java uygulamalarında main metot küçük bir metot olur.
     * Bir Java programınd ilgili işlemlerin çoğunluğu başka metodlarda yapılır.
     * Daha sonra ana metod diğer metodları çağırır. Böylece ana metodun içi kalabalık bir hale gelmemiş olur.

     *
     * @param args bu uygulamada kullanılmamaktadır. Command Line üzerinden argüman alan
     *             bir uygulamamız olsaydı kullanılacaktı.
     */
    public static void main(String[] args)
    {
        workWithData();
    }
}
