package co.natalia.parcialeco1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.Random;

import co.natalia.parcialeco1.model.Color;
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
    private int x;
    private int y;
    private Gson gson;
    private Coordenada coordenada;
    private String msg;
    private int r, g, b;
    private Color color;

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
        x = 50;
        y = 50;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.colorBtn:
                gson = new Gson();

                color = new Color(r,g,b);
                color.setB((int)(Math.random()*255+1));
                color.setG((int)(Math.random()*255+1));
                color.setR((int)(Math.random()*255+1));

                msg = gson.toJson(color);
                tcpCliente.SendMessage(msg);

                break;
            case R.id.upBtn:
                gson = new Gson();
                y-=3;
                coordenada = new Coordenada(x,y);
                msg = gson.toJson(coordenada);
                tcpCliente.SendMessage(msg);

                break;
            case R.id.downBtn:
                gson = new Gson();
                y+=3;
                coordenada = new Coordenada(x,y);
                msg = gson.toJson(coordenada);
                tcpCliente.SendMessage(msg);
                break;
            case R.id.rightBtn:
                gson = new Gson();
                x+=3;
                coordenada = new Coordenada(x,y);

                msg = gson.toJson(coordenada);
                tcpCliente.SendMessage(msg);
                break;
            case R.id.leftBtn:
                gson = new Gson();
                x-=3;
                coordenada = new Coordenada(x,y);

                msg = gson.toJson(coordenada);
                tcpCliente.SendMessage(msg);
                break;

        }

    }
}