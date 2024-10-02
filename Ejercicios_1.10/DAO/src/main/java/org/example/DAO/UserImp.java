package org.example.DAO;

import org.example.Database;
import org.example.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImp implements UserDAO{

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    private final Database db;
    private final Connection conn;

    public UserImp(Database db){
        this.db = db;
        this.conn = db.getConnection();
    }

    @Override
    public void insert(User user) {
        String query = "INSERT INTO users (name, age) VALUES (?, ?)";
        try {
            var ps = conn.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            int res = ps.executeUpdate();
            if(res > 0){
                System.out.println("Usuario insertado");
            }else{
                System.out.println("No se pudo insertar el usuario");
                throw new SQLException("No se pudo insertar el usuario");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void select() {
        String query = "SELECT * FROM users";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(ANSI_GREEN + "ID: " + ANSI_RESET + rs.getInt("id") + ANSI_GREEN +" Name: "
                        + ANSI_RESET + rs.getString("name") + ANSI_GREEN + " Age: " + ANSI_RESET
                        + rs.getInt("age"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        String query = "UPDATE users SET name = ?, age = ? WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.setInt(3, user.getId());
            int res = ps.executeUpdate();
            if(res > 0){
                System.out.println("Usuario actualizado");
            }else{
                System.out.println("No se pudo actualizar el usuario");
                throw new SQLException("No se pudo actualizar el usuario");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            int res = ps.executeUpdate();
            if(res > 0){
                System.out.println("Usuario eliminado");
            }else{
                System.out.println("No se pudo eliminar el usuario");
                throw new SQLException("No se pudo eliminar el usuario");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
