package co.edu.uniquindio.tienda.controladorSecundario;

import co.edu.uniquindio.tienda.enumerados.TipoUsuario;
import co.edu.uniquindio.tienda.fabrica.ModelFactory;
import co.edu.uniquindio.tienda.modelo.Tienda;
import co.edu.uniquindio.tienda.modelo.Usuario;

public class guardarUsuarioControladorSecundario {
    private ModelFactory modelFactory;
    private Tienda tienda;
    public guardarUsuarioControladorSecundario(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
        tienda = modelFactory.getTienda();
    }

    public Usuario guardarUsuario(Usuario usuario) {
        if (usuario.getUsuario().equals(TipoUsuario.comprador)){
            return modelFactory.guardarComprador(usuario);
        }
        else {
            return  modelFactory.guardarVendedor(usuario);
        }

    }
}
