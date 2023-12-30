package co.edu.uniquindio.tienda.controladorSecundario;

import co.edu.uniquindio.tienda.enumerados.TipoUsuario;
import co.edu.uniquindio.tienda.fabrica.ModelFactory;
import co.edu.uniquindio.tienda.modelo.Tienda;

public class ControladorSecundarioLogin {

    private Tienda tienda;
    private ModelFactory modelFactory;

    public  ControladorSecundarioLogin(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
        tienda = modelFactory.getTienda();
    }

    public boolean loginUsuario(TipoUsuario usuario, String email, String contrasena) {

        if (TipoUsuario.comprador.equals(usuario)){
            return modelFactory.loginUsuarioComprador(usuario,email, contrasena);

        } else{
            return modelFactory.loginUsuarioVendedor(usuario, email, contrasena);
        }
    }
}
