package co.edu.uniquindio.tienda.controlador;

import co.edu.uniquindio.tienda.controladorSecundario.guardarUsuarioControladorSecundario;
import co.edu.uniquindio.tienda.enumerados.TipoEstadoCivil;
import co.edu.uniquindio.tienda.enumerados.TipoUsuario;
import co.edu.uniquindio.tienda.fabrica.ModelFactory;
import co.edu.uniquindio.tienda.modelo.Comprador;
import co.edu.uniquindio.tienda.modelo.Tienda;
import co.edu.uniquindio.tienda.modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerRegistroUser implements Initializable {

    public ModelFactory modelFactory = new ModelFactory();
    public guardarUsuarioControladorSecundario controladorSecundario;
    private final ObservableList<TipoEstadoCivil> estadoCiviles = FXCollections.observableArrayList();
    private final ObservableList<TipoUsuario> usuarios = FXCollections.observableArrayList();

    @FXML
    private Button btnRegistrarse;

    @FXML
    private ComboBox<TipoEstadoCivil> cbxEstadoCilvil;

    @FXML
    private ComboBox<TipoUsuario> cbxTipoUsuario;

    @FXML
    private DatePicker dateFechaNacimiento;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtContrasena;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtNombre;

    @FXML
    void actionRegistrar(ActionEvent event) {
        TipoUsuario usuario = cbxTipoUsuario.getSelectionModel().getSelectedItem();
        Usuario c;
        Comprador comprador = new Comprador();
        String nombre  = txtNombre.getText();
        String apellido = txtApellido.getText();
        LocalDate fechaNacimiento = dateFechaNacimiento.getValue();
        TipoEstadoCivil estadoCivil = cbxEstadoCilvil.getValue();
        String direccion = txtDireccion.getText();
        TipoUsuario tipoUsuario = cbxTipoUsuario.getValue();
        String correo = txtCorreo.getText();
        String contrasena = txtContrasena.getText();

        comprador.setNombre(nombre);
        comprador.setApellido(apellido);
        comprador.setFechaNacimiento(fechaNacimiento);
        comprador.setEstadoCivil(estadoCivil);
        comprador.setDireccion(direccion);
        comprador.setUsuario(tipoUsuario);
        comprador.setEmail(correo);
        comprador.setContrasena(contrasena);
        c = controladorSecundario.guardarUsuario(comprador);
        if (c == null){
            System.out.println("Null");
        }
        else {
            System.out.println("No es nula");
        }
    }

    public void mostrarEstadosCiviles(){
        estadoCiviles.add(TipoEstadoCivil.soltero);
        estadoCiviles.add(TipoEstadoCivil.unionLibre);
        estadoCiviles.add(TipoEstadoCivil.casado);
        estadoCiviles.add(TipoEstadoCivil.divorciado);
        cbxEstadoCilvil.setItems(estadoCiviles);
    }
    public void mostrarTipoUsuarios(){
        usuarios.add(TipoUsuario.vendedor);
        usuarios.add(TipoUsuario.comprador);
        cbxTipoUsuario.setItems(usuarios);
    }
    public void iniciar(){
        modelFactory = ModelFactory.getInstance();
        controladorSecundario = new guardarUsuarioControladorSecundario(modelFactory);
        new ControllerRegistroUser();
        mostrarEstadosCiviles();
        mostrarTipoUsuarios();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciar();
    }

 }









