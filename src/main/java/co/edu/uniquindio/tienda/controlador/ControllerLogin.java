package co.edu.uniquindio.tienda.controlador;

import co.edu.uniquindio.tienda.controladorSecundario.ControladorSecundarioLogin;
import co.edu.uniquindio.tienda.enumerados.TipoUsuario;
import co.edu.uniquindio.tienda.fabrica.ModelFactory;
import co.edu.uniquindio.tienda.modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerLogin implements Initializable {

    public ModelFactory modelFactory = new ModelFactory();
    private final ObservableList<TipoUsuario> tipoUsuarios = FXCollections.observableArrayList();

    public ControladorSecundarioLogin loginControladorSecundario;


    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private ComboBox<TipoUsuario> cbxTipoUsuario;

    @FXML
    private TextField txtContrasena;

    @FXML
    private TextField txtEmail;

    @FXML
    void actionIngresar(ActionEvent event)
    {
        TipoUsuario usuario = cbxTipoUsuario.getSelectionModel().getSelectedItem();
        String email = txtEmail.getText();
        String contrasena = txtContrasena.getText();

        Usuario usuario2 = new Usuario();
        usuario2.setUsuario(usuario);
        usuario2.setEmail(email);
        usuario2.setContrasena(contrasena);

        boolean bandera = loginControladorSecundario.loginUsuario(usuario,email,contrasena);

        if (bandera){
            System.out.println("Bienvenido");
        }
        else {
            System.out.println("Usuario no registrado o datos incorrectos");
        }
    }
    @FXML
    void actionRegistrarse(ActionEvent event) {


    }
    public void cargarTipoUsuario(){
        tipoUsuarios.add(TipoUsuario.vendedor);
        tipoUsuarios.add(TipoUsuario.comprador);
        cbxTipoUsuario.setItems(tipoUsuarios);
    }

    public void iniciar()
    {
        modelFactory = ModelFactory.getInstance();
        loginControladorSecundario = new ControladorSecundarioLogin(modelFactory);
        new ControllerLogin();
        cargarTipoUsuario();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciar();
    }
}