package co.edu.uniquindio.tienda;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("viewLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 600);
        stage.setTitle("Ventana Login");
        stage.setScene(scene);
        stage.show();
    }
    /* Este metopo me permite cambiar de usuario (ejemplo cierro una vista y me carga nuevamente la vita
    del login)*/
    public void cargarVentanaLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            newStage.setTitle("Ventana Login");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // me permite mostrar la ventana del vendedor
    public void cargarVentanaVendedor() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ViewVendedor.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            newStage.setTitle("Ventana Vendedor");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // metodo que me carga la ventana comprador
    public void cargarVentanaComprador() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ViewComprador.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            newStage.setTitle("Ventana Comprador");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public static void main(String[] args) {
        launch();
    }
}