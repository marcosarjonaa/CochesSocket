package server.domain.usecase;

import java.io.PrintWriter;

import server.domain.interfaces.GenericRepositoryInterface;
import server.domain.interfaces.RestInterface;
import server.infraestructure.server.DataThread;

public class LogOutUseCase implements RestInterface {
    private GenericRepositoryInterface repository;

    public LogOutUseCase (GenericRepositoryInterface repository){
        this.repository = repository;
    }

    public void responseHttp(String response, PrintWriter pw){
        pw.println(response);
        pw.flush();
    }

    @Override
    public boolean execute(PrintWriter pw, String[] args, Thread context) {
        if (!((DataThread)context).isLogged()){
            responseHttp("Debes estar registrado!!", pw);
            return false;
        }
        ((DataThread)context).setLogged(false);
        ((DataThread)context).setExit();
        responseHttp("Usuario deslogueado", pw);
        return true;
    }
}
