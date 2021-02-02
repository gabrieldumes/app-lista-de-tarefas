package com.example.listadetarefas.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listadetarefas.ArmazenamentoBancoDeDados;
import com.example.listadetarefas.R;
import com.example.listadetarefas.RecyclerItemClickListener;
import com.example.listadetarefas.activity.AlterarTarefaActivity;
import com.example.listadetarefas.adapter.Adapter;
import com.example.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefasAtuaisFragment extends Fragment {

    private RecyclerView recyclerViewTarefasAtuais;

    private ArmazenamentoBancoDeDados bancoDeDados;
    private List<Tarefa> listaTarefas = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tarefas_atuais, container, false);

        recyclerViewTarefasAtuais = view.findViewById(R.id.recyclerViewTarefasAtuais);

        bancoDeDados = new ArmazenamentoBancoDeDados(getActivity());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewTarefasAtuais.setLayoutManager(layoutManager);
        recyclerViewTarefasAtuais.setHasFixedSize(true);
        recyclerViewTarefasAtuais.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));

        recyclerViewTarefasAtuais.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getActivity(),
                        recyclerViewTarefasAtuais,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Tarefa tarefa = listaTarefas.get(position);
                                Intent intent = new Intent(getActivity(), AlterarTarefaActivity.class);
                                intent.putExtra("id_tarefa", tarefa.getId());
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Tarefa tarefa = listaTarefas.get(position);
                                Toast.makeText(
                                        getActivity(),
                                        "Clique longo / ID: " + tarefa.getId(),
                                        Toast.LENGTH_SHORT
                                ).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        listaTarefas.clear();
        popularLista();
        Adapter adapter = new Adapter(this.listaTarefas);
        recyclerViewTarefasAtuais.setAdapter(adapter);
    }

    public void popularLista() {
        for (int i = 0; i < bancoDeDados.getQtdLinhas(); i++) {
            Tarefa tarefa = bancoDeDados.getTarefa(i);
            this.listaTarefas.add(tarefa);
        }
    }
}