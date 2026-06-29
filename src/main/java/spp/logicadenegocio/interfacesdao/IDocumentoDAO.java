/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.Documento;
import spp.logicadenegocio.enums.TipoDocumento;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 * Define las operaciones de acceso a datos para la gestión de documentos.
 *
 * Proporciona los métodos necesarios para registrar, consultar y eliminar
 * documentos dentro del sistema.
 *
 * @author gomes
 */
public interface IDocumentoDAO {
    /**
     * Inserta un documento en el sistema.
     *
     * @param documento Documento que se desea registrar.
     * @return {@code true} si el documento se registró correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean insertarDocumento(Documento documento)throws OperacionesDeDaoExcepcion;
    /**
     * Consulta un documento a partir de su nombre.
     *
     * @param nombre Nombre del documento que se desea consultar.
     * @return Objeto {@code Documento} con la información encontrada o
     * {@code null} si no existe un documento con el nombre proporcionado.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public Documento consultarDocumento(String nombre)throws OperacionesDeDaoExcepcion;
    /**
     * Elimina un documento del sistema.
     *
     * @param nombre Nombre del documento que se desea eliminar.
     * @return {@code true} si el documento fue eliminado correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean eliminarDocumento(String nombre) throws OperacionesDeDaoExcepcion;
    public int contarDocumentosPorTipo(int idPracticante, TipoDocumento tipoDocumento) throws OperacionesDeDaoExcepcion;
    public boolean verificarExistenciaDocumento(int idPracticante, TipoDocumento tipoDocumento) throws OperacionesDeDaoExcepcion;
    public int contarDocumentosCalificadosPorTipo(int idPracticante, TipoDocumento tipoDocumento) throws OperacionesDeDaoExcepcion;
    public boolean verificarDocumentoAprobado(int idPracticante, TipoDocumento tipoDocumento) throws OperacionesDeDaoExcepcion;
}
