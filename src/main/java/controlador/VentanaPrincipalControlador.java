package controlador;

import javafx.animation.KeyValue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import modelo.Usuario;
import modelo.ViabilidadFinanciera;


public class VentanaPrincipalControlador {

    @FXML
    private ChoiceBox<String> choiceTipoAmortizacion;

    @FXML
    private Rectangle rctViabilidad;

    @FXML
    private TextField txtCapital;

    @FXML
    private AnchorPane anchorViabilidad;

    @FXML
    private Label lblTitulo;

    @FXML
    private Label lblProbabilidad;

    @FXML
    private Label lblCapacidadMaxima;

    @FXML
    private Label lblCapacidad;

    @FXML
    private TextField txtCrecimiento;

    @FXML
    private TextField txtDeudas;

    @FXML
    private TextField txtGastos;

    @FXML
    private TextField txtIngresos;

    @FXML
    private TextField txtIntereses;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPlazo;

    @FXML
    void exportarInfo(ActionEvent event) {

    }

    @FXML
    void generarProyeccion(ActionEvent event) {

    }
    private GaussianBlur blur;

    @FXML
    void generarViabilidad(ActionEvent event) {
        String nombre = txtNombre.getText();
        double ingresosFijos = Double.parseDouble(txtIngresos.getText());
        double gastosFijos = Double.parseDouble(txtGastos.getText());
        double deudas = Double.parseDouble(txtDeudas.getText());
        Usuario usuario = new Usuario(nombre, ingresosFijos, gastosFijos, deudas);
        double capacidad = usuario.getViabilidadFinanciera().getCe();
        lblCapacidad.setText(String.format("%.2f%%", capacidad));
        lblProbabilidad.setText(usuario.getViabilidadFinanciera().getRango());
        double capacidadMaxima = usuario.getViabilidadFinanciera().getCmp();
        lblCapacidadMaxima.setText(String.format("$%.2f", capacidadMaxima));
        if (usuario.getViabilidadFinanciera().getRango().equals("Alta probabilidad")) {
            lblProbabilidad.setStyle("-fx-text-fill: green;");
        }else{
            lblProbabilidad.setStyle("-fx-text-fill: red;");
        }
        rctViabilidad.setVisible(false);
        aplicarAnimacionDesenfoque(anchorViabilidad);


    }
    public void initialize() {
        blur = new GaussianBlur(50);
        anchorViabilidad.setEffect(blur);
        txtCrecimiento.setDisable(true);
        choiceTipoAmortizacion.getItems().addAll("Frances", "Americano", "Aleman", "Progresion Arimetica", "Progresion Geometrica");
        choiceTipoAmortizacion.setValue("Frances");
        choiceTipoAmortizacion.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if ("Progresion Arimetica".equals(newVal)) {
                txtCrecimiento.setDisable(false);
            } else if("Progresion Geometrica".equals(newVal)){
                txtCrecimiento.setDisable(false);
            }
            else {
                txtCrecimiento.setDisable(true);
            }
        });
    }
    private void aplicarAnimacionDesenfoque(Node nodo) {
        GaussianBlur blur = new GaussianBlur(50); // Fuerte desenfoque inicial
        nodo.setEffect(blur);

        Timeline animacion = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(blur.radiusProperty(), 50)
                ),
                new KeyFrame(Duration.seconds(1.2),
                        new KeyValue(blur.radiusProperty(), 0)
                )
        );

        animacion.setOnFinished(ev -> nodo.setEffect(null));
        animacion.play();
    }

}

