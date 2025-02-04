package server.infraestructure.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import server.application.Routing;

public class DataThread extends Thread {
    private Routing routing;
    private Socket socketServidor;
    private PrintWriter salida;
    private Scanner sc;
    private boolean logged  = false;
    private boolean exit = false;

    public DataThread(Socket socketServidor, Routing routing) {
        this.socketServidor = socketServidor;
        this.routing = routing;
    }

    @Override
    public void run() {
        try {
            salida = new PrintWriter(this.socketServidor.getOutputStream());
            sc = new Scanner(this.socketServidor.getInputStream());

            System.out.println("Esperando respuesta cliente");

            while (this.sc.hasNextLine()) {
              String line = this.sc.nextLine();
              InetAddress address = this.socketServidor.getInetAddress();
              System.out.printf("Recibida conexión desde %s:%d: %s%n", address.getHostAddress(), socketServidor.getPort(), line);
              this.routing.execute(salida, line, this);
              if (isExit()){
                socketServidor.close();
                salida.close();
                sc.close();
                System.exit(0);
              }
             
            }

        }catch (Exception e) {
            e.printStackTrace();
            try {
                socketServidor.close(); 
            } catch (IOException ex) {
                System.out.println("Error inesperado de entrada o salida por el servidor");
            }
        }
    }

    public boolean isLogged() {
        return this.logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isExit(){
        return this.exit;
    }

    public void setExit(){
        this.exit = true;
    }
    
}
