package co.edu.uniquindio.tienda.controladorSecundario;

import co.edu.uniquindio.tienda.fabrica.ModelFactory;
import co.edu.uniquindio.tienda.modelo.Producto;
import co.edu.uniquindio.tienda.modelo.Tienda;

import java.util.ArrayList;

public class ControladorSecundarioComprador {

    private Tienda tienda;
    private ModelFactory modelFactory;

    public ControladorSecundarioComprador(ModelFactory modelFactory)
    {
        this.modelFactory = modelFactory;
        this.tienda = modelFactory.getTienda();
    }

    public ArrayList<Producto> obtenerProducto()
    {
        return modelFactory.obtenerProducto();
    }
}
