/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebascreacionarchivos;

import java.io.File;
import java.nio.file.Path;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.enums.TipoDocumento;
import spp.logicadenegocio.gestores.GestorDocumentos;

/**
 *
 * @author gomes
 */
public class PruebaCreacionArchivos {
    
    @Test
    public void pruebaCrearDireccionFormatoPresentacion() {

        try {

            SesionUsuario.getInstancia().iniciarSesion(1,"Practicante","S12345678","hash");

            File archivo = File.createTempFile("prueba", ".pdf");

            GestorDocumentos gestor = new GestorDocumentos();

            Path ruta = gestor.crearDireccionArchivo(TipoDocumento.FORMATO_PRESENTACION, archivo);

            assertTrue(ruta.toString().contains("FormatoPresentacion"));

        } catch (Exception e) {

            fail();

        }

    }

    @Test
    public void pruebaCrearDireccionBitacoraPSP() {

        try {

            SesionUsuario.getInstancia().iniciarSesion(1, "Practicante", "S12345678", "hash");

            File archivo = File.createTempFile("prueba", ".pdf");

            GestorDocumentos gestor = new GestorDocumentos();

            Path ruta = gestor.crearDireccionArchivo(TipoDocumento.BITACORA_PSP, archivo);

            assertTrue(ruta.toString().contains("Bitacora_PSP"));

        } catch (Exception e) {

            fail();

        }

    }

    @Test
    public void pruebaCrearDireccionReporteParcial() {

        try {

            SesionUsuario.getInstancia().iniciarSesion(1, "Practicante", "S12345678", "hash");

            File archivo = File.createTempFile("prueba", ".pdf");

            GestorDocumentos gestor = new GestorDocumentos();

            Path ruta = gestor.crearDireccionArchivo(TipoDocumento.REPORTE_PARCIAL, archivo);

            assertTrue(ruta.toString().contains("ReportesParciales"));

        } catch (Exception e) {

            fail();

        }

    }

    @Test
    public void pruebaCrearDireccionReporteMensual() {

        try {

            SesionUsuario.getInstancia().iniciarSesion( 1, "Practicante", "S12345678", "hash");

            File archivo = File.createTempFile("prueba", ".pdf");

            GestorDocumentos gestor = new GestorDocumentos();

            Path ruta = gestor.crearDireccionArchivo(TipoDocumento.REPORTE_MENSUAL, archivo);

            assertTrue(ruta.toString().contains("ReportesMensuales"));

        } catch (Exception e) {

            fail();

        }

    }

    @Test
    public void pruebaCrearDireccionHorario() {

        try {

            SesionUsuario.getInstancia().iniciarSesion(1,  "Practicante", "S12345678",  "hash");

            File archivo = File.createTempFile("prueba", ".pdf");

            GestorDocumentos gestor = new GestorDocumentos();

            Path ruta = gestor.crearDireccionArchivo(TipoDocumento.HORARIO, archivo);

            assertTrue(ruta.toString().contains("Horario"));

        } catch (Exception e) {

            fail();

        }

    }

    @Test
    public void pruebaCrearDireccionAutoevaluacion() {

        try {

            SesionUsuario.getInstancia().iniciarSesion(1, "Practicante", "S12345678", "hash");

            File archivo = File.createTempFile("prueba", ".pdf");

            GestorDocumentos gestor = new GestorDocumentos();

            Path ruta = gestor.crearDireccionArchivo( TipoDocumento.AUTOEVALUACION, archivo);

            assertTrue(ruta.toString().contains("Autoevaluacion"));

        } catch (Exception e) {

            fail();

        }

    }

    @Test
    public void pruebaCrearDireccionPlanActividades() {

        try {

            SesionUsuario.getInstancia().iniciarSesion(1,  "Practicante", "S12345678", "hash");

            File archivo = File.createTempFile("prueba", ".pdf");

            GestorDocumentos gestor = new GestorDocumentos();

            Path ruta = gestor.crearDireccionArchivo(TipoDocumento.PLAN_ACTIVIDADES, archivo);

            assertTrue(ruta.toString().contains("PlanActividades"));

        } catch (Exception e) {

            fail();

        }

    }
    
}
