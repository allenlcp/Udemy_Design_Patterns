package e002_composite2.e001_composite1;

public class Company {
    public static void main(String[] args) {
        Employee dev1 = new Developer("Josh", 100, "Pro developer");
        Employee dev2 = new Developer("Jane", 101, "Jnr developer");

        Manager engManager = new Manager("Mike", 2000);
        engManager.addEmployee(dev1);
        engManager.addEmployee(dev2);


        Manager generalManager = new Manager("Mark", 30000);
        Employee dev3 = new Developer("Tod", 103, "Intermediate");
        generalManager.addEmployee(dev3);
        generalManager.addEmployee(engManager);

        generalManager.showEmployeeDetails();


    }
}
