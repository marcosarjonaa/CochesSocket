package server.domain.usecase;

import java.io.PrintWriter;

import server.domain.interfaces.GenericRepositoryInterface;
import server.domain.interfaces.RestInterface;
import server.domain.model.Coche;

public class CocheByModelUseCase implements RestInterface{
    private GenericRepositoryInterface repository;
    
    public void responseHttp(String response, PrintWriter pw){
        pw.println(response);
        pw.flush();
    }

    public CocheByModelUseCase(GenericRepositoryInterface repository) {
        this.repository = repository;
    }



    @Override
    public boolean execute(PrintWriter pw, String[] args, Thread context) {

        if (args.length < 1) {
            responseHttp("Debes pasar el nombre del modelo", pw);
            return false;
        }
    
        Coche cochePorModelo = (Coche)repository.findByModelo(args[0]);
        System.out.println(cochePorModelo);
    
        if (cochePorModelo == null) {
            responseHttp("Ese coche no existe con ese modelo", pw);
            return false;
        } else {
            responseHttp("Datos del coche: " + cochePorModelo.toString(), pw);
            return true;
        }
    }
    
}
