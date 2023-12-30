package co.edu.uniquindio.tienda.modelo;

import co.edu.uniquindio.tienda.enumerados.TipoUsuario;

import java.util.ArrayList;

public class Tienda {
    private ArrayList<Vendedor> listaVendedores = new ArrayList<>();
    private ArrayList<Comprador> listaCompradores = new ArrayList<>();
    private ArrayList<Producto> listaProductos = new ArrayList<>();
    private ArrayList<Compra> listaCompras = new ArrayList<>();

    public Tienda() {
    }

    public ArrayList<Vendedor> getListaVendedores() {
        return listaVendedores;
    }

    public void setListaVendedores(ArrayList<Vendedor> listaVendedores) {
        this.listaVendedores = listaVendedores;
    }

    public ArrayList<Comprador> getListaCompradores() {
        return listaCompradores;
    }

    public void setListaCompradores(ArrayList<Comprador> listaCompradores) {
        this.listaCompradores = listaCompradores;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public ArrayList<Compra> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(ArrayList<Compra> listaCompras) {
        this.listaCompras = listaCompras;
    }

    public Comprador guardarComprador(Usuario usuario) {
        Comprador comprador = new Comprador();
        comprador.setNombre(usuario.getNombre());
        comprador.setApellido(usuario.getApellido());
        comprador.setFechaNacimiento(usuario.getFechaNacimiento());
        comprador.setEstadoCivil(usuario.getEstadoCivil());
        comprador.setDireccion(usuario.getDireccion());
        comprador.setUsuario(usuario.getUsuario());
        comprador.setEmail(usuario.getEmail());
        comprador.setContrasena(usuario.getContrasena());
        if (!verificarCompradores(comprador)) {
            getListaCompradores().add(comprador);
            System.out.println("Comprador agregado");
        } else {
            System.out.println("Comprador ya existe!");
        }
        return comprador;
    }

    public Vendedor guardarVendedor(Usuario usuario) {
        Vendedor vendedor = new Vendedor();
        vendedor.setNombre(usuario.getNombre());
        vendedor.setApellido(usuario.getApellido());
        vendedor.setFechaNacimiento(usuario.getFechaNacimiento());
        vendedor.setEstadoCivil(usuario.getEstadoCivil());
        vendedor.setDireccion(usuario.getDireccion());
        vendedor.setUsuario(usuario.getUsuario());
        vendedor.setEmail(usuario.getEmail());
        vendedor.setContrasena(usuario.getContrasena());
        if (!verificarVendedores(vendedor)) {
            getListaVendedores().add(vendedor);
            System.out.println("Vendedor agregado");
        } else {
            System.out.println("Vendedor ya existe!");
        }
        return vendedor;
    }

    public boolean verificarCompradores(Usuario usuario) {

        boolean bandera = false;
        for (Comprador comprador : getListaCompradores()) {
            if (usuario.getEmail().equals(comprador.getEmail())) {
                bandera = true;
                break;
            }
        }
        return bandera;
    }

    public boolean verificarVendedores(Usuario usuario) {

        boolean bandera = false;
        for (Vendedor vendedor : getListaVendedores()) {
            if (usuario.getEmail().equals(vendedor.getEmail())) {
                bandera = true;
                break;
            }

        }
        return bandera;
    }

    public boolean loginUsuarioComprador(TipoUsuario usuario, String email, String contrasena) {
        boolean badera = false;
        for (Comprador comprador : getListaCompradores())
        {
            if (comprador.getUsuario().equals(usuario) && comprador.getEmail().equals(email) &&
                    comprador.getContrasena().equals(contrasena))
            {
                badera = true;
                break;
            }
        }
        return  badera;
    }

    public boolean loginUsuarioVendedor(TipoUsuario usuario, String email, String contrasena) {
        boolean bandera = false;
        for (Vendedor vendedor:getListaVendedores()) {
            if (vendedor.getUsuario().equals(usuario) && vendedor.getEmail().equals(email) &&
                    vendedor.getContrasena().equals(contrasena)){
                bandera = true;
            }
        }
        return bandera;
    }

    /**
     * Método que recibe un Objeto producto por parámetro que es el que
     *  nos permite settiar los datos al producto que finalmente
     *  vamos a guardar en la tienda y que después retornamos.
     *
     */

    public Producto agregarProductoVendedor(Producto producto)
    {
        Producto p1 = new Producto();//nuevo
        p1.setId(producto.getId());
        p1.setNombre(producto.getNombre());
        p1.setTipoProducto(producto.getTipoProducto());
        p1.setFechaCaducidad(producto.getFechaCaducidad());
        p1.setPrecioProducto(producto.getPrecioProducto());
        p1.setStock(producto.getStock());

        getListaProductos().add(p1);//acá se guarda el producto, es el final de la comunicación entre clases
        //ahora lo retorno ya con los datos settiados y hago lo inverso, devolverme!!
        return p1;
    }

    public boolean eliminarProducto(Producto producto) {

        if(producto != null) {
            getListaProductos().remove(producto);
            return true;
        }else {
            return false;
        }
    }

    public Producto obtenerProducto(String codigo) {

        for (Producto producto : listaProductos) {
            if(producto.getId().equals(codigo)) {
                return producto;
            }
        }
        return null;
    }

    /**
     *Método que me permite actualizar el producto en la clase tienda con la ayuda
     * del objeto producto que me llega por parámetro que me permite settiar los datos que ingreso
     * el usuario en la vista
     */
    public boolean actualizar(Producto productoSeleccionado){

        boolean bandera = false;

        Producto productoEncontrado = obtenerProducto(productoSeleccionado.getId());
        System.out.println(productoSeleccionado.getNombre());
        if(productoEncontrado != null) {

            for (int i = 0; i < getListaProductos().size(); i++) {
                if (getListaProductos().get(i).getId().equals(productoSeleccionado.getId())) {

                    productoEncontrado.setNombre(productoSeleccionado.getNombre());
                    productoEncontrado.setFechaCaducidad(productoSeleccionado.getFechaCaducidad());
                    productoEncontrado.setId(productoSeleccionado.getId());
                    productoEncontrado.setTipoProducto(productoSeleccionado.getTipoProducto());
                    productoEncontrado.setStock(productoSeleccionado.getStock());
                    productoEncontrado.setPrecioProducto(productoSeleccionado.getPrecioProducto());

                    System.out.println(productoEncontrado.getNombre());
                    System.out.println(productoEncontrado.getFechaCaducidad());
                    System.out.println(productoEncontrado.getId());
                    System.out.println(productoEncontrado.getTipoProducto());
                    System.out.println(productoEncontrado.getStock());
                    System.out.println(productoEncontrado.getPrecioProducto());

                    getListaProductos().set(i, productoEncontrado);
                    bandera = true;
                }
            }
        }else{
            System.out.println("nulo");
        }
        return bandera;
    }
}
