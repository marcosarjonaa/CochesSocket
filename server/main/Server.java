package server.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import server.application.Routing;
import server.infraestructure.server.DataThread;

public class Server {
    private static Routing routing;
    
    public static void main(String[] args) {
        int puertoServidor=0;

        if (args.length == 0) {
            System.out.println("Debes pasar el puerto");
            System.exit(1);
        }
        try{
            puertoServidor = Integer.parseInt(args[0]);
        }catch(NumberFormatException e){
            System.out.println("Error en el puerto");
            System.exit(1);
        }
        routing = new Routing();
        System.out.println("Servidor a la escucha");
        try (ServerSocket serverSocket = new ServerSocket(puertoServidor)) {
            while (true) {
                Socket socketClient = serverSocket.accept();
                System.out.printf("Establecida conexión con"+
                    socketClient.getInetAddress()+socketClient.getPort()
                );
                new DataThread(socketClient,  routing).start();
            }
        } catch (IOException e) {
             e.printStackTrace();
        }
     
    }
}
