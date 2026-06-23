package spp.presentacion.controladores.practicante;



import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import spp.logicadenegocio.clasesdto.Actividad;
import spp.logicadenegocio.clasesdto.ActividadReporteFinal;
import spp.logicadenegocio.clasesdto.ReporteFinal;
import spp.logicadenegocio.gestores.GestorActividades;
import spp.logicadenegocio.gestores.GestorReporteFinal;
import spp.logicadenegocio.validaciones.validacionesreportes.ValidacionReporteFinal;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.numerosentablas.FabricaCeldaNumerica;
import spp.utilerias.selecciones.seleccionactividadentregable.SeleccionActividadEntregable;
import spp.utilerias.selecciones.seleccionesreportefinal.EdicionAvanceListener;
import spp.utilerias.selecciones.seleccionesreportefinal.EdicionObservacionListener;
import spp.utilerias.validadorsoloenteros.ValidadorEnteros;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

public class ControladorFormularioReporteFinal {

    @FXML 
    private TableView<ActividadReporteFinal> tblActividades;
    
    @FXML 
    private TableColumn<ActividadReporteFinal, String> colActividad;
    
    @FXML 
    private TableColumn<ActividadReporteFinal, String> colAvance;
   
    @FXML 
    private TableColumn<ActividadReporteFinal, String> colObservaciones;
    
    @FXML 
    private TableColumn<ActividadReporteFinal, Boolean> colEntregable;
    
    @FXML 
    private TextArea taObservacionesGenerales;

    private ObservableList<ActividadReporteFinal> listaActividadesFinales;

    @FXML
    public void initialize() {

        colAvance.setEditable(true);
        colObservaciones.setEditable(true);
        colEntregable.setEditable(true);
        
        configurarColumnas();
        cargarActividades();
        
    }

    @FXML
    private void validarPorcentajeAvance(KeyEvent evento) {

        TextField campoTexto = (TextField) evento.getSource();
        ValidadorEnteros.validarSoloNumeros(campoTexto);
     
    }

    private void configurarColumnas() {
        
        colActividad.setCellValueFactory(new PropertyValueFactory<>("nombreActividad"));

        colAvance.setCellValueFactory(new PropertyValueFactory<>("porcentajeAvance"));
        colAvance.setCellFactory(new FabricaCeldaNumerica());
        colAvance.setOnEditCommit(new EdicionAvanceListener(tblActividades));

        colObservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        colObservaciones.setCellFactory(TextFieldTableCell.forTableColumn());
        colObservaciones.setOnEditCommit(new EdicionObservacionListener(tblActividades));

        colEntregable.setCellValueFactory(new SeleccionActividadEntregable());
        colEntregable.setCellFactory(CheckBoxTableCell.forTableColumn(colEntregable));
    }

    private void cargarActividades() {
        
        try {
            
            GestorActividades gestorActividades = new GestorActividades();
            List<Actividad> actividadesBD = gestorActividades.consultarActividadesAsignadas();

            List<ActividadReporteFinal> listaActividades = new ArrayList<>();

            String valorPredeterminadoAvance = "0";
            String valorPredeterminadoObservaciones = "Ninguna";
            boolean valorPredeterminadoEsEntregable = false;
            for (Actividad actividad : actividadesBD) {
                ActividadReporteFinal actFinal = new ActividadReporteFinal();
                actFinal.setNombreActividad(actividad.getTitulo()); 
                actFinal.setPorcentajeAvance(valorPredeterminadoAvance); 
                actFinal.setObservaciones(valorPredeterminadoObservaciones); 
                actFinal.setEsEntregable((valorPredeterminadoEsEntregable));
                
                listaActividades.add(actFinal);
            }

            listaActividadesFinales = FXCollections.observableArrayList(listaActividades);
            tblActividades.setItems(listaActividadesFinales);

        } catch (OperacionesDeDaoExcepcion e) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar actividades",
            "Hubo un error al recuperar las actividades de la base de datos.");
            
        }
        
    }

    @FXML
    public void generarReporte(ActionEvent evento) throws OperacionesDeDaoExcepcion {
        
        if (sonDatosGeneralesValidos()) {
            
            ReporteFinal reporte = crearReporteFinal();
            
            try {
                
                ValidacionReporteFinal validacion = new ValidacionReporteFinal();
                validacion.validarDatosReporte(reporte);
                
                GestorReporteFinal gestor = new GestorReporteFinal();
                gestor.generarReporteFinal(reporte); 
                
                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Descarga Exitosa", 
                "Reporte Final generado correctamente.");
                
                regresar(evento);
                
            } catch (ReglaDeNegocioExcepcion e) {
                
                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Error de validación", 
                e.getMessage());
                
            } catch (ProcesamientoSistemaExcepcion e) {
                
                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error del Sistema", 
                e.getMessage());
                
            }
            
        }
        
    }

    private boolean sonDatosGeneralesValidos() {
        
        boolean sonValidos = true;
        
        if (taObservacionesGenerales.getText().trim().isEmpty()) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos incompletos", 
            "Por favor, llena el campo de observaciones generales.");
            sonValidos = false;
            
        } else if (!actividadesLlenas()) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos incompletos", 
            "Asegúrate de llenar el porcentaje de avance en todas las actividades de la tabla.");
            sonValidos = false;
            
        }
        
        return sonValidos;
        
    }

    private boolean actividadesLlenas(){

        boolean estanLlenas = true;
        String valorPrederminado = "0";
        for(ActividadReporteFinal act : listaActividadesFinales) {
            if(act.getPorcentajeAvance().equals(valorPrederminado)) {
                estanLlenas = false;
                break;
            }
        }
        return estanLlenas;
    }

    private ReporteFinal crearReporteFinal(){

        ReporteFinal reporte = new ReporteFinal();
        
        reporte.setObservaciones(taObservacionesGenerales.getText().trim());
        
        List<ActividadReporteFinal> soloActividades = new ArrayList<>();
        List<ActividadReporteFinal> soloEntregables = new ArrayList<>();

        for(ActividadReporteFinal act : listaActividadesFinales) {
            if(act.getEsEntregable()) {
                soloEntregables.add(act);
            } else {
                soloActividades.add(act);
            }
        }

        reporte.setActividades(soloActividades);
        reporte.setEntregables(soloEntregables);
        
        return reporte;
    }

    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaGenerarEvidenciaPracticas.fxml", "Generar Evidencias");
        CerradorVentana.cerrarVentana(evento);
        
    }

}