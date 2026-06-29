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
    /**
     * 
     * @param idPracticante Id del Practicante al que esta asignado el documento.
     * @param tipoDocumento Tipo de documento que se tiene registrado y se busca contar la cantidad.
     * @return Valor {@code int} que indica la cantidad encontrada de documentos. Comenzando desde 0.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public int contarDocumentosPorTipo(int idPracticante, TipoDocumento tipoDocumento) throws OperacionesDeDaoExcepcion;
    /**
     * 
     * @param idPracticante Id del Practicante al que esta asignado el documento.
     * @param tipoDocumento Tipo de documento que se pretende verificar que exista.
     * @return Valor {@code true} que indica que el documento existe para ese practicante. Para el 
     * caso de el valor {@code false} indica que el documento no existe para ese practicante.  
     * @throws OperacionesDeDaoExcepcion
     */
    public boolean verificarExistenciaDocumento(int idPracticante, TipoDocumento tipoDocumento) throws OperacionesDeDaoExcepcion;
    /**
     * 
     * @param idPracticante Id del Practicante al que esta asignado el documento.
     * @param tipoDocumento Tipo de documento que se quiere encontar para contar la cantidad de ellos.
     * @return Valor {@code int} que indica la cantidad encontrada de documentos calificados. Comenzando desde 0.
     * @throws OperacionesDeDaoExcepcion
     */
    public int contarDocumentosCalificadosPorTipo(int idPracticante, TipoDocumento tipoDocumento) throws OperacionesDeDaoExcepcion;

    public boolean verificarDocumentoAprobado(int idPracticante, TipoDocumento tipoDocumento) throws OperacionesDeDaoExcepcion;    
    /**
     * 
     * @param documento Objeto que contiene la información necesaria para actualizar el estado del documento.
     * @return {@code true} si el documento se acualizo el estado del documento correctamente 
     * {@code false} en caso de que no se haya actualizado el estado.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean actualizarEstadoDocumento(Documento documento) throws OperacionesDeDaoExcepcion;

    public Documento buscarDocumentoPorUsuarioYTipo(int idUsuario, String tipo) throws OperacionesDeDaoExcepcion;

    public boolean eliminarDocumentoPorId(int idDocumento) throws OperacionesDeDaoExcepcion; 

}
