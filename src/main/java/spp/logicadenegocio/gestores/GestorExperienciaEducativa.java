package spp.logicadenegocio.gestores;

import java.util.ArrayList;
import java.util.List;

import spp.logicadenegocio.clasesdao.ExperienciaEducativaDAO;
import spp.logicadenegocio.clasesdao.ReferenciaCursoDAO;
import spp.logicadenegocio.clasesdto.ExperienciaEducativa;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.clasesdto.ReferenciaCurso;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

public class GestorExperienciaEducativa {

    public List<ExperienciaEducativa> buscarExperienciasEducativasActivas() throws OperacionesDeDaoExcepcion{

        List<ExperienciaEducativa> listaDeExperiencia = new ArrayList<>(); 
        ExperienciaEducativaDAO experienciaDao = new ExperienciaEducativaDAO();
        listaDeExperiencia = experienciaDao.consultarExperienciasEducativasActivas();
        
        if(listaDeExperiencia != null && !listaDeExperiencia.isEmpty()){

            for(ExperienciaEducativa experiencia : listaDeExperiencia ){

                int idReferenciaCurso = experiencia.getIdReferenciaCurso();
                experiencia.setNrc(buscarReferenciaCurso(idReferenciaCurso));

            }
            
        }

        return listaDeExperiencia;

    }

    private String buscarReferenciaCurso(int idReferenciaCurso) throws OperacionesDeDaoExcepcion{
        
        ReferenciaCursoDAO referenciaCursoDao = new ReferenciaCursoDAO();
        ReferenciaCurso nrcCompleto = new ReferenciaCurso();
        String nrcReferenciado;

        nrcCompleto = referenciaCursoDao.consultarReferenciaCursoPorId(idReferenciaCurso);
        nrcReferenciado= nrcCompleto.getNrc();
        
        return nrcReferenciado;
    }

    public boolean asignarProfesorAEE(Practicante practicante, ExperienciaEducativa experiencia) throws OperacionesDeDaoExcepcion{
        
        boolean asignacionExitosa = false; 
        ExperienciaEducativaDAO experienciaDao = new ExperienciaEducativaDAO();
        String matricula = practicante.getMatricula();
        int idExperienciaEducativa = experiencia.getIdExperienciaEducativa();

        asignacionExitosa = experienciaDao.asignarExperienciaEducativaAPracticante(idExperienciaEducativa, matricula);

        return asignacionExitosa;

    }
    
    public boolean asignarProfesorAEE(Profesor profesor, ExperienciaEducativa experiencia) throws OperacionesDeDaoExcepcion{
        
        boolean asignacionExitosa = false; 
        ExperienciaEducativaDAO experienciaDao = new ExperienciaEducativaDAO();
        String numeroPersonal = profesor.getNumeroDePersonal();
        int idExperienciaEducativa = experiencia.getIdExperienciaEducativa();

        asignacionExitosa = experienciaDao.asignarExperienciaEducativaAProfesor(idExperienciaEducativa, numeroPersonal);

        return asignacionExitosa;

    }

    public List<ExperienciaEducativa> buscarExperienciasEducativasSinProfesor() throws OperacionesDeDaoExcepcion {
        
        ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
        
        return experienciaEducativaDAO.consultarExperienciasEducativasSinProfesor();
        
    }
    
}
