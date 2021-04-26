
import datos.Conexion;
import datos.PersonaDAO;
import domain.Persona;

import java.sql.*;
import java.util.List;

public class TestManejoPersonas {
    public static void main(String[] args) {
        Connection conexion= null;
        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            //Realizar el comienzo de las transacciones
            PersonaDAO personaDao = new PersonaDAO(conexion);
            //Actualizar el id 4
            Persona cambioPersona = new Persona();
            cambioPersona.setIdPersona(4);
            cambioPersona.setNombre("Carla Ivonne");
            cambioPersona.setApellido("Gomez");
            cambioPersona.setEmail("kgomes@hotmail.com");
            cambioPersona.setTelefono("555585455");
            personaDao.actualizar(cambioPersona);

            //Insert

            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombre("Carlos");
            nuevaPersona.setApellido("Gomez");
            personaDao.insertar(nuevaPersona);
            conexion.commit();

        }
        catch (SQLException e){
            e.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace(System.out);
            }

        }


    }
}
