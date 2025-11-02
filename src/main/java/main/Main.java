package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Rutas;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Rutas.VENTANA_PRINCIPAL_VISTA));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
