package co.edu.uniquindio.tienda.controlador;

import co.edu.uniquindio.tienda.controladorSecundario.ControladorSecundarioVendedor;
import co.edu.uniquindio.tienda.enumerados.TipoProducto;
import co.edu.uniquindio.tienda.fabrica.ModelFactory;
import co.edu.uniquindio.tienda.modelo.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerVendedor implements Initializable
{

    //nos permite trabajar con la tabla y así poder hacer eliminar y actualizar el producto=
    private Producto productoSeleccionado;
    //Para comunicarnos con este controlador=
    private ControladorSecundarioVendedor controladorSecundarioVendedor;
    //la clase que me administra el proyecto, siempre va =
    private ModelFactory modelFactory = new ModelFactory();
    //para poder mostrar en la interfaz gráfica los items en el combo box =
    private final ObservableList<TipoProducto> tipoProductoObservableList = FXCollections.observableArrayList();
    private final ObservableList<Producto> listaProductos  = FXCollections.observableArrayList();
    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private ComboBox<TipoProducto> cbxTipoProducto;

    @FXML
    private DatePicker dateFechaCaducidad;

    @FXML//               objeto,tipoDato
    private TableColumn<Producto, String> tblColumCodigo;

    @FXML
    private TableColumn<Producto, LocalDate> tblColumFechaCaducidad;

    @FXML
    private TableColumn<Producto, Integer> tblColumInventario;

    @FXML
    private TableColumn<Producto, String> tblColumNombre;

    @FXML
    private TableColumn<Producto, Integer> tblColumPrecio;

    @FXML
    private TableColumn<Producto, TipoProducto> tblColumTipoProducto;

    @FXML
    private TableView<Producto> tblProductos;

    @FXML
    private TextField txtCodigoProducto;

    @FXML
    private TextField txtInventario;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtPrecio;
    @FXML
    /**
     * Método que me permite actualizar el producto que el usuario selecciona en la GUI
     * este método captura los datos que el usuario modifica de los txt y se comunica
     * con el controladorSecundario
     */
    void actionActualizarProducto(ActionEvent event) {
        String id = txtCodigoProducto.getText();
        String nombre = txtNombreProducto.getText();
        TipoProducto tipo = cbxTipoProducto.getSelectionModel().getSelectedItem();
        int precioProducto = Integer.parseInt(txtPrecio.getText());
        int stock = Integer.parseInt(txtInventario.getText());
        LocalDate fechaCaducidad = dateFechaCaducidad.getValue();

        Producto productoActualizar = new Producto();
        productoActualizar.setId(id);
        productoActualizar.setNombre(nombre);
        productoActualizar.setPrecioProducto(precioProducto);
        productoActualizar.setTipoProducto(tipo);
        productoActualizar.setFechaCaducidad(fechaCaducidad);
        productoActualizar.setStock(stock);

        boolean actualizarProducto = controladorSecundarioVendedor.actualizarProductos(productoActualizar);

        if (actualizarProducto) {

            System.out.println("producto Actualizado");
            tblProductos.refresh();
        }
        else {
            System.out.println("error");
        }
    }

    @FXML
    void actionAgregarProducto(ActionEvent event)
    {
        Producto producto = new Producto();
        Producto producto1;
        //atributos, los capturo de los txt de la GUI
        String id = txtCodigoProducto.getText();
        String nombre = txtNombreProducto.getText();
        TipoProducto tipo = cbxTipoProducto.getSelectionModel().getSelectedItem();
        int precioProducto = Integer.parseInt(txtPrecio.getText());
        int stock = Integer.parseInt(txtInventario.getText());
        LocalDate fechaCaducidad = dateFechaCaducidad.getValue();
        //setiamos los datos para el objeto producto:
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setTipoProducto(tipo);
        producto.setPrecioProducto(precioProducto);
        producto.setStock(stock);
        producto.setFechaCaducidad(fechaCaducidad);
        //Nos comunicamos con el controladorSecundarioVendedor:
        producto1 = controladorSecundarioVendedor.agregarProducto(producto);
        if(producto1 != null)
        {
            listaProductos.add(producto1);//se agraga el productoa a la tabla
        }
        else
        {
            System.out.println("El producto no fue creado");
        }
    }

    /**
     * Método que me permite mostrar los
     * productos en la tabla de la GUI:
     */
    private void mostrarProductosTabla() {
        tblColumCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));//código
        tblColumNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tblColumTipoProducto.setCellValueFactory(new PropertyValueFactory<>("tipoProducto"));
        tblColumFechaCaducidad.setCellValueFactory(new PropertyValueFactory<>("fechaCaducidad"));
        tblColumPrecio.setCellValueFactory(new PropertyValueFactory<>("precioProducto"));
        tblColumInventario.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tblProductos.getItems().clear();//para limpiar la tabla
        tblProductos.setItems(getProductos());//para agregar los productos a la tabla

        tblProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoSeleccionado = (Producto) newSelection;//generamos el producto seleccionado de la tabla

            mostrarInformacionProducto(productoSeleccionado);//carga el producto en los txt

        });
    }

    /**
     *Captura la lista de los productos que vienen
     * desde el modelo(tienda) y se almacena en listaProductos:
     */
    public ObservableList<Producto> getProductos(){
        listaProductos.addAll(controladorSecundarioVendedor.obtenerProducto());
        return listaProductos;
    }

    /**
     *Me permite mostrar/ver en los textField de la gui el producto que seleccione en la tabla:
     */
    private void mostrarInformacionProducto(Producto productoseleccionado) {
        if(productoseleccionado != null) {
            txtCodigoProducto.setText(productoseleccionado.getId());
            txtNombreProducto.setText(productoseleccionado.getNombre());
            cbxTipoProducto.setValue(productoseleccionado.getTipoProducto());
            dateFechaCaducidad.setValue(productoseleccionado.getFechaCaducidad());
            txtPrecio.setText(String.valueOf(productoseleccionado.getPrecioProducto()));
            txtInventario.setText(String.valueOf(productoseleccionado.getStock()));
        }
    }

    /**
     * Me permite mostrar en el combo box los Tipos de Producto:
     */
    public void mostrarTiposProductos()//para mostrar en el combo box
    {
        tipoProductoObservableList.add(TipoProducto.aseo);
        tipoProductoObservableList.add(TipoProducto.verdura);
        tipoProductoObservableList.add(TipoProducto.granos);
        tipoProductoObservableList.add(TipoProducto.lacteos);
        tipoProductoObservableList.add(TipoProducto.mascotas);
        tipoProductoObservableList.add(TipoProducto.bebidas);
        tipoProductoObservableList.add(TipoProducto.carnes);
        cbxTipoProducto.setItems(tipoProductoObservableList);
    }

    @FXML
    void actionEliminarProducto(ActionEvent event) {

        boolean eliminarProducto = controladorSecundarioVendedor.elimianrProductos(productoSeleccionado);

        if (eliminarProducto == true){
            listaProductos.remove(productoSeleccionado);
            productoSeleccionado = null;
            tblProductos.getSelectionModel().clearSelection();
            System.out.println("producto eliminado");
        }
        else {
            System.out.println("error");
        }
    }
    //Para inicializar el controlador:
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        iniciar();
    }

    /**
     * Método que me me permite inicializar el controlador
     */
    private void iniciar()
    {
        modelFactory = ModelFactory.getInstance();
        controladorSecundarioVendedor = new ControladorSecundarioVendedor(modelFactory);
        mostrarTiposProductos();
        mostrarProductosTabla();
    }
}