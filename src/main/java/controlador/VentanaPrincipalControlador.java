package controlador;

import javafx.animation.KeyValue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.chart.NumberAxis;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import modelo.*;

import java.util.List;


public class VentanaPrincipalControlador {

    private Usuario usuario;


    @FXML
    private ChoiceBox<String> choiceTipoAmortizacion;
    @FXML
    private AnchorPane anchorGrande;
    @FXML
    private Group grupo1;

    @FXML
    private Group grupo2;

    @FXML
    private Rectangle rctTodo1;

    @FXML
    private Rectangle rctTodo11;

    @FXML
    private Rectangle rctTodo2;

    @FXML
    private Rectangle rctViabilidad;

    @FXML
    private TextField txtCapital;

    @FXML
    private TableView<PeriodoCredito> tablaAmortizacion;

    @FXML
    private TableColumn<?, ?> colAmortizacion;

    @FXML
    private TableColumn<?, ?> colAmortizacionD;

    @FXML
    private TableColumn<?, ?> colCuota;

    @FXML
    private Label lblSensibilidadCapital;

    @FXML
    private Label lblSensibilidadCuota;

    @FXML
    private Spinner<String> spSeleccionarGrafico;

    @FXML
    private Label lblSensibilidadTiempo;
    @FXML
    private Label lblIndicadorSensibilidad1;

    @FXML
    private Label lblIndicadorSensibilidad2;

    @FXML
    private Label lblIndicadorSensibilidad3;

    @FXML
    private TableColumn<?, ?> colCuotaD;

    @FXML
    private TableColumn<?, ?> colIntereses;

    @FXML
    private TableColumn<?, ?> colInteresesD;

    @FXML
    private TableColumn<?, ?> colPeriodo;

    @FXML
    private TableColumn<?, ?> colSaldo;

    @FXML
    private TableColumn<?, ?> colSaldoD;


    @FXML
    private LineChart<Number, Number> lineChart;

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
        double capital = 0;
        double intereses = 0;
        int plazo = 0;
        double crecimiento = 0;
        Credito credito;

        try{
            if((choiceTipoAmortizacion.getValue().equals("Progresion Arimetica")) || (choiceTipoAmortizacion.getValue().equals("Progresion Geometrica"))) {
                capital= Double.parseDouble(txtCapital.getText());
                intereses = Double.parseDouble(txtIntereses.getText());
                plazo = Integer.parseInt(txtPlazo.getText());
                crecimiento = Double.parseDouble(txtCrecimiento.getText());
            }else{
                capital= Double.parseDouble(txtCapital.getText());
                intereses = Double.parseDouble(txtIntereses.getText());
                plazo = Integer.parseInt(txtPlazo.getText());
            }
        }catch(Exception e){
            return;
        }
        if(choiceTipoAmortizacion.getValue().equals("Frances")){
            credito= new Credito(usuario, new MetodoFrances(capital,intereses,plazo));
        }else if(choiceTipoAmortizacion.getValue().equals("Americano")){
            credito= new Credito(usuario, new MetodoAmericano(capital,intereses,plazo));
        }else if(choiceTipoAmortizacion.getValue().equals("Aleman")){
            credito= new Credito(usuario, new MetodoAleman(capital,intereses,plazo));
        }else if(choiceTipoAmortizacion.getValue().equals("Progresion Arimetica")){
            credito= new Credito(usuario, new MetodoAritmetico(capital,intereses,plazo,crecimiento));
        }else{
            credito= new Credito(usuario, new MetodoGeometrico(capital,intereses,plazo,crecimiento));
        }
        ProyectarFunciones proyectarFunciones=new ProyectarFunciones(credito);
        List<PeriodoCredito> lista = proyectarFunciones.getTabla();
        ObservableList<PeriodoCredito> data = FXCollections.observableArrayList(lista);
        tablaAmortizacion.setItems(data);
        AnalizarSensibilidad sensibilidad=new AnalizarSensibilidad(credito);
        lblSensibilidadCapital.setText(String.format("$%.2f", sensibilidad.sensibilidadCapital()));
        modificarColor(sensibilidad.sensibilidadCapital(),lblIndicadorSensibilidad1);
        lblSensibilidadCuota.setText(String.format("$%.2f", sensibilidad.sensibilidadTasa()));
        modificarColor(sensibilidad.sensibilidadTasa(),lblIndicadorSensibilidad2);
        lblSensibilidadTiempo.setText(String.format("$%.2f", sensibilidad.sensibilidadTiempo()));
        modificarColor(sensibilidad.sensibilidadTiempo(),lblIndicadorSensibilidad3);
        SpinnerValueFactory<String> valueFactory =
                new SpinnerValueFactory.ListSpinnerValueFactory<>(
                        FXCollections.observableArrayList(
                                "Cuota", "Amortizacion", "Intereses", "Saldo",
                                "Cuota Derivada", "Amortizacion Derivada", "Intereses Derivada", "Saldo Derivada"
                        )
                );
        spSeleccionarGrafico.setValueFactory(valueFactory);
        spSeleccionarGrafico.getValueFactory().setValue("Cuota");

// Configurar ejes del LineChart (ya definidos en FXML)
        NumberAxis xAxis = (NumberAxis) lineChart.getXAxis();
        NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();

        xAxis.setLabel("Periodo (t)");
        yAxis.setLabel("Valor");

// Ajuste de eje Y para valores pequeños o negativos
        yAxis.setAutoRanging(true);
        yAxis.setForceZeroInRange(false);

// Desactivar animaciones
        lineChart.setAnimated(false);

// Listener del spinner
        spSeleccionarGrafico.valueProperty().addListener((obs, oldVal, newVal) -> {
            lineChart.getData().clear();

            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(newVal);

            // ✅ Versión corregida — usa Double.isNaN y Double.isInfinite
            java.util.function.BiConsumer<Number, Number> addSafe = (t, val) -> {
                if (t != null && val != null) {
                    double tt = t.doubleValue();
                    double vv = val.doubleValue();
                    if (!Double.isNaN(tt) && !Double.isInfinite(tt)
                            && !Double.isNaN(vv) && !Double.isInfinite(vv)) {
                        series.getData().add(new XYChart.Data<>(tt, vv));
                    }
                }
            };

            switch (newVal) {
                case "Cuota" -> lista.forEach(pc -> addSafe.accept(pc.getT(), pc.getA_t()));
                case "Amortizacion" -> lista.forEach(pc -> addSafe.accept(pc.getT(), pc.getA_t_acum()));
                case "Intereses" -> lista.forEach(pc -> addSafe.accept(pc.getT(), pc.getI_t()));
                case "Saldo" -> lista.forEach(pc -> addSafe.accept(pc.getT(), pc.getS_t()));
                case "Cuota Derivada" -> lista.forEach(pc -> addSafe.accept(pc.getT(), pc.getA_t_prime()));
                case "Amortizacion Derivada" -> lista.forEach(pc -> addSafe.accept(pc.getT(), pc.getA_t_acum_prime()));
                case "Intereses Derivada" -> lista.forEach(pc -> addSafe.accept(pc.getT(), pc.getI_t_prime()));
                case "Saldo Derivada" -> lista.forEach(pc -> addSafe.accept(pc.getT(), pc.getS_t_prime()));
            }

            if (series.getData().isEmpty()) {
                System.out.println("⚠ No hay datos válidos para: " + newVal);
            }

            lineChart.getData().add(series);

            // Estilo de línea
            series.nodeProperty().addListener((o, oldNode, newNode) -> {
                if (newNode != null) {
                    newNode.setStyle("-fx-stroke: #00bfff; -fx-stroke-width: 2px;");
                }
            });
        });
        aplicarAnimacionDesenfoque2(rctTodo11);












    }
    private GaussianBlur blur;

    @FXML
    void generarViabilidad(ActionEvent event) {
        String nombre = "";
        double ingresosFijos = 0;
        double gastosFijos = 0;
        double deudas = 0;
        try{
            nombre = txtNombre.getText();
            ingresosFijos = Double.parseDouble(txtIngresos.getText());
            gastosFijos = Double.parseDouble(txtGastos.getText());
            deudas = Double.parseDouble(txtDeudas.getText());
        }catch(Exception e){
            return;
        }
        usuario = new Usuario(nombre, ingresosFijos, gastosFijos, deudas);
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
        anchorViabilidad.toFront();
        rctViabilidad.setVisible(false);
        aplicarAnimacionDesenfoque(anchorViabilidad);


    }
    public void initialize() {
        blur = new GaussianBlur(50);
        anchorViabilidad.setEffect(blur);
        GaussianBlur blur = new GaussianBlur(25);
        // Traer ambos rectángulos al frente

        rctTodo11.toFront();

// Crear un solo efecto blu

// --- RECTÁNGULO 1 ---

// --- RECTÁNGULO 2 ---
        rctTodo11.setEffect(blur);
        rctTodo11.setX(rctTodo11.getX() - 30);
        rctTodo11.setY(rctTodo11.getY() - 30);
        rctTodo11.setWidth(rctTodo11.getWidth() + 60);
        rctTodo11.setHeight(rctTodo11.getHeight() + 60);





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
        colPeriodo.setCellValueFactory(new PropertyValueFactory<>("T"));
        colCuota.setCellValueFactory(new PropertyValueFactory<>("A_t"));
        colIntereses.setCellValueFactory(new PropertyValueFactory<>("I_t"));
        colAmortizacion.setCellValueFactory(new PropertyValueFactory<>("A_t_acum"));
        colSaldo.setCellValueFactory(new PropertyValueFactory<>("S_t"));
        colCuotaD.setCellValueFactory(new PropertyValueFactory<>("A_t_prime"));
        colInteresesD.setCellValueFactory(new PropertyValueFactory<>("I_t_prime"));
        colAmortizacionD.setCellValueFactory(new PropertyValueFactory<>("A_t_acum_prime"));
        colSaldoD.setCellValueFactory(new PropertyValueFactory<>("S_t_prime"));
        // Apariencia visual
        lineChart.setCreateSymbols(false);
        lineChart.setLegendVisible(false);
        lineChart.getXAxis().setLabel(null);
        lineChart.getYAxis().setLabel(null);

// Quitar bordes y rellenos innecesarios (opcional)
        lineChart.setHorizontalGridLinesVisible(false);
        lineChart.setVerticalGridLinesVisible(false);
        lineChart.setAlternativeColumnFillVisible(false);
        lineChart.setAlternativeRowFillVisible(false);

        lineChart.applyCss();
        lineChart.layout();


    }
    private void aplicarAnimacionDesenfoque(Node nodo) {
        GaussianBlur blur = new GaussianBlur(50);
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
    public void modificarColor(double m,Label label){
        if(m<0){
            label.setText("disminuye");
            label.setStyle("-fx-text-fill: red;");
        }else{
            label.setText("aumenta");
            label.setStyle("-fx-text-fill: green;");

        }

    }
    private void aplicarAnimacionDesenfoque2(Rectangle... rectangulos) {
        for (Rectangle rect : rectangulos) {
            // Asegúrate de que sean visibles antes de iniciar
            rect.setVisible(true);
            rect.setOpacity(1);

            // Aplica desenfoque suave
            GaussianBlur blur = new GaussianBlur(30);
            rect.setEffect(blur);

            // Animación del blur (de más a menos)
            Timeline animBlur = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(blur.radiusProperty(), 30)),
                    new KeyFrame(Duration.seconds(1.0), new KeyValue(blur.radiusProperty(), 0))
            );

            // Animación del desvanecimiento (fade out)
            Timeline fadeOut = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(rect.opacityProperty(), 1)),
                    new KeyFrame(Duration.seconds(1.0), new KeyValue(rect.opacityProperty(), 0))
            );

            // Cuando termina, quitar efecto y ocultar
            fadeOut.setOnFinished(e -> {
                rect.setVisible(false);
                rect.setEffect(null);
                rect.setOpacity(1); // por si se vuelve a usar
            });

            // Ejecutar ambas animaciones juntas
            animBlur.play();
            fadeOut.play();
        }
    }







}

