package datos;

import java.sql.*;


public class Conexion {
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/test?allowPublicKeyRetrieval=true&ssl=false&useTimezone=true&serverTimezone=UTC";
    private static final String JDBC_USER="root";
    private static final String JDBC_PASSWRD="admin";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWRD);
    }
    public static void close(ResultSet rs) throws SQLException {
    rs.close();
    }
    public static void close(Statement st) throws SQLException {
        st.close();
    }
    public static void close(PreparedStatement st) throws  SQLException{
        st.close();
    }
    public static void close(Connection conn) throws SQLException{
        conn.close();
    }
}
