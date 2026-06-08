package spp.presentacion.controladores.practicante;



import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import spp.logicadenegocio.clasesdto.Actividad;
import spp.logicadenegocio.clasesdto.ActividadReporteFinal;
import spp.logicadenegocio.gestores.GestorActividades;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.seleccionactividadentregable.SeleccionActividadEntregable;
import spp.utilerias.ventanademensajes.VentanaMensaje;

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

    private ObservableList<ActividadReporteFinal> listaActividadesFinales;

    @FXML
    public void initialize() {

        colAvance.setEditable(true);
        colObservaciones.setEditable(true);
        colEntregable.setEditable(true);
        
        configurarColumnas();
        cargarActividades();
        
    }

    private void configurarColumnas() {
        
        colActividad.setCellValueFactory(new PropertyValueFactory<>("nombreActividad"));

        colAvance.setCellValueFactory(new PropertyValueFactory<>("porcentajeAvance"));
        colAvance.setCellFactory(TextFieldTableCell.forTableColumn());
        colAvance.setOnEditCommit(new EdicionAvanceListener());

        colObservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        colObservaciones.setCellFactory(TextFieldTableCell.forTableColumn());
        colObservaciones.setOnEditCommit(new EdicionObservacionListener());

        colEntregable.setCellValueFactory(new SeleccionActividadEntregable());
        colEntregable.setCellFactory(CheckBoxTableCell.forTableColumn(colEntregable));
    }

    private void cargarActividades() {
        
        try {
            
            GestorActividades gestorActividades = new GestorActividades();
            List<Actividad> actividadesBD = gestorActividades.recuperarActividadesAsignadas();

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

        } catch (ReglaDeNegocioExcepcion e) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar actividades",
            "Hubo un error al recuperar las actividades de la base de datos.");
            
        }
        
    }

    private void mostrarMensaje() {
        
        VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "!UY¡", 
        "En progreso.");
        
    }

    @FXML
    public void generarReporte(ActionEvent evento) {
        
        boolean estanLlenas = actividadesLlenas();
        
        if(estanLlenas) {
            // Lógica para enviar a generar el PDF (GestorReporteFinal)
            mostrarMensaje();
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Exito", "Generando PDF...");
        } else {
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos incompletos", 
            "Asegúrate de llenar el % de avance en todas las actividades.");
        }
        
    }

    private boolean actividadesLlenas(){

        boolean estanLlenas = true;
        String valorPrederminado = "0";
        for(ActividadReporteFinal act : listaActividadesFinales) {
            if(!act.getPorcentajeAvance().equals(valorPrederminado)) {
                estanLlenas = false;
                break;
            }
        }
        return estanLlenas;
    }

    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaGenerarEvidenciaPracticas.fxml", "Generar Evidencias");
        CerradorVentana.cerrarVentana(evento);
        
    }

    private class EdicionAvanceListener implements EventHandler<CellEditEvent<ActividadReporteFinal, String>> {
        @Override
        public void handle(CellEditEvent<ActividadReporteFinal, String> evento) {
            ActividadReporteFinal actividadEditada = evento.getRowValue();
            actividadEditada.setPorcentajeAvance(evento.getNewValue());
            tblActividades.refresh();
        }
    }

    private class EdicionObservacionListener implements EventHandler<CellEditEvent<ActividadReporteFinal, String>> {
        @Override
        public void handle(CellEditEvent<ActividadReporteFinal, String> evento) {
            ActividadReporteFinal actividadEditada = evento.getRowValue();
            actividadEditada.setObservaciones(evento.getNewValue());
            tblActividades.refresh();
        }
    }

}