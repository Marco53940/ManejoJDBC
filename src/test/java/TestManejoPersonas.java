import datos.Conexion;
import datos.PersonaDAO;
import domain.Persona;

import java.util.List;

public class TestManejoPersonas {
    public static void main(String[] args) {
        PersonaDAO personaDao = new PersonaDAO();
        Persona persona2 = new Persona("Test","Test","Email","Telefono");
        personaDao.insertar(persona2);
        persona2.setIdPersona(2);
        personaDao.actualizar(persona2);
        Persona persona3 = new Persona(10);
        personaDao.borrar(persona3);
        List<Persona> personas=personaDao.seleccionar();
        personas.forEach(persona -> System.out.println(persona));


    }
}
