package org.example;

import javax.swing.plaf.nimbus.State;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.BreakIterator;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.logging.SocketHandler;

public class Main {
    public static void main(String[] args) {
        try{
            run();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private static void run() throws IOException {
        String choise = "0";
        do{
            System.out.println("[1] Select Query \n [2] Insert Query [3]\n Update Query");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            choise = br.readLine();
            switch (choise){
                case "1": selectQuery(br); break;
                case "2": insertQuery(); break;
                case "3": updateQuery(); break;
            }
       }while(!choise.equals("0"));
    }

    private static void selectQuery(BufferedReader br) throws IOException {
        System.out.println("Ingrese que tabla quiere ver");
        String table = br.readLine();
        System.out.println("Ingrese que columnas quiere ver separadas por espacios");
        String columns = br.readLine();
        BreakIterator bi = BreakIterator.getWordInstance(Locale.US);
        bi.setText(columns);
        LinkedList<String> columnList = new LinkedList<>();
        int start = bi.first();
        for (int end = bi.next(); end != BreakIterator.DONE; start = end, end = bi.next()) {
            String word = columns.substring(start, end);
            columnList.add(word);
        }
        makeQuery(table, columnList);
    }


    private static void makeQuery(String query, LinkedList<String> column){

        try{
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","pancha123");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                for(String col : column){
                    System.out.print(rs.getString(col) + " ");
                }
                System.out.println();
            }
            conn.close();
            st.close();
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}