package employees;

public class Administrative extends Employee {

    public Administrative(String name, int assignedWorkHours, int salary) {
        super(name, assignedWorkHours, salary);
    }

    @Override
    public void work(){
        System.out.printf("El administrativo %s esta trabajando %d por %d al mes", this.name, this.assignedWorkHours, this.salary);
    }
}
