package co.natalia.parcialeco1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import co.natalia.parcialeco1.model.Coordenada;
import co.natalia.parcialeco1.model.Nombre;
import co.natalia.parcialeco1.model.TCPCliente;

public class Activity2 extends AppCompatActivity implements View.OnClickListener {

    private Button colorBtn;
    private Button upBtn;
    private Button downBtn;
    private Button leftBtn;
    private Button rightBtn;
    private TCPCliente tcpCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        //this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        colorBtn = findViewById(R.id.colorBtn);
        upBtn = findViewById(R.id.upBtn);
        downBtn = findViewById(R.id.downBtn);
        leftBtn = findViewById(R.id.leftBtn);
        rightBtn = findViewById(R.id.rightBtn);

        colorBtn.setOnClickListener(this);
        upBtn.setOnClickListener(this);
        downBtn.setOnClickListener(this);
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);

        tcpCliente = TCPCliente.getInstance();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.colorBtn:
                break;
            case R.id.upBtn:
                Gson gson = new Gson();

                Coordenada coordenada = new Coordenada();

                String msg = gson.toJson(coordenada);
                tcpCliente.SendMessage(msg);

                break;
            case R.id.downBtn:
                break;
            case R.id.rightBtn:
                break;
            case R.id.leftBtn:
                break;

        }

    }
}