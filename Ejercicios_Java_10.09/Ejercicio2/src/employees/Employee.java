package employees;

public class Employee {
    protected String name;
    protected int assignedWorkHours;
    protected int salary;

    Employee(String name, int assignedWorkHours, int salary) {
        this.name = name;
        this.assignedWorkHours = assignedWorkHours;
        this.salary = salary;
    }

    public void work(){
        System.out.printf("El empleado %s esta trabajando%n", this.name);
    }
}
