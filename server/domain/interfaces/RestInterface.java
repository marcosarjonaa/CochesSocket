package server.domain.interfaces;

import java.io.PrintWriter;

import server.infraestructure.server.DataThread;

public interface RestInterface {
    public boolean execute(PrintWriter salida, String [] args, Thread context);
}
