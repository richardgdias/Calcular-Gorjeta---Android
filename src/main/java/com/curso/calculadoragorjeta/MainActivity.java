package com.curso.calculadoragorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText textInputValor;
    private TextView textProgresso, textGorjeta, textTotal;
    private SeekBar seekBarProgresso;

    private double porcentagem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInputValor = findViewById(R.id.textInputValor);
        textProgresso = findViewById(R.id.textProgresso);
        textGorjeta = findViewById(R.id.textGorjeta);
        textTotal = findViewById(R.id.textTotal);
        seekBarProgresso = findViewById(R.id.seekBarProgresso);

        //Listener para seekBar
        seekBarProgresso.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentagem = progress;
                textProgresso.setText(Math.round(porcentagem) + " %"); // round arredonda um numero com ,
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void calcular(){
        String valorRecuperado = textInputValor.getText().toString();
        if (valorRecuperado == null || valorRecuperado.equals("")){ // se o valor for nullo ou vazio
            Toast.makeText(getApplicationContext(), "Digite um valor primeiro", Toast.LENGTH_LONG).show();
        }
        else {
            double valorDigitado = Double.parseDouble(valorRecuperado); // convertendo string para double
            double gorjeta = valorDigitado * (porcentagem/100);
            double total = gorjeta + valorDigitado;
            //textGorjeta.setText("R$ " + Math.round(gorjeta)); // round arredonda o valor
            textGorjeta.setText("R$ " + gorjeta);
            textTotal.setText("R$ " + total);
        }
    }
}
