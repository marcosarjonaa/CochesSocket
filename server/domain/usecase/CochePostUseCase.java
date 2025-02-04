package server.domain.usecase;

import java.io.PrintWriter;

import server.domain.interfaces.GenericRepositoryInterface;
import server.domain.interfaces.RestInterface;
import server.domain.model.Coche;
import server.infraestructure.server.DataThread;

public class CochePostUseCase implements RestInterface{
    private GenericRepositoryInterface repository;
    public void responseHttp(String response, PrintWriter pw){
        pw.println(response);
        pw.flush();
    }
    public CochePostUseCase(GenericRepositoryInterface repository){
        this.repository = repository;
    }
    @Override
    public boolean execute(PrintWriter salida, String[] args, Thread context) {
        if (args.length < 2){
            responseHttp("Debes pasar el nombre del modelo", salida);
            return false;
        }

        if (!((DataThread)context).isLogged()){
            responseHttp("Debes estar registrado", salida);
            return false;
        }
        
        Coche addCo = new Coche();
        addCo.setModelo(args[0]);
        addCo.setMarca(args[1]);
        repository.add((Coche)addCo);
        responseHttp("Coche añadido: ", salida);
        return true;
    }

}
