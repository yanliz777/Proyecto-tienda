package co.edu.uniquindio.tienda.controladorSecundario;

import co.edu.uniquindio.tienda.fabrica.ModelFactory;
import co.edu.uniquindio.tienda.modelo.Producto;
import co.edu.uniquindio.tienda.modelo.Tienda;

import java.util.ArrayList;

public class ControladorSecundarioVendedor {

    private Tienda tienda;
    private ModelFactory modelFactory;

    public ControladorSecundarioVendedor(ModelFactory modelFactory)
    {
        this.modelFactory = modelFactory;
        this.tienda = modelFactory.getTienda();
    }

    /**
     * Método que recibe por parámetros un Objeto producto y se
     * comunica con el modelFactory por medio del método agregarProducto y le envían
     * como parámetro al objeto producto que recibe este método
     */
    public Producto agregarProducto(Producto producto)
    {
        return modelFactory.agregarProductoVendedor(producto);
    }

    public ArrayList<Producto> obtenerProducto()
    {
        return modelFactory.obtenerProducto();
    }

    public boolean elimianrProductos(Producto producto){
        return modelFactory.eliminarProductos(producto);
    }

    public boolean actualizarProductos(Producto productoSeleccionado) {
        return modelFactory.actualizarProductos(productoSeleccionado);
    }

/*
    public boolean actualizarProductos(Producto productoSeleccionado)
    {
        return modelFactory.actualizarProductos(productoSeleccionado);
    }*/

}
