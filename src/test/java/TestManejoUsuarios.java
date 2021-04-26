import datos.UsuarioDao;
import domain.Usuario;

import java.util.List;

public class TestManejoUsuarios {
    public static void main(String[] args) {
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario= new Usuario("Test", "Test");
        usuarioDao.insertar(usuario);
        usuario.setUsername("Prueba");
        usuario.setPassword("Prueba");
        usuarioDao.insertar(usuario);
        List<Usuario> usuarios = usuarioDao.seleccionar();
        usuarios.forEach(usuario1 -> System.out.println(usuario1));
    }
}
