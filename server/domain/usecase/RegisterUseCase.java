package server.domain.usecase;

import java.io.PrintWriter;

import server.domain.interfaces.GenericRepositoryInterface;
import server.domain.interfaces.RestInterface;
import server.domain.model.Usuario;

public class RegisterUseCase implements RestInterface{
    private GenericRepositoryInterface repository;

    public RegisterUseCase (GenericRepositoryInterface repository){
        this.repository = repository;
    }

    public void responseHttp(String response, PrintWriter salida){
        salida.println(response);
        salida.flush();
    }

    @Override
    public boolean execute(PrintWriter salida, String[] args, Thread context) {
        if (args.length < 3){
            responseHttp("Necesitas el nombre, email y contraseña", salida);
            return false;
        }
        Usuario usuario = (Usuario)repository.findByEmailAndPassword(args[0], args[1]);

        if (usuario != null){
            responseHttp("Usuario ya registrado ", salida);
            return false;
        }
        else{
            repository.add(new Usuario(args));
            responseHttp("-Usuario añadido: ", salida);
            return true;
        }
    }
}
