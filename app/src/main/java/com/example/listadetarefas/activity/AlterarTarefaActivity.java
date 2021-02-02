package com.example.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listadetarefas.ArmazenamentoBancoDeDados;
import com.example.listadetarefas.R;

public class AlterarTarefaActivity extends AppCompatActivity {

    private EditText editNovaTarefa;
    private Button buttonSalvarTarefa;

    private ArmazenamentoBancoDeDados bancoDeDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_tarefa);

        editNovaTarefa = findViewById(R.id.editNovaTarefa);
        buttonSalvarTarefa = findViewById(R.id.buttonSalvarTarefa);

        bancoDeDados = new ArmazenamentoBancoDeDados(this);

        buttonSalvarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editNovaTarefa.getText().toString().equals("")) {
                    Toast.makeText(AlterarTarefaActivity.this, "Tarefa VAZIA!", Toast.LENGTH_SHORT).show();
                } else {
                    bancoDeDados.setTarefa(editNovaTarefa.getText().toString(), 0);
                    editNovaTarefa.setText("");
                    Toast.makeText(
                            AlterarTarefaActivity.this,
                            "Tarefa salva com sucesso",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_fechar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.optionsFechar) finish();
        return super.onOptionsItemSelected(item);
    }
}