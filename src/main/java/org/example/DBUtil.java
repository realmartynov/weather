package org.example;

public class DBUtil {
    public static void DB(String[] args) throws ClassNotFoundException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "postgres";

        Class.forName("org.postgresql.Driver");
    }
}