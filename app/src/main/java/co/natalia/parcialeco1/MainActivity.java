package co.natalia.parcialeco1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import co.natalia.parcialeco1.model.Nombre;
import co.natalia.parcialeco1.model.TCPCliente;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView nameText;
    private Button continuarBtn;
    private TCPCliente tcpCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = findViewById(R.id.nameText);
        continuarBtn = findViewById(R.id.continuarBtn);

        continuarBtn.setOnClickListener(this);

        tcpCliente = TCPCliente.getInstance();

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.continuarBtn:
                Gson gson = new Gson();
                Nombre nombre = new Nombre(nameText.getText().toString());

                String msg = gson.toJson(nombre);
                tcpCliente.SendMessage(msg);

                Intent i = new Intent(this, Activity2.class);
                startActivity(i);
                break;

        }

    }
}