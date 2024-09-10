import Threads.DoWork;
import Threads.LoadData;
import employees.Comercial;
import employees.Employee;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        try{
            run();
        } catch (Exception e){
            System.out.printf("Ocurrió un error en el programa: %s", e.getMessage());
        }
    }

    public static void run() throws InterruptedException {
        Employee[] employees = new Employee[2];
        LoadData loadClassesDataThread = new LoadData("Loading Data", employees);
        loadClassesDataThread.start();

        try{
            loadClassesDataThread.join();
        }catch (InterruptedException e){
            throw new InterruptedException(e.getMessage());
        }
        employees = loadClassesDataThread.getEmployees().clone();

        DoWork doWork = new DoWork("Doing work", employees);
        doWork.start();
    }
}