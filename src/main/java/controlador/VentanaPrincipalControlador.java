package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class VentanaPrincipalControlador {

    @FXML
    private ChoiceBox<String> choiceTipoAmortizacion;

    @FXML
    private TextField txtCapital;

    @FXML
    private Label lblTitulo;

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

    @FXML
    void generarViabilidad(ActionEvent event) {

    }
    public void initialize() {
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

}

