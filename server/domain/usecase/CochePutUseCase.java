package server.domain.usecase;

import java.io.PrintWriter;

import client.Coches;
import server.domain.interfaces.GenericRepositoryInterface;
import server.domain.interfaces.RestInterface;
import server.domain.model.Coche;
import server.infraestructure.server.DataThread;

public class CochePutUseCase implements RestInterface{
    private GenericRepositoryInterface repository;
    
    public void responseHttp(String response, PrintWriter salida){
        salida.println(response);
        salida.flush();
    }

    public CochePutUseCase(GenericRepositoryInterface repository){
        this.repository = repository;
    }

    @Override
    public boolean execute(PrintWriter salida, String[] args, Thread context) {
        if (args.length < 1){
            responseHttp("Debes pasar el nombre del modelo", salida);
            return false;
        }
        try {
            int id = Integer.parseInt(args[0]);
            if (!((DataThread)context).isLogged()){
            responseHttp("Debes estar registrado primero", salida);
            return false;
            }

            Coche cocheActualizado = new Coche();
            cocheActualizado.setMatricula(id);
            cocheActualizado.setModelo(args[1]);
            cocheActualizado.setMarca(args[2]); 
            repository.put(cocheActualizado);
            responseHttp("Coche actualizado.", salida);
            return true;
        }catch (Exception e) {
            responseHttp("Error al actualizar el coche: " + e.getMessage(), salida);
            return false;
        }
    }
}
