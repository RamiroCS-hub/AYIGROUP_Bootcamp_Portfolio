package org.example;

import java.sql.*;
import java.util.List;

public class Database {

    public Connection conn;
    Database(String connectionUrl, String user, String pass) throws SQLException {
        this.conn = DriverManager.getConnection(connectionUrl,user,pass);
    }


    public void makeQuery(String query) {
        try {
            Statement st = this.conn.createStatement();
            int result = st.executeUpdate(query);

            if (result == 1) {
                System.out.println("Se ha insertado correctamente");
            } else {
                System.out.println("No se ha podido insertar");
            }
            st.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void makeQuery(String query, List<String> column){
        try{
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                if(column.contains("*")){
                    ResultSetMetaData rsmd = rs.getMetaData();
                    for(int i = 1; i <= rsmd.getColumnCount(); i++){
                        System.out.print(rs.getString(i) + " ");
                    }
                }else{
                    for(String col : column){
                        System.out.print(rs.getString(col) + " ");
                    }
                }
                System.out.println();
            }
            conn.close();
            st.close();
        }catch(Exception e) {
            System.out.println(e);
        }
    }

    public void close() throws SQLException {
        this.conn.close();
    }
}
