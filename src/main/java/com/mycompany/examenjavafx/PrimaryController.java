package com.mycompany.examenjavafx;

import com.mycompany.examenjavafx.models.Alumno;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class PrimaryController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidos;
    @FXML
    private Spinner<Integer> spAD;
    @FXML
    private Spinner<Integer> spSGE;
    @FXML
    private Spinner<Integer> spDI;
    @FXML
    private Spinner<Integer> spPMDM;
    @FXML
    private Spinner<Integer> spPSP;
    @FXML
    private Spinner<Integer> spEIE;
    @FXML
    private Spinner<Integer> spHLC;
    @FXML
    private TableView<Alumno> tabla;
    @FXML
    private TableColumn<Alumno, String> tcNombre;
    @FXML
    private TableColumn<Alumno, String> tcApellidos;
    @FXML
    private TableColumn<Alumno, Integer> tcAD;
    @FXML
    private TableColumn<Alumno, Integer> tcSGE;
    @FXML
    private TableColumn<Alumno, Integer> tcDI;
    @FXML
    private TableColumn<Alumno, Integer> tcPMDM;
    @FXML
    private TableColumn<Alumno, Integer> tcPSP;
    @FXML
    private TableColumn<Alumno, Integer> tcEIE;
    @FXML
    private TableColumn<Alumno, Integer> tcHLC;

    ObservableList<Alumno> listado;

    private AlumnoDAO gestorAlumnos = new AlumnoDAOJDBC();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        listado = FXCollections.observableArrayList();

        // Alumnos añadidos a mano
        //listado.addAll(crearAlumnos());
        listado.addAll(traerAlumnosDeBaseDeDatos());

        tabla.getItems().clear();
        tabla.getItems().addAll(listado);

        tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        tcApellidos.setCellValueFactory(new PropertyValueFactory("apellidos"));
        tcAD.setCellValueFactory(new PropertyValueFactory("ad"));
        tcSGE.setCellValueFactory(new PropertyValueFactory("sge"));
        tcDI.setCellValueFactory(new PropertyValueFactory("di"));
        tcPMDM.setCellValueFactory(new PropertyValueFactory("pmdm"));
        tcPSP.setCellValueFactory(new PropertyValueFactory("psp"));
        tcEIE.setCellValueFactory(new PropertyValueFactory("eie"));
        tcHLC.setCellValueFactory(new PropertyValueFactory("hlc"));

        rellenarSpinners();

    }

    @FXML
    private void btnAdd(ActionEvent event) {
        String nombre = tfNombre.getText();
        String apellidos = tfApellidos.getText();
        Integer ad = spAD.getValue();
        Integer sge = spSGE.getValue();
        Integer di = spDI.getValue();
        Integer pmdm = spPMDM.getValue();
        Integer psp = spPSP.getValue();
        Integer eie = spEIE.getValue();
        Integer hlc = spHLC.getValue();

        Alumno alumno = new Alumno(4, nombre, apellidos, ad, sge, di, pmdm, psp, eie, hlc);
        tabla.getItems().add(alumno);
    }

    @FXML
    private void btnPdf(ActionEvent event) {
        try {
            pdfReport();
        } catch (JRException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnSalir(ActionEvent event) {
        System.exit(0);
    }

    private ObservableList<Alumno> crearAlumnos() {
        ObservableList<Alumno> listado = FXCollections.observableArrayList(
                new Alumno(1, "Alumno", "Uno", 9, 9, 9, 9, 9, 9, 9),
                new Alumno(2, "Alumno", "Dos", 8, 8, 8, 8, 8, 8, 8),
                new Alumno(2, "Alumno", "Tres", 7, 7, 7, 7, 7, 7, 7));

        return listado;
    }

    private void rellenarSpinners() {
        SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        spAD.setValueFactory(svf);

        SpinnerValueFactory svf2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        spSGE.setValueFactory(svf2);

        SpinnerValueFactory svf3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        spDI.setValueFactory(svf3);

        SpinnerValueFactory svf4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        spPMDM.setValueFactory(svf4);

        SpinnerValueFactory svf5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        spPSP.setValueFactory(svf5);

        SpinnerValueFactory svf6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        spEIE.setValueFactory(svf6);

        SpinnerValueFactory svf7 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        spHLC.setValueFactory(svf7);
    }

    public static void pdfReport() throws JRException, ClassNotFoundException, SQLException {

        HashMap hm = new HashMap();

        //hm.put("tipo", tipo);
        String report = "notas.jasper";

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                report,
                hm,
                JdbcUtils.getConnection()
        );

        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("notas.pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();

        System.out.print("Done!");
    }

    @FXML
    private void clckModal(MouseEvent event) {
        Alumno alumno = tabla.getSelectionModel().getSelectedItem();
        Integer ad = alumno.getAd();
        Integer sge = alumno.getSge();
        Integer di = alumno.getDi();
        Integer pmdm = alumno.getPmdm();
        Integer psp = alumno.getPsp();
        Integer eie = alumno.getEie();
        Integer hlc = alumno.getHlc();

        Integer contador = 0;

        if (ad >= 5 && sge >= 5 && di >= 5 && pmdm >= 5 && psp >= 5 && eie >= 5 && hlc >= 5) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(alumno.getNombre() + " " + alumno.getApellidos());
            alert.setTitle("Notas medias");
            alert.setContentText("La nota media del alumno " + alumno.getNombre() + " " + alumno.getApellidos() + " es " + calculadoraDeMedias(alumno));
            alert.showAndWait();
        } else {

            if (ad < 5) {
                contador++;
            }

            if (sge < 5) {
                contador++;
            }

            if (di < 5) {
                contador++;
            }

            if (pmdm < 5) {
                contador++;
            }

            if (psp < 5) {
                contador++;
            }

            if (eie < 5) {
                contador++;
            }

            if (hlc < 5) {
                contador++;
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(alumno.getNombre() + " " + alumno.getApellidos());
            alert.setTitle("Asignaturas suspensas");
            alert.setContentText("El alumno " + alumno.getNombre() + " " + alumno.getApellidos() + " ha suspendido " + contador + " módulos.");
            alert.showAndWait();

        }

    }

    private Integer calculadoraDeMedias(Alumno alumno) {
        Integer ad = alumno.getAd();
        Integer sge = alumno.getSge();
        Integer di = alumno.getDi();
        Integer pmdm = alumno.getPmdm();
        Integer psp = alumno.getPsp();
        Integer eie = alumno.getEie();
        Integer hlc = alumno.getHlc();

        return (ad + sge + di + pmdm + psp + eie + hlc) / 7;
    }

    private ArrayList<Alumno> traerAlumnosDeBaseDeDatos() {
        return gestorAlumnos.getAlumnos();
    }

}
