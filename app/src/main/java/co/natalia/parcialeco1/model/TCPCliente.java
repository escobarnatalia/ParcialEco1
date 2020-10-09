package co.natalia.parcialeco1.model;

import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPCliente extends Thread {
    // Objeto unico que se va a usar en todas las demás sesiones
    protected static TCPCliente instanciaUnica;

    // Constructor inaccesible por medios normales
    private TCPCliente() {

    }

    // crear y llama el valor unico para las demás clases
    public static TCPCliente getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new TCPCliente();
            instanciaUnica.start();
        }
        return instanciaUnica;

    }

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    @Override
    public void run() {
        try {
            // 2
            socket = new Socket("10.0.2.2", 7000);
            System.out.println("Esperando conexion");

            // 3.cliente y servidor conectados
            System.out.println("Cliente conectado");

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            reader = new BufferedReader(isr);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);

            writer = new BufferedWriter(osw);

            while (true) {
                System.out.println("Esperando...");
                String line = reader.readLine();
                System.out.println("Recibido: " + line);



            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SendMessage(String msg) {
        new Thread(() -> {
            try {
                writer.write(msg + "\n");
                writer.flush();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }).start();
        {

        }
    }
}
