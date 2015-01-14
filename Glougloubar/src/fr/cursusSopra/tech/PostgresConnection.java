package fr.cursusSopra.tech;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnection {
	public static Connection GetConnexion() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/glougloubar";
            String user = "postgres";
            String passwd = "postgres";
            return DriverManager.getConnection(url, user, passwd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
