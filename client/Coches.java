package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Coches {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Socket socketClient;
        int port;
        InetAddress ip_server;
        String linea;
        if(args.length!=2){
            System.out.println("Necesitas poner dos argumentos. Ip-server puerto");
            System.exit(1);
        }
        port = Integer.parseInt(args[1]);
        try {
            socketClient = new Socket(args[0], port);
            ip_server = socketClient.getInetAddress();
            System.out.println("Conectado al servidor.");
            Scanner in = new Scanner(socketClient.getInputStream());
            PrintWriter out = new PrintWriter(socketClient.getOutputStream());
            Boolean sal=false;
            String respuesta;

            do{
                System.out.print("shell coche>");
                linea = sc.nextLine();
                out.println(linea);
                out.flush();

                if (linea.equals("fin")) {
                    sal=true;
                    socketClient.close();
                    out.close();
                    sc.close();
                }else{
                    respuesta = in.nextLine();
                    System.out.println(respuesta);
                }

            }while(!sal);
            sc.close();
        } catch (UnknownHostException ex){
            System.out.printf("Servidor desconocido %s%n", args[0]);
            ex.printStackTrace();
            System.exit(1);
        } catch (IOException e){
            System.out.println("Error en flujo de E/S");
            e.printStackTrace();
            System.exit(1);    
        }
    }
}