package e002_composite2.e001_composite1;

import java.util.ArrayList;
import java.util.List;

public class Manager implements Employee {
    private List<Employee> employeeList = new ArrayList<>();
    private String name;
    private double salary;

    public Manager(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public void showEmployeeDetails() {
        System.out.println("----------------");
        System.out.println("Name: " + getName());
        System.out.println("Salary: " + getSalary());
        System.out.println("----------------");

        for (Employee emp: employeeList){
            emp.showEmployeeDetails();
        }
    }

    public void addEmployee(Employee emp){
        employeeList.add(emp);
    }

    public void removeEmployee(Employee emp){
        employeeList.remove(emp);
    }

    public Employee getChild(int i){
        return employeeList.get(i);
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}
