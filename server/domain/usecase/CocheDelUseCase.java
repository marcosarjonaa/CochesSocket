package server.domain.usecase;

import java.io.PrintWriter;

import server.domain.interfaces.GenericRepositoryInterface;
import server.domain.interfaces.RestInterface;
import server.domain.model.Coche;
import server.infraestructure.server.DataThread;

public class CocheDelUseCase implements RestInterface{
    private GenericRepositoryInterface repository;
    
    public void responseHttp(String response, PrintWriter salida){
        salida.println(response);
        salida.flush();
    }

    public CocheDelUseCase (GenericRepositoryInterface repository){
        this.repository = repository;
    }

    @Override
    public boolean execute(PrintWriter salida, String[] args, Thread context) {
        if (args.length < 1){
            responseHttp("Debes pasar el nombre del modelo", salida);
            return false;
        }

        if (!((DataThread)context).isLogged()){
            responseHttp("Debes estar registrado!!", salida);
            return false;
        }
        
        Coche delCoche = (Coche)repository.findByIdToRemove(Integer.parseInt(args[0]));
        if (delCoche == null){
            responseHttp("Ese coche no exixte", salida);
            return false;
        }
        else{
            responseHttp("Datos del coche borrado: " + delCoche.toString(), salida);
            return true;
        }
    }
}