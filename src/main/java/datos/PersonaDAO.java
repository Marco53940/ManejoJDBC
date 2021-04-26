package datos;

import domain.Persona;
import static datos.Conexion.*;
import java.sql.*;
import java.util.*;

public class PersonaDAO {

    private Connection conexionTransaccional;

    private static final String SQL_SELECT= "SELECT id_persona, nombre, apellido, email, telefono FROM persona";
    private static final String SQL_INSERT= "INSERT INTO persona (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE= "UPDATE persona SET nombre= ?, apellido=?, email=?, telefono= ? WHERE (id_persona=?)";
    private static final String SQL_DELETE= "DELETE FROM persona WHERE (id_persona=?)";

    public PersonaDAO(){

    }
    public PersonaDAO(Connection conexionTransaccional) {
        this.conexionTransaccional= conexionTransaccional;
    }

    public List<Persona> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Persona persona;
        List<Persona> personas = new ArrayList<>();
        try {
            conn = this.conexionTransaccional!=null ? this.conexionTransaccional : getConnection();
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

        }
        finally{
                close(rs);
                close(stm);
                if(this.conexionTransaccional==null){
                    close(conn);
                }
        }

        return personas;
    }
    public int insertar(Persona persona) throws SQLException {

     Connection conn = null;
     PreparedStatement stm= null;
     int registros=0;
        try {
            conn = this.conexionTransaccional!=null ? this.conexionTransaccional : getConnection();
            stm= conn.prepareStatement(SQL_INSERT);
            stm.setString(1,persona.getNombre());
            stm.setString(2,persona.getApellido());
            stm.setString(3,persona.getEmail());
            stm.setString(4,persona.getTelefono());
            registros= stm.executeUpdate();

        }
        finally{

                close(stm);
                if(this.conexionTransaccional==null){
                    close(conn);
                }


        }


     return registros;
    }
    public int actualizar(Persona persona) throws SQLException {
        Connection conn=null;
        PreparedStatement stm= null;
        var registros=0;
        try {
            conn = this.conexionTransaccional!=null ? this.conexionTransaccional : getConnection();
            stm= conn.prepareStatement(SQL_UPDATE);
            stm.setString(1,persona.getNombre());
            stm.setString(2,persona.getApellido());
            stm.setString(3,persona.getEmail());
            stm.setString(4,persona.getTelefono());
            stm.setInt(5,persona.getIdPersona());
            registros=stm.executeUpdate();

        }
        finally{
                close(stm);
                if(this.conexionTransaccional==null){
                    close(conn);
                }

        }
        return registros;
    }

    public int borrar(Persona persona) throws SQLException {
        Connection conn=null;
        PreparedStatement stm= null;
        var registros=0;
        try {
            conn = this.conexionTransaccional!=null ? this.conexionTransaccional : getConnection();
            stm= conn.prepareStatement(SQL_DELETE);
            stm.setInt(1,persona.getIdPersona());
            registros=stm.executeUpdate();

        }
        finally{
                close(stm);
                if(this.conexionTransaccional==null){
                    close(conn);
                }

        }
        return registros;


    }

}
