package Threads;


import employees.Administrative;
import employees.Comercial;
import employees.Employee;

public class LoadData extends Thread{
    Employee[] employees;
    public LoadData(String name, Employee[] employees){
        super(name);
        this.employees = employees.clone();
    }

    @Override
    public void run(){
        Comercial comercialEmployee = new Comercial("Jorge", 20, 400);
        Administrative administrativeEmployee = new Administrative("Juan", 100, 10000);
        this.employees[0] = comercialEmployee;
        this.employees[1] = administrativeEmployee;
    }

    public Employee[] getEmployees() {
        return this.employees;
    }
}
