package org.example;

import javax.swing.plaf.nimbus.State;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.BreakIterator;
import java.util.*;
import java.util.logging.SocketHandler;

public class Main {

    static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASSWORD = "pancha123";
    static Database db;

    public static void main(String[] args) {
        try{
            run();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private static void run() throws IOException, SQLException {
        String choise;
        db = new Database(CONNECTION_URL, USER, PASSWORD);
        do{
            System.out.println("[1] Select \n[2] Insert " +
                    "\n[3] Update \n[4] Delete \n[5] Consulta Multitabla \n[0] Exit");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            choise = br.readLine();
            switch (choise){
                case "1": selectQuery(br); break;
                case "2": insertQuery(br); break;
                case "3": updateQuery(br); break;
                case "4": deleteQuery(br); break;
                case "5": multiTableQuery(br); break;
            }
        } while(!choise.equals("0"));
        db.close();
    }

    private static void multiTableQuery(BufferedReader br) throws IOException {
        System.out.println("Ingrese el legajo del estudiante que quiere buscar");
        String legajo = br.readLine();
        String query = "SELECT e.legajo,e.nombre,e.apellido,c.nombre_curso,i.fecha_inscripcion,cal.nota FROM estudiantes e JOIN inscripciones i ON e.legajo = i.legajo_estudiante JOIN cursos c ON i.id_curso = c.id_curso LEFT JOIN calificaciones cal ON e.legajo = cal.legajo_estudiante AND c.id_curso = cal.id_curso WHERE e.legajo =" + legajo;
        db.makeQuery(query, List.of("legajo", "nombre", "apellido", "nombre_curso", "fecha_inscripcion", "nota"));
    }

    private static void deleteQuery(BufferedReader br) throws IOException {
        System.out.println("Ingrese en que tabla quiere operar");
        String table = br.readLine();

        System.out.println("Ingrese el Id de la fila que quiere borrar (Ingrese * para eliminar todas las filas)");
        String id = br.readLine();

        String query = formatQuery(1, table, id, null, null, null);
        db.makeQuery(query);
    }

    private static String formatQuery(int typeOfQuery, String table, String id, String columnsValues, String columns, String valueListToString){
       return switch (typeOfQuery){
           case 1 -> Objects.equals(id, "*") ? "DELETE FROM " + table : "DELETE FROM "+ table + " WHERE id_estudiante = " + id;
           case 2 -> "UPDATE "+ table + " SET" + columnsValues + "WHERE id = " + id;
           case 3 -> "INSERT INTO " + table + "(" + columns.replace(' ', ',') + ")" + " VALUES ("+ valueListToString + ")";
           case 4 -> Objects.equals(id, "*") ? "SELECT * FROM "+ table : "SELECT " + columns.replace(' ', ',') + " FROM " + table;
           default -> "";
       };
    }

    private static void updateQuery(BufferedReader br) throws  IOException {
        System.out.println("Ingrese que tabla quiere actualizar");
        String table = br.readLine();

        System.out.println("Ingrese el Id de la fila que quiere actualizar");
        String id = br.readLine();

        System.out.println("Ingrese que columnas quiere actualizar separadas por espacios");
        String columns = br.readLine();
        List<String> columnList = separateString(columns);

        System.out.println("Ingrese los valores que quiere actualizar separados por espacios");
        String values = br.readLine();
        List<String> valueList = separateString(values);
        System.out.println(valueList);

        String columnsValues = refactorUpdateQuery(valueList, columnList);

        String query = formatQuery(2, table, id, columnsValues, null, null);
        db.makeQuery(query);
    }

    private static String refactorUpdateQuery(List<String> values, List<String> columns){
        String result = "";
        for(int i = 0; i < values.size(); i++){
            result += columns.get(i) + " = " + values.get(i) + ", ";
        }
        return result;
    }

    private static void insertQuery(BufferedReader br) throws IOException {
        System.out.println("Ingrese que tabla quiere insertar");
        String table = br.readLine();

        System.out.println("Ingrese que columnas quiere insertar separadas por espacios");
        String columns = br.readLine();

        System.out.println("Ingrese los valores que quiere insertar separados por espacios");
        String values = br.readLine();
        List<String> valueList = separateString(values);
        String valueListToString = valueList.toString().replace("[", "").replace("]", "");

        System.out.println(valueListToString);
        String query = formatQuery(3, table, null, null, columns, null);
        db.makeQuery(query);
    }

    private static void selectQuery(BufferedReader br) throws IOException {
        System.out.println("Ingrese que tabla quiere ver");
        String table = br.readLine();

        System.out.println("Ingrese que columnas quiere ver separadas por espacios (Ingrese * para ver todas)");
        String columns = br.readLine();

        List<String> columnList = separateString(columns);
        String query = formatQuery(4, table, null, null, columns, null);
        db.makeQuery(query, columnList);
    }

    private static List<String> separateString(String columns){
        BreakIterator bi = BreakIterator.getWordInstance(Locale.US);
        bi.setText(columns);
        LinkedList<String> columnList = new LinkedList<>();
        int start = bi.first();
        boolean flag = false;
        String stringWord = "";
        for (int end = bi.next(); end != BreakIterator.DONE; start = end, end = bi.next()) {
            String word = columns.substring(start, end);

            if(word.equals("'")){
               flag = !flag;
               word = stringWord;
            }

            if(flag) {
                stringWord += word;
                continue;
            }

            if(word.equals(" ")) continue;

            columnList.add(word);
        }

        return refactorQueryParams(columnList);
    }

    private static List<String> refactorQueryParams(LinkedList<String> list){
        return list.stream().map(column -> {
            if(column.contains(" ")){
                StringBuilder _sb = new StringBuilder(column);
                _sb.insert(0, "'");
                _sb.append("'");
                return _sb.toString();
            }
            return column;
        }).toList();
    }
}