package datos;


import domain.Usuario;

import java.sql.*;
import java.util.*;


import static datos.Conexion.close;
import static datos.Conexion.getConnection;

public class UsuarioDao {

    private static final String SQL_SELECT= "SELECT id_usuario, username, password FROM usuarios";
    private static final String SQL_INSERT= "INSERT INTO usuarios (username, password) VALUES (?, ?)";
    private static final String SQL_UPDATE= "UPDATE usuarios SET username= ?, password=? WHERE (id_usuario=?)";
    private static final String SQL_DELETE= "DELETE FROM usuarios WHERE (id_usuario=?)";
    public List<Usuario> seleccionar(){
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Usuario usuario;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conn = getConnection();
            stm= conn.prepareStatement(SQL_SELECT);
            rs = stm.executeQuery();
            while(rs.next()){
                usuario= new Usuario(rs.getInt("id_usuario"),
                        rs.getString("username"),
                        rs.getString("password"));
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        finally{
            try {
                close(rs);
                close(stm);
                close(conn);

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }

        return usuarios;
    }
    public int insertar(Usuario usuario){

        Connection conn = null;
        PreparedStatement stm= null;
        int registros=0;
        try {
            conn= getConnection();
            stm= conn.prepareStatement(SQL_INSERT);
            stm.setString(1, usuario.getPassword());
            stm.setString(2,usuario.getPassword());
            registros= stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            try {
                close(stm);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }


        return registros;
    }
    public int actualizar(Usuario usuario){
        Connection conn=null;
        PreparedStatement stm= null;
        var registros=0;
        try {
            conn= getConnection();
            stm= conn.prepareStatement(SQL_UPDATE);
            stm.setString(1,usuario.getUsername());
            stm.setString(2,usuario.getPassword());
            stm.setInt(5,usuario.getIdUsuario());
            registros=stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            try {
                close(stm);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }
        return registros;
    }

    public int borrar(Usuario usuario){
        Connection conn=null;
        PreparedStatement stm= null;
        var registros=0;
        try {
            conn= getConnection();
            stm= conn.prepareStatement(SQL_DELETE);
            stm.setInt(1,usuario.getIdUsuario());
            registros=stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            try {
                close(stm);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }
        return registros;


    }
}
