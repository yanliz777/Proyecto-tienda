package co.edu.uniquindio.tienda.controlador;

import co.edu.uniquindio.tienda.HelloApplication;
import co.edu.uniquindio.tienda.controladorSecundario.ControladorSecundarioLogin;
import co.edu.uniquindio.tienda.enumerados.TipoUsuario;
import co.edu.uniquindio.tienda.fabrica.ModelFactory;
import co.edu.uniquindio.tienda.modelo.Comprador;
import co.edu.uniquindio.tienda.modelo.Usuario;
import co.edu.uniquindio.tienda.modelo.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {
    //Para darle manejo a las ventanas: helloApplication.
    private  HelloApplication helloApplication = new HelloApplication();
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
    /*
    Método que me permite iniciar sesión (logear) de acuerdo al usuario
    que ingrese.
    * */
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

        if (cbxTipoUsuario.getSelectionModel().getSelectedItem().equals(TipoUsuario.comprador)){
            if (bandera){
                cerrarVentana(btnIngresar);//cierra la ventana Login
                helloApplication.cargarVentanaComprador();//carga(pone) la ventana del comprador
            }else {
                System.out.println("no esta registrado");
            }
        }else if (cbxTipoUsuario.getSelectionModel().getSelectedItem().equals(TipoUsuario.vendedor)){
            if (bandera){
                cerrarVentana(btnIngresar);
                helloApplication.cargarVentanaVendedor();
            }else {
                System.out.println("no esta registrado");
            }

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

    public void cerrarVentana(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
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