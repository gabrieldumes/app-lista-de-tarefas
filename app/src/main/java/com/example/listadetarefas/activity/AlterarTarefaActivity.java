package com.example.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listadetarefas.ArmazenamentoBancoDeDados;
import com.example.listadetarefas.R;
import com.example.listadetarefas.model.Tarefa;

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

        try {
            Bundle bundle = getIntent().getExtras();
            int idTarefa = bundle.getInt("id_tarefa");
            Tarefa tarefa = bancoDeDados.getTarefaById(idTarefa);
            editNovaTarefa.setText(tarefa.getTarefa());
            buttonSalvarTarefa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editNovaTarefa.getText().toString().equals("")) {
                        Toast.makeText(AlterarTarefaActivity.this, "Tarefa VAZIA!", Toast.LENGTH_SHORT).show();
                    } else {
                        bancoDeDados.alterarTarefa(idTarefa, editNovaTarefa.getText().toString());
                        Toast.makeText(
                                AlterarTarefaActivity.this,
                                "Tarefa salva com sucesso!",
                                Toast.LENGTH_SHORT
                        ).show();
                        finish();
                    }
                }
            });
        } catch (Exception e) {
            buttonSalvarTarefa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editNovaTarefa.getText().toString().equals("")) {
                        Toast.makeText(AlterarTarefaActivity.this, "Tarefa VAZIA!", Toast.LENGTH_SHORT).show();
                    } else {
                        bancoDeDados.setTarefa(editNovaTarefa.getText().toString(), 0);
                        Toast.makeText(
                                AlterarTarefaActivity.this,
                                "Tarefa salva com sucesso!",
                                Toast.LENGTH_SHORT
                        ).show();
                        finish();
                    }
                }
            });
            Log.i("INSETO", e.getMessage());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
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