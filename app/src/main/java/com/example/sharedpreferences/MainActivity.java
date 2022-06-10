package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button btn_salvar;
    private TextInputEditText edt_nome;
    private TextView txt_resultado;
    private final static String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_salvar = findViewById(R.id.btn_salvar);
        txt_resultado = findViewById(R.id.txt_resultado);
        edt_nome = findViewById(R.id.edt_nome);

        btn_salvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                //preferences.getAll().keySet(); util pra atividade em dupla

                if(!edt_nome.getText().toString().isEmpty()){
                    String nome = edt_nome.getText().toString();
                    editor.putString("nome", nome);
                    editor.commit();
                    txt_resultado.setText("Olá, "+nome);
                }else{
                    Toast.makeText(getApplicationContext(), "Preencha o campo!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, MODE_PRIVATE);
        if (preferences.contains("nome")){
            String nome = preferences.getString("nome", "");
            txt_resultado.setText("Olá de novo, " + nome);
        }else{
            txt_resultado.setText("Usuário não definido");
        }


    }
}