package spp.logicadenegocio.gestores;

import java.util.ArrayList;
import java.util.List;

import spp.logicadenegocio.clasesdao.ExperienciaEducativaDAO;
import spp.logicadenegocio.clasesdto.ExperienciaEducativa;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

public class GestorExperienciaEducativa {

    public List<ExperienciaEducativa> buscarExperienciasEducativasActivas() throws OperacionesDeDaoExcepcion{

        List<ExperienciaEducativa> experiencia = new ArrayList<>(); 
        ExperienciaEducativaDAO experienciaDao = new ExperienciaEducativaDAO();
        experiencia = experienciaDao.consultarExperienciasEducativasActivas();

        return experiencia;
    }

    public boolean asignarExperienciaAlPracticante(Practicante practicante, ExperienciaEducativa experiencia) throws OperacionesDeDaoExcepcion{
        
        boolean asignacionExitosa = false; 
        ExperienciaEducativaDAO experienciaDao = new ExperienciaEducativaDAO();
        String matricula = practicante.getMatricula();
        int idExperienciaEducativa = experiencia.getIdExperienciaEducativa();

        asignacionExitosa = experienciaDao.asignarExperienciaEducativaAPracticante(idExperienciaEducativa, matricula);

        return asignacionExitosa;

    }

}
