package employees;

public class Comercial extends  Employee {

    public Comercial(String name, int assignedWorkHours, int salary) {
        super(name, assignedWorkHours, salary);
    }

    @Override
    public void work(){
        System.out.printf("El Comercial %s esta trabajando %d por %d al mes%n", this.name, this.assignedWorkHours, this.salary);
    }
}
