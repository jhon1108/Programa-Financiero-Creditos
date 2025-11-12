package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utils.Rutas;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Rutas.VENTANA_PRINCIPAL_VISTA));
        Font.loadFont(getClass().getResourceAsStream(Rutas.TIPOGRAFIA1), 1);
        Font.loadFont(getClass().getResourceAsStream(Rutas.TIPOGRAFIA2), 1);
        Font.loadFont(getClass().getResourceAsStream(Rutas.TIPOGRAFIA3), 1);
        Font.loadFont(getClass().getResourceAsStream(Rutas.TIPOGRAFIA4), 1);
        stage.getIcons().add(new Image(getClass().getResourceAsStream(Rutas.ICONO)));
        BorderPane pane = loader.load();
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource(Rutas.ESTILOS).toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
