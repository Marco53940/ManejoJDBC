package datos;

import domain.Persona;
import static datos.Conexion.*;
import java.sql.*;
import java.util.*;

public class PersonaDAO {
    
    private static final String SQL_SELECT= "SELECT id_persona, nombre, apellido, email, telefono FROM persona";
    private static final String SQL_INSERT= "INSERT INTO persona (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE= "UPDATE persona SET nombre= ?, apellido=?, email=?, telefono= ? WHERE (id_persona=?)";
    private static final String SQL_DELETE= "DELETE FROM persona WHERE (id_persona=?)";
    public List<Persona> seleccionar(){
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Persona persona;
        List<Persona> personas = new ArrayList<>();
        try {
            conn = getConnection();
            stm= conn.prepareStatement(SQL_SELECT);
            rs = stm.executeQuery();
            while(rs.next()){
                persona= new Persona(rs.getInt("id_persona"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("telefono"));
                personas.add(persona);
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

        return personas;
    }
    public int insertar(Persona persona){

     Connection conn = null;
     PreparedStatement stm= null;
     int registros=0;
        try {
            conn= getConnection();
            stm= conn.prepareStatement(SQL_INSERT);
            stm.setString(1,persona.getNombre());
            stm.setString(2,persona.getApellido());
            stm.setString(3,persona.getEmail());
            stm.setString(4,persona.getTelefono());
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
    public int actualizar(Persona persona){
        Connection conn=null;
        PreparedStatement stm= null;
        var registros=0;
        try {
            conn= getConnection();
            stm= conn.prepareStatement(SQL_UPDATE);
            stm.setString(1,persona.getNombre());
            stm.setString(2,persona.getApellido());
            stm.setString(3,persona.getEmail());
            stm.setString(4,persona.getTelefono());
            stm.setInt(5,persona.getIdPersona());
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

    public int borrar(Persona persona){
        Connection conn=null;
        PreparedStatement stm= null;
        var registros=0;
        try {
            conn= getConnection();
            stm= conn.prepareStatement(SQL_DELETE);
            stm.setInt(1,persona.getIdPersona());
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
