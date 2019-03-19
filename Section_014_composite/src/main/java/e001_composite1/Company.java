package e001_composite1;

public class Company {
    public static void main(String[] args) {
        Employee dev1 = new Developer("Josh", 100, "Pro developer");
        Employee dev2 = new Developer("Jane", 101, "Jnr developer");

        Directory engDirectory = new Directory();
        engDirectory.addEmployee(dev1);
        engDirectory.addEmployee(dev2);

        Employee man1 = new Manager("Jen", 200, "SEO Manager");
        Employee man2 = new Manager("David", 201, "Linux Manager");

        Directory accDirectory = new Directory();
        accDirectory.addEmployee(man1);
        accDirectory.addEmployee(man2);

        Directory companyDirectory = new Directory();
        companyDirectory.addEmployee(engDirectory);
        companyDirectory.addEmployee(accDirectory);
        companyDirectory.showEmployeeDetails();


    }
}
