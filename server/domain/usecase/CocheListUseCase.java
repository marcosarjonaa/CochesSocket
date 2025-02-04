package server.domain.usecase;

import java.io.PrintWriter;
import java.util.List;

import server.domain.interfaces.GenericRepositoryInterface;
import server.domain.interfaces.RestInterface;
import server.domain.model.Coche;

public class CocheListUseCase implements RestInterface{
    private GenericRepositoryInterface repository;
    
    public void responseHttp(String response, PrintWriter salida){
        salida.println(response);
        salida.flush();
    }

    public CocheListUseCase(GenericRepositoryInterface repository){
        this.repository = repository;
    }

    @Override
    public boolean execute(PrintWriter salida, String[] args, Thread context) {
        List<Coche> coches = (List<Coche>)repository.getAll();
        responseHttp("Datos:" + coches.toString(), salida);
        return true;
    }
    
}
