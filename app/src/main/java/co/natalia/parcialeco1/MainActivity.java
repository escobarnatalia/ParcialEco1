package co.natalia.parcialeco1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView nameText;
    private Button continuarBtn;

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = findViewById(R.id.nameText);
        continuarBtn = findViewById(R.id.continuarBtn);

        continuarBtn.setOnClickListener(this);

        initClient();
    }

    public void initClient() {
        new Thread(() -> {
            try {
                Log.e("hi", "practicandolalloracionconjhon");
                // 2. enviando conexion
                socket = new Socket("192.168.0.4", 7000);
                // 3.cliente y servidor conectados
                Log.e("666","Conectados");

                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                reader = new BufferedReader(isr);

                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);

                writer = new BufferedWriter(osw);

                while (true) {
                    System.out.println("Esperando...");
                    String line = reader.readLine();
                    Log.e("666", "Recibido: " + line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        ).start();
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

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.continuarBtn:
                Intent i = new Intent(this, Activity2.class);
                startActivity(i);
                break;

        }

    }
}