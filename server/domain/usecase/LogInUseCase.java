package server.domain.usecase;

import java.io.PrintWriter;

import server.domain.interfaces.GenericRepositoryInterface;
import server.domain.interfaces.RestInterface;
import server.domain.model.Usuario;
import server.infraestructure.server.DataThread;

public class LogInUseCase implements RestInterface {
    private GenericRepositoryInterface repository;

    public LogInUseCase (GenericRepositoryInterface repository){
        this.repository = repository;
    }

    public void responseHttp(String response, PrintWriter salida){
        salida.println(response);
        salida.flush();
    }
    @Override
    public boolean execute(PrintWriter salida, String[] args, Thread context) {
        if (args.length < 2){
            responseHttp("Debes pasar el email y password", salida);
            return false;
        }
        Usuario usuario = (Usuario)repository.findByEmailAndPassword(args[0], args[1]);
        if (usuario== null){
            responseHttp("No hay usuario con esa id", salida);
            return false;
        }
        else{
            responseHttp("Usuario logueado ", salida);
            ((DataThread)context).setLogged(true);
            return true;
        }
    }
    
}