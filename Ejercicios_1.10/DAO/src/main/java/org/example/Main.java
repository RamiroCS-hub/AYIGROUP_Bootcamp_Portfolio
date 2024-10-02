package org.example;

import org.example.DAO.UserDAO;
import org.example.DAO.UserImp;
import org.example.Models.User;

import java.sql.SQLException;

public class Main {
    static final String CONNECTION_QUERY = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASSWORD = "pancha123";

    static UserDAO dao;
    public static void main(String[] args) throws SQLException {
        Database db = new Database(CONNECTION_QUERY, USER, PASSWORD);
        dao = new UserImp(db);
        run();
    }

    private static void run(){
        boolean flag = true;
        while(flag){
            System.out.println("Insert [1] \nSelect [2] \nUpdate [3] \nDelete [4] \nSalir [5]");
            String option = System.console().readLine();
            switch(option){
                case "1":
                    User newUser = getUserData();
                    dao.insert(newUser);
                    break;
                case "2":
                    dao.select();
                    break;
                case "3":
                    User updateUser = getUserUpdate();
                    dao.update(updateUser);
                    break;
                case "4":
                    int id = getId();
                    dao.delete(id);
                    break;
                case "5":
                    flag = false;
                    break;
            }
        }
    }

    private static User getUserUpdate(){
       User user = getUserData();
       user.setId(getId());
       return user;
    }

    private static User getUserData(){
        System.out.println("Ingrese el nombre del usuario");
        String name = System.console().readLine();

        System.out.println("Ingrese la edad del usuario");
        int age = Integer.parseInt(System.console().readLine());

        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    private static int getId(){
        System.out.println("Ingrese el id del usuario");
        return Integer.parseInt(System.console().readLine());
    }

}