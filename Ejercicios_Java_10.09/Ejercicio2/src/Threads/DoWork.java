package Threads;

import employees.Employee;

public class DoWork extends Thread {
    Employee[] employees;
    public DoWork(String name, Employee[] employees){
        super(name);
        this.employees = employees.clone();
    }

    @Override
    public void run(){
        for(Employee employee : this.employees){
           employee.work();
        }
    }
}
