package co.edu.uniquindio.tienda.controlador;

import co.edu.uniquindio.tienda.controladorSecundario.ControladorSecundarioComprador;
import co.edu.uniquindio.tienda.enumerados.TipoProducto;
import co.edu.uniquindio.tienda.fabrica.ModelFactory;
import co.edu.uniquindio.tienda.modelo.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerComprador implements Initializable {
    //nos permite trabajar con la tabla y así poder hacer eliminar y actualizar el producto=
    private Producto productoSeleccionado;
    //Para comunicarnos con estos controladores=
    private ControllerVendedor controllerVendedor;
    private ControladorSecundarioComprador controladorSecundarioComprador;

    private final ObservableList<Producto> listaProductos  = FXCollections.observableArrayList();
    //la clase que me administra el proyecto, siempre va =
    private ModelFactory modelFactory = new ModelFactory();
    @FXML
    private TableColumn<Producto,String> tblColumnaCodigo;
    @FXML
    private TableColumn<Producto, LocalDate> tblColumnaFechaCaducidad;
    @FXML
    private TableColumn<Producto,Integer> tblColumnaInventario;
    @FXML
    private TableColumn<Producto, String> tblColumnaNombre;
    @FXML
    private TableColumn<Producto,Integer> tblColumnaPrecio;
    @FXML
    private TableColumn<Producto,TipoProducto> tblColumnaTipoProducto;
    @FXML
    private TableView<Producto> tblProductos;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TextField txtPrecioTotal;
    /**
     * Método que me permite mostrar los
     * productos en la tabla de la GUI:
     */
    private void mostrarProductosTabla() {
        tblColumnaCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));//código
        tblColumnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tblColumnaTipoProducto.setCellValueFactory(new PropertyValueFactory<>("tipoProducto"));
        tblColumnaFechaCaducidad.setCellValueFactory(new PropertyValueFactory<>("fechaCaducidad"));
        tblColumnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precioProducto"));
        tblColumnaInventario.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tblProductos.getItems().clear();//para limpiar la tabla
        tblProductos.setItems(getProductos());//para agregar los productos a la tabla

        tblProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoSeleccionado = (Producto) newSelection;//generamos el producto seleccionado de la tabla
        });
    }
    /**
     *Captura la lista de los productos que vienen
     * desde el modelo(tienda) y se almacena en listaProductos:
     */
    public ObservableList<Producto> getProductos(){
        listaProductos.addAll(controladorSecundarioComprador.obtenerProducto());
        return listaProductos;
    }
    private void iniciar()
    {
        modelFactory = ModelFactory.getInstance();
        controladorSecundarioComprador = new ControladorSecundarioComprador(modelFactory);
        mostrarProductosTabla();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciar();
    }

    public void actionComprar(ActionEvent actionEvent) {
    }
}
