package DLL;

import java.sql.*;

public class Database {
    //Url para conectar a postgresql en puerto por defecto(5432)
    private final static String URL = "jdbc:postgresql://localhost:5432/prog_avanzada_tp";

    //Usuario de postgresql por defecto(postgres)
    private final static String USER = "postgres";

    //Contraseña de base de datos
    private final static String PASSWORD = "hjkl";

    //Conección a base de datos
    private Connection conn;

    private static Database instance;

    //Constructora de clase(debe acceder solo en mismo package)
    protected Database() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    protected static Database getInstanse() throws SQLException {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    protected ResultSet query(final String sql) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);

        return pstmt.executeQuery();
    }

    protected ResultSet query(final String sql, final String[] vals) throws SQLException {
        int length = vals.length;
        if (!checkValuesOfQuery(sql, length)) throw new SQLException("No hay sql o valores");

        PreparedStatement pstmt = conn.prepareStatement(sql);

        for (int i = 0; i < length; i++) {
            pstmt.setObject(i + 1, vals[i]);
        }

        return pstmt.executeQuery();
    }

    protected int update(final String sql) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);

        return pstmt.executeUpdate();
    }

    protected int update(final String sql, final String[] vals) throws SQLException {
        int length = vals.length;
        if (!checkValuesOfQuery(sql, length)) throw new SQLException("No hay sql o valores");

        PreparedStatement pstmt = conn.prepareStatement(sql);

        for (int i = 0; i < length; i++) {
            pstmt.setObject(i + 1, vals[i]);
        }

        return pstmt.executeUpdate();
    }

    //Funciones auxiliares internas
    private boolean checkValuesOfQuery(final String sql, final int length) {
        if (sql.isBlank()) return false;

        int token = 0;
        for (int i = 0; i < sql.length(); i++) {
            if (sql.charAt(i) == '?') token++;
        }
        System.out.println(sql);
        System.out.print(token + " " + length + "\n");

        return token == length;
    }
}
