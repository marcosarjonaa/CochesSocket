package server.application;

import java.io.PrintWriter;
import java.util.HashMap;

import server.data.repository.Repository;
import server.domain.interfaces.GenericRepositoryInterface;
import server.domain.interfaces.RestInterface;
import server.domain.usecase.CocheByMatriculaUseCase;
import server.domain.usecase.CocheByModelUseCase;
import server.domain.usecase.CocheDelUseCase;
import server.domain.usecase.CocheListUseCase;
import server.domain.usecase.CochePostUseCase;
import server.domain.usecase.CochePutUseCase;
import server.domain.usecase.LogInUseCase;
import server.domain.usecase.LogOutUseCase;
import server.domain.usecase.RegisterUseCase;
import server.infraestructure.server.DataThread;

public class Routing {

    private final HashMap<String, RestInterface> managerEndPoints;
    private final Repository repository;
    
    public Routing(){
        this.repository = new Repository<>();
        managerEndPoints = new HashMap<>();
        managerEndPoints.put("getAll", new CocheListUseCase(repository));
        managerEndPoints.put("model", new CocheByModelUseCase(repository));
        managerEndPoints.put("put", new CochePutUseCase(repository) );
        managerEndPoints.put("post", new CochePostUseCase(repository));
        managerEndPoints.put("delete", new CocheDelUseCase(repository));
        managerEndPoints.put("get", new CocheByMatriculaUseCase(repository));
        managerEndPoints.put("log", new LogInUseCase(repository));
        managerEndPoints.put("deslog", new LogOutUseCase(repository));
        managerEndPoints.put("addUser", new RegisterUseCase(repository));
    }

    public void responseHttp(String response, PrintWriter pw){
        pw.println(response);
        pw.flush();
    }
    public boolean execute(PrintWriter pw, String body, Thread context){
        System.out.println (body);
        String [] args = body.split(" ");
        try {
            String verb  = args[0];
            RestInterface endPoint = managerEndPoints.get(verb);

            if (endPoint == null){
                responseHttp("Error, debes pasar un comando válido", pw);
                return false;
            }
            String [] operationsArgs = new String[args.length - 1]; 
            System.arraycopy(args, 1,  operationsArgs, 0, args.length - 1);
            return(endPoint.execute(pw, operationsArgs, context));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

