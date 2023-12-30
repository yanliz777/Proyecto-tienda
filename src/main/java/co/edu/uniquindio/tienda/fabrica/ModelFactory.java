package co.edu.uniquindio.tienda.fabrica;
import co.edu.uniquindio.tienda.enumerados.TipoUsuario;
import co.edu.uniquindio.tienda.modelo.*;
import co.edu.uniquindio.tienda.persistencia.Persistencia;
import java.io.IOException;
import java.util.ArrayList;

public class ModelFactory {

    private Persistencia persistencia = new Persistencia();
    public Tienda tienda;

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    /**
     *Método que recibe el parámetro y lo envía al modelo, a la clase tienda.
     * La que finalmente lo almacena en el arrayList de la Clase tienda. También
     * Mé permite guardar el producto en la persistencia de datos
     */
    public Producto agregarProductoVendedor(Producto producto)
    {
        {
            Producto p = null;

            try
            {
                p = getTienda().agregarProductoVendedor(producto);
                persistencia.guardarProductos(getTienda().getListaProductos());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return p;
        }
    }

    public ArrayList<Producto> obtenerProducto()
    {
        return getTienda().getListaProductos();
    }


    public static class SingletonHolder {
        private final static ModelFactory eINSTANCE = new ModelFactory();
    }
    public static ModelFactory getInstance() {
        return SingletonHolder.eINSTANCE;
    }
    //constructor:
    public ModelFactory() {
        cargarDatosDesdeArchivos();
    }
    public void cargarDatosDesdeArchivos() {
        this.tienda = new Tienda();
        try {
            ArrayList<Vendedor> vendedores;
            ArrayList<Comprador> compradores;
            ArrayList<Producto> productos;
            ArrayList<Compra>compras;

            vendedores = persistencia.leerVendedor();
            compradores = persistencia.leerComprador();
            productos = persistencia.leerProductos();
            compras = persistencia.leerCompra();

            getTienda().getListaVendedores().addAll(vendedores);
            getTienda().getListaCompradores().addAll(compradores);
            getTienda().getListaProductos().addAll(productos);
            getTienda().getListaCompras().addAll(compras);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Usuario guardarComprador(Usuario usuario) {
        Comprador c = null;
        try {
            c = getTienda().guardarComprador(usuario);
            persistencia.guardarCompradores(getTienda().getListaCompradores());
        }catch (IOException e){
            e.printStackTrace();
        }
        return c;
    }

    public Usuario guardarVendedor(Usuario usuario) {
        Vendedor v = null;
        try {
            v = getTienda().guardarVendedor(usuario);
            persistencia.guardarVendedor(getTienda().getListaVendedores());
        }catch (IOException e){
            e.printStackTrace();
        }
        return v;
    }
    public boolean loginUsuarioComprador(TipoUsuario usuario,String email, String contrasena) {
        boolean bandera = false;
        try {
            bandera = getTienda().loginUsuarioComprador(usuario, email, contrasena);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bandera;
    }
    public boolean loginUsuarioVendedor(TipoUsuario usuario,String email, String contrasena) {
        boolean bandera = false;
        try {
            bandera = getTienda().loginUsuarioVendedor(usuario,email, contrasena);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bandera;
    }

    public boolean eliminarProductos(Producto producto) {

        boolean eliminar = false;

        try {

            eliminar = getTienda().eliminarProducto(producto);
            persistencia.guardarProductos(obtenerProducto());

        }catch (Exception e){
            e.printStackTrace();
        }
        return eliminar;

    }

    public boolean actualizarProductos(Producto productoSeleccionado) {
        {
            boolean p = false;

            try
            {
                p = getTienda().actualizar(productoSeleccionado);
                persistencia.guardarProductos(getTienda().getListaProductos());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return p;
        }
    }
}
