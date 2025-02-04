package server.domain.usecase;

import java.io.PrintWriter;

import server.domain.interfaces.GenericRepositoryInterface;
import server.domain.interfaces.RestInterface;
import server.domain.model.Coche;

public class CocheByMatriculaUseCase implements RestInterface{
    private GenericRepositoryInterface repository;
    
    public void responseHttp(String response, PrintWriter salida){
        salida.println(response);
        salida.flush();
    }

    public CocheByMatriculaUseCase(GenericRepositoryInterface repository) {
        this.repository = repository;
    }

    @Override
    public boolean execute(PrintWriter salida, String[] args, Thread context) {
        if (args.length < 1) {
            responseHttp("Pasa el id", salida);
            return false;
        }
        Coche cochePorMatricula = (Coche)repository.get(Integer.parseInt(args[0]));
        System.out.println(cochePorMatricula);
        if (cochePorMatricula == null) {
            responseHttp("No hay ningun coche con esa matricula", salida);
            return false;
        } else {
            responseHttp("Datos del coche por matricula: " + cochePorMatricula.toString(), salida);
            return true;
        }
    }
}
