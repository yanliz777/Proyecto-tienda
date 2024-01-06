package co.edu.uniquindio.tienda.persistencia;

import co.edu.uniquindio.tienda.enumerados.TipoEstadoCivil;
import co.edu.uniquindio.tienda.enumerados.TipoProducto;
import co.edu.uniquindio.tienda.enumerados.TipoUsuario;
import co.edu.uniquindio.tienda.modelo.Compra;
import co.edu.uniquindio.tienda.modelo.Comprador;
import co.edu.uniquindio.tienda.modelo.Producto;
import co.edu.uniquindio.tienda.modelo.Vendedor;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Persistencia {

    private ArchivoUtil archivoUtil = new ArchivoUtil();

    //----------------------------------IVANCHO----------------------------------------------------------------

    public static final String rutaVendedor = "C:\\Users\\ivanr\\Documents\\Proyecto-tienda\\src\\main\\" +
            "java\\co\\edu\\uniquindio\\tienda\\archivos\\Vendedores.txt";

    public static final String rutaCompras = "C:\\Users\\ivanr\\Documents\\Proyecto-tienda\\src\\main\\java" +
            "\\co\\edu\\uniquindio\\tienda\\archivos\\Compras.txt";

    public static final String rutaProductos = "C:\\Users\\ivanr\\Documents\\Proyecto-tienda\\src\\main\\" +
            "java\\co\\edu\\uniquindio\\tienda\\archivos\\Productos.txt";

    public static final String rutaCompradores = "C:\\Users\\ivanr\\Documents\\Proyecto-tienda\\src\\main\\" +
            "java\\co\\edu\\uniquindio\\tienda\\archivos\\Compradores.txt";

    //-----------------------------------EDUARD---------------------------------------------------------------

  /* public static final String rutaVendedor = "C:\\Users\\eduar\\IdeaProjects\\Recursividad\\Proyecto-Vaciones\\" +
            "src\\main\\java\\co\\edu\\uniquindio\\tienda\\archivos\\Vendedores.txt";

    public static final String rutaCompras = "C:\\Users\\eduar\\IdeaProjects\\Recursividad\\Proyecto-Vaciones\\" +
            "src\\main\\java\\co\\edu\\uniquindio\\tienda\\archivos\\Compras.txt";

    public static final String rutaProductos = "C:\\Users\\eduar\\IdeaProjects\\Recursividad\\Proyecto-Vaciones\\" +
            "src\\main\\java\\co\\edu\\uniquindio\\tienda\\archivos\\Productos.txt";

    public static final String rutaCompradores = "C:\\Users\\eduar\\IdeaProjects\\Recursividad\\Proyecto-Vaciones\\" +
            "src\\main\\java\\co\\edu\\uniquindio\\tienda\\archivos\\Compradores.txt";*/

    //-----------------------------------FRANCO---------------------------------------------------------------

   /*public static final String rutaVendedor = "C:\\Users\\YAN FRANCO\\OneDrive\\Escritorio\\Carpeta Universidad\\proyectoVacaciones - copia\\proyectoTienda" +
           "\\tienda\\src\\main\\java\\co\\edu\\uniquindio\\tienda\\archivos\\Vendedores.txt";

    public static final String rutaCompras = "C:\\Users\\YAN FRANCO\\OneDrive\\Escritorio\\Carpeta Universidad\\proyectoVacaciones - copia\\proyectoTienda" +
            "\\tienda\\src\\main\\java\\co\\edu\\uniquindio\\tienda\\archivos\\Compras.txt";

    public static final String rutaProductos = "C:\\Users\\YAN FRANCO\\OneDrive\\Escritorio\\Carpeta Universidad\\proyectoVacaciones - copia\\proyectoTienda" +
            "\\tienda\\src\\main\\java\\co\\edu\\uniquindio\\tienda\\archivos\\Productos.txt";

    public static final String rutaCompradores = "C:\\Users\\YAN FRANCO\\OneDrive\\Escritorio\\Carpeta Universidad\\proyectoVacaciones - copia\\proyectoTienda" +
            "\\tienda\\src\\main\\java\\co\\edu\\uniquindio\\tienda\\archivos\\Compradores.txt";
*/
    public void guardarVendedor(ArrayList<Vendedor>listaVendedores) throws IOException {
        StringBuilder datos  = new StringBuilder();
        for (Vendedor v: listaVendedores) {
            datos.append(v.getUsuario()).append("!!").
                    append(v.getNombre()).append("!!").
                    append(v.getApellido()).append("!!").
                    append(v.getFechaNacimiento()).append("!!").
                    append(v.getEstadoCivil()).append("!!").
                    append(v.getEmail()).append("!!").
                    append(v.getContrasena()).append("!!").
                    append(v.getDireccion()).append("\n");
        }
        archivoUtil.guardarArchivo(rutaVendedor, datos.toString(), false );
    }
    public ArrayList<Vendedor> leerVendedor()throws IOException{
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        ArrayList<String>datos = archivoUtil.leerArchivo(rutaVendedor);
        String cadena;
        for (String t: datos) {
            cadena = t;
            Vendedor vendedor = new Vendedor();
            vendedor.setUsuario(TipoUsuario.valueOf(cadena.split("!!")[0]));
            vendedor.setNombre(cadena.split("!!")[1]);
            vendedor.setApellido(cadena.split("!!")[2]);
            vendedor.setFechaNacimiento(LocalDate.parse(cadena.split("!!")[3]));
            vendedor.setEstadoCivil(TipoEstadoCivil.valueOf(cadena.split("!!")[4]));
            vendedor.setEmail(cadena.split("!!")[5]);
            vendedor.setContrasena(cadena.split("!!")[6]);
            vendedor.setDireccion(cadena.split("!!")[7]);
            vendedores.add(vendedor);
        }
        return vendedores;
    }

    public void guardarCompradores(ArrayList<Comprador>listaCompradores)throws IOException{

        StringBuilder datosComprador  =new StringBuilder();

        for (Comprador c: listaCompradores) {

            datosComprador.append(c.getUsuario()).append("!!").
                    append(c.getNombre()).append("!!").
                    append(c.getApellido()).append("!!").
                    append(c.getFechaNacimiento()).append("!!").
                    append(c.getEstadoCivil()).append("!!").
                    append(c.getEmail()).append("!!").
                    append(c.getContrasena()).append("!!").
                    append(c.getDireccion()).append("\n");
        }
        archivoUtil.guardarArchivo(rutaCompradores, datosComprador.toString(), false );

    }
    public ArrayList<Comprador> leerComprador()throws IOException{
        ArrayList<Comprador> compradores = new ArrayList<>();
        ArrayList<String>datos = archivoUtil.leerArchivo(rutaCompradores);
        String cadena;
        for (String t: datos) {
            cadena = t;
            Comprador comprador = new Comprador();
            comprador.setUsuario(TipoUsuario.valueOf(cadena.split("!!")[0]));
            comprador.setNombre(cadena.split("!!")[1]);
            comprador.setApellido(cadena.split("!!")[2]);
            comprador.setFechaNacimiento(LocalDate.parse(cadena.split("!!")[3]));
            comprador.setEstadoCivil(TipoEstadoCivil.valueOf(cadena.split("!!")[4]));
            comprador.setEmail(cadena.split("!!")[5]);
            comprador.setContrasena(cadena.split("!!")[6]);
            comprador.setDireccion(cadena.split("!!")[7]);
            compradores.add(comprador);
        }
        return compradores;
    }

    public void guardarProductos(ArrayList<Producto>listaProductos) throws IOException{
        StringBuilder datosProductos = new StringBuilder();

        for(Producto p:listaProductos){
            datosProductos.append(p.getId()).append("!!").
                    append(p.getNombre()).append("!!").
                    append(p.getTipoProducto()).append("!!").
                    append(p.getFechaCaducidad()).append("!!").
                    append(p.getPrecioProducto()).append("!!").
                    append(p.getStock()).append("\n");
        }
        archivoUtil.guardarArchivo(rutaProductos, datosProductos.toString(), false );

    }

    public ArrayList<Producto> leerProductos()throws IOException{
        ArrayList<Producto> productos = new ArrayList<>();
        ArrayList<String>datos = archivoUtil.leerArchivo(rutaProductos);
        String cadena;
        for (String t: datos) {
            cadena = t;
            Producto producto = new Producto();
            producto.setId(cadena.split("!!")[0]);
            producto.setNombre(cadena.split("!!")[1]);
            producto.setTipoProducto(TipoProducto.valueOf(cadena.split("!!")[2]));
            producto.setFechaCaducidad(LocalDate.parse(cadena.split("!!")[3]));
            producto.setPrecioProducto(Integer.parseInt(cadena.split("!!")[4]));
            producto.setStock(Integer.parseInt(cadena.split("!!")[5]));
            productos.add(producto);

        }
        return productos;
    }

    public void guardarCompras(ArrayList<Compra>listaCompras)throws IOException{
        StringBuilder datosCompra = new StringBuilder();
        for(Compra cp:listaCompras){
            datosCompra.append(cp.getComprador()).append("!!").
                    append(cp.getProducto()).append("!!").
                    append(cp.getCantidad()).append("!!").
                    append(cp.getTotal()).append("!!").
                    append(cp.getFechaCompra()).append("\n");
        }
        archivoUtil.guardarArchivo(rutaCompras, datosCompra.toString(), false );
    }
    public ArrayList<Compra> leerCompra()throws IOException {
        ArrayList<Compra> compras = new ArrayList<>();
        ArrayList<String> datos = archivoUtil.leerArchivo(rutaCompras);
        String cadena;
        for (String c : datos) {
            cadena = c;
            Compra compra = new Compra();
            compra.setComprador(cadena.split("!!")[0]);
            compra.setProducto(cadena.split("!!")[1]);
            compra.setCantidad(Integer.parseInt(cadena.split("!!")[2]));
            compra.setTotal(Integer.parseInt(cadena.split("!!")[3]));
            compra.setFechaCompra(LocalDate.parse(cadena.split("!!")[4]));
            compras.add(compra);

        }
        return compras;
    }
}

