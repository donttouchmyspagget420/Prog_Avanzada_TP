package DLL;

import java.sql.*;

public class Database {
    //Url para conectar a postgresql en puerto por defecto(5432)
    private final static String URL = "jdbc:postgresql://localhost:5432/Prog_Avanzada_TP";

    //Usuario de postgresql por defecto(postgres)
    private final static String USER = "postgres";

    //Contraseña de base de datos
    private final static String PASSWORD = "hjkl";

    //Conección a base de datos
    private Connection conn;

    //Constructora de clase(debe acceder solo en mismo package)
    protected Database() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    protected Connection getConn() throws Exception {
        if (conn != null) {
            return conn;
        }
        throw new Exception("No hay conexion a base de datos");
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
        for (int i = 0; i < length; i++) {
            if (sql.charAt(i) == '?') {
                token++;
            }
        }

        return token == length;
    }
}
