package spp.presentacion.controladores.practicante;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import spp.logicadenegocio.clasesdto.ActividadReporteParcial;
import spp.logicadenegocio.clasesdto.ReporteParcial;
import spp.logicadenegocio.gestores.GestorReporteParcial;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import spp.logicadenegocio.validaciones.validacionesreportes.ValidacionReporteParcial;
import spp.utilerias.selecciones.seleccionesreporteparcial.SeleccionTablaListener;
import spp.utilerias.validadorsoloenteros.ValidadorEnteros;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorFormularioReporte {
    
    @FXML private TextField txtPeriodo;
    @FXML private TextField txtHoras;

    @FXML private TextField txtNuevaActividad;
    @FXML private TextField txtTiempoPlan;
    @FXML private TextField txtTiempoReal;

    @FXML private CheckBox chkPlanS1; 
    @FXML private CheckBox chkPlanS2; 
    @FXML private CheckBox chkPlanS3; 
    @FXML private CheckBox chkPlanS4; 
    @FXML private CheckBox chkPlanS5; 
    @FXML private CheckBox chkPlanS6; 
    @FXML private CheckBox chkPlanS7;
    @FXML private CheckBox chkPlanS8;
    
    @FXML private CheckBox chkRealS1; 
    @FXML private CheckBox chkRealS2; 
    @FXML private CheckBox chkRealS3; 
    @FXML private CheckBox chkRealS4; 
    @FXML private CheckBox chkRealS5; 
    @FXML private CheckBox chkRealS6; 
    @FXML private CheckBox chkRealS7; 
    @FXML private CheckBox chkRealS8;

    @FXML private TableView<ActividadReporteParcial> tblActividades;
    @FXML private TableColumn<ActividadReporteParcial, String> colDescripcionActividad;

    @FXML private TextArea taResultados;
    @FXML private TextArea taObservaciones;

    @FXML private Button btnRegresar;
    @FXML private Button btnGenerar;

    private ObservableList<ActividadReporteParcial> listaActividades;

    @FXML
    public void initialize() {
        
        listaActividades = FXCollections.observableArrayList();
        tblActividades.setItems(listaActividades);

        colDescripcionActividad.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tblActividades.getSelectionModel().selectedItemProperty().addListener(new SeleccionTablaListener(this));
        
    }

    @FXML
    public void validarNumeros(KeyEvent evento) {
        
        TextField campoTexto = (TextField) evento.getSource();
        ValidadorEnteros.validarSoloNumeros(campoTexto);
        
    }

    @FXML
    public void leerDatosDeActividad(ActionEvent evento) {
        
        if (sonCamposDeActividadValidos()) {
            
            ActividadReporteParcial actividad = crearActividad();
             
            ValidacionReporteParcial validacion = new ValidacionReporteParcial();
            List<String> listaValidaciones;
            listaValidaciones = validacion.validarTamañoActividad(actividad);
            
            if(!listaValidaciones.isEmpty()){
                
                registrarActividad(actividad);
            
            }else{

                mostrarVentanaErrores(listaValidaciones);
            }

                
        } else {

            mostrarMensajeCamposFaltantes();

        }
        
    }

    @FXML
    public boolean sonCamposDeActividadValidos() {
        
        boolean sonCamposValidos = true; 
        
        if (txtNuevaActividad.getText().isBlank() || 
            txtTiempoPlan.getText().isBlank() || 
            txtTiempoReal.getText().isBlank()) {
            
            sonCamposValidos = false; 

        }
        
        return sonCamposValidos; 
        
    }

    private ActividadReporteParcial crearActividad() {
        
        String descripcion = txtNuevaActividad.getText().trim();
        int tiempoPlan = Integer.parseInt(txtTiempoPlan.getText().trim());
        int tiempoReal = Integer.parseInt(txtTiempoReal.getText().trim());

        ActividadReporteParcial nuevaActividad = new ActividadReporteParcial();
        nuevaActividad.setDescripcion(descripcion);
        nuevaActividad.setHorasPlaneadas(tiempoPlan);
        nuevaActividad.setHorasReales(tiempoReal);

        boolean[] plan = nuevaActividad.getSemanasPlan();
        plan[0] = chkPlanS1.isSelected(); 
        plan[1] = chkPlanS2.isSelected();
        plan[2] = chkPlanS3.isSelected(); 
        plan[3] = chkPlanS4.isSelected();
        plan[4] = chkPlanS5.isSelected(); 
        plan[5] = chkPlanS6.isSelected();
        plan[6] = chkPlanS7.isSelected(); 
        plan[7] = chkPlanS8.isSelected();

        boolean[] real = nuevaActividad.getSemanasReal();
        real[0] = chkRealS1.isSelected(); 
        real[1] = chkRealS2.isSelected();
        real[2] = chkRealS3.isSelected(); 
        real[3] = chkRealS4.isSelected();
        real[4] = chkRealS5.isSelected(); 
        real[5] = chkRealS6.isSelected();
        real[6] = chkRealS7.isSelected(); 
        real[7] = chkRealS8.isSelected();

        return nuevaActividad;
        
    }

    private void registrarActividad(ActividadReporteParcial actividad) {
        
        listaActividades.add(actividad);
        limpiarFormularioActividad();
        
    }

    @FXML
    public void eliminarActividad(ActionEvent evento) {
        
        ActividadReporteParcial actividadSeleccionada = tblActividades.getSelectionModel().getSelectedItem();
        
        if (actividadSeleccionada != null) {
            
            listaActividades.remove(actividadSeleccionada);
            limpiarFormularioActividad();
            
        }
        
    }

    private void limpiarFormularioActividad() {
        
        txtNuevaActividad.clear();
        txtTiempoPlan.clear();
        txtTiempoReal.clear();
        
        chkPlanS1.setSelected(false); 
        chkPlanS2.setSelected(false); 
        chkPlanS3.setSelected(false); 
        chkPlanS4.setSelected(false);
        chkPlanS5.setSelected(false); 
        chkPlanS6.setSelected(false); 
        chkPlanS7.setSelected(false); 
        chkPlanS8.setSelected(false);

        chkRealS1.setSelected(false); 
        chkRealS2.setSelected(false); 
        chkRealS3.setSelected(false); 
        chkRealS4.setSelected(false);
        chkRealS5.setSelected(false); 
        chkRealS6.setSelected(false); 
        chkRealS7.setSelected(false); 
        chkRealS8.setSelected(false);
        
    }
    
    @FXML
    public void generarReporte(ActionEvent evento) {
        
        if (sonDatosDeReporteValidos()) {
             
            String periodo = txtPeriodo.getText().trim();
            String resultados = taResultados.getText().trim();
            String observaciones = taObservaciones.getText().trim();
            
            ValidacionReporteParcial validacion = new ValidacionReporteParcial();
            List<String> listaValidaciones = new ArrayList<>();
            listaValidaciones = validacion.validarTamañoReporte(periodo, resultados, observaciones);
            
            if(listaValidaciones.isEmpty()){
                
                ReporteParcial reporte = crearReporte();
                guardarReporte (reporte, evento);

            }else{

                mostrarVentanaErrores(listaValidaciones);
            }

        } else {
            
            mostrarMensajeCamposFaltantesReporte();
            
        }
        
    }

    private boolean sonDatosDeReporteValidos() {
        
        boolean sonValidos = true;
        
        if (txtPeriodo.getText().isBlank() || txtHoras.getText().isBlank()) {
            sonValidos = false;
        }
        
        if (listaActividades.isEmpty()) {
            sonValidos = false;
        }
        
        if (taResultados.getText().isBlank() || taObservaciones.getText().isBlank()) {
            sonValidos = false;
        }
        
        return sonValidos;
        
    }

    private ReporteParcial crearReporte() {
        
        String periodo = txtPeriodo.getText().trim();
        int horas = Integer.parseInt(txtHoras.getText().trim());
        String resultados = taResultados.getText().trim();
        String observaciones = taObservaciones.getText().trim();
        
        ReporteParcial reporte = new ReporteParcial();
        
        reporte.setPeriodoReporteYHorasCubiertas(periodo + " / " + horas + " horas");
        reporte.setResultados(resultados);
        reporte.setObservaciones(observaciones);
        reporte.setActividades(listaActividades);
        
        return reporte;
        
    }

    private void guardarReporte(ReporteParcial reporte, ActionEvent evento) {

        try{

            GestorReporteParcial gestor = new GestorReporteParcial();
            gestor.generarReporteParcial(reporte);

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Descarga Exitosa", 
            "Reporte parcial generado correctamente.");
            
            regresar(evento);

        }catch(OperacionesDeDaoExcepcion | ProcesamientoSistemaExcepcion e){
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error generar reporte", 
            e.getMessage());

        }
        
    }

    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaGenerarEvidenciaPracticas.fxml", 
        "Generar Evidencias de Practicas");
        CerradorVentana.cerrarVentana(evento);
        
    }

    private void mostrarMensajeCamposFaltantesReporte() {
        
        VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Reporte Incompleto", 
        "Faltan datos por llenar. Por favor llene el formulario completo.");
        
    }

    private void mostrarMensajeCamposFaltantes(){
        
        VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
        "Faltan datos por agregar. Por favor ingrese todos los campos requeridos.");           
        
    }

    private void mostrarVentanaErrores(List<String> listaValidaciones) {
        
        VentanaMensaje.mostrarVentanaErrores(listaValidaciones);
    }
    
    public void cargarActividadEnFormulario(ActividadReporteParcial actividadNueva){
        
        txtNuevaActividad.setText(actividadNueva.getDescripcion());
        txtTiempoPlan.setText(String.valueOf(actividadNueva.getHorasPlaneadas()));
        txtTiempoReal.setText(String.valueOf(actividadNueva.getHorasReales()));

        boolean[] plan = actividadNueva.getSemanasPlan();
        chkPlanS1.setSelected(plan[0]); 
        chkPlanS2.setSelected(plan[1]); 
        chkPlanS3.setSelected(plan[2]); 
        chkPlanS4.setSelected(plan[3]);
        chkPlanS5.setSelected(plan[4]); 
        chkPlanS6.setSelected(plan[5]); 
        chkPlanS7.setSelected(plan[6]); 
        chkPlanS8.setSelected(plan[7]);

        boolean[] real = actividadNueva.getSemanasReal();
        chkRealS1.setSelected(real[0]); 
        chkRealS2.setSelected(real[1]); 
        chkRealS3.setSelected(real[2]); 
        chkRealS4.setSelected(real[3]);
        chkRealS5.setSelected(real[4]); 
        chkRealS6.setSelected(real[5]); 
        chkRealS7.setSelected(real[6]); 
        chkRealS8.setSelected(real[7]);
                
        
    }
   
}
