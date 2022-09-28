package com.example.whatsapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.MessageFormat;


public class MainActivity extends AppCompatActivity {

    private TextView number;
    private TextView mensagem;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // declaração de variáveis

        number = findViewById(R.id.number);
        mensagem = findViewById(R.id.mensagem);
        send = findViewById(R.id.send);

        // ação de clicar no botão, faz a validação dos campos, caso estejam preenchidos o app prossegue normalmente.

        send.setOnClickListener(view -> {
            if(number.getText().length() == 0 || mensagem.getText().length() == 0) {
                this.showPopUp("Erro", "Campos não preenchidos devidamente, por favor, tente novamente.");
            }
            else if(number.getText().length() != 0 && !this.numberValidation()) {
                this.showPopUp("Erro", ("Número de telefone invalido: " + number.getText()));
            }
            else {
                this.sendMessage();
            }
        });
    }

    // mensagem de pop-up de erro.
    protected void showPopUp(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Tentar novamente", null);
        AlertDialog alerta = builder.create();
        alerta.show();
    }

    // validação do número.
    protected boolean numberValidation() {
        return number.getText().toString().matches("[1-9]{2}9[0-9]{4}[0-9]{4}");
    }

    // Concatenação da mensagem e número para o user ser encaminhado para o whatsapp.
    protected void sendMessage() {
        Uri whatsAppLink = Uri.parse(MessageFormat.format("https://wa.me/55{0}?text={1}", number.getText(), mensagem.getText()));
        Intent whatsApp = new Intent(Intent.ACTION_VIEW, whatsAppLink);
        startActivity(whatsApp);
    }

}