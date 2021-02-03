package com.example.listadetarefas.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.listadetarefas.ArmazenamentoBancoDeDados;
import com.example.listadetarefas.R;
import com.example.listadetarefas.adapter.Adapter;
import com.example.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefasConcluidasFragment extends Fragment {

    private RecyclerView recyclerTarefasConcluidas;

    private List<Tarefa> listaTarefas = new ArrayList<>();
    private ArmazenamentoBancoDeDados bancoDeDados;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tarefas_concluidas, container, false);

        recyclerTarefasConcluidas = view.findViewById(R.id.recyclerTarefasConcluidas);

        bancoDeDados = new ArmazenamentoBancoDeDados(getActivity());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerTarefasConcluidas.setLayoutManager(layoutManager);
        recyclerTarefasConcluidas.setHasFixedSize(true);
        recyclerTarefasConcluidas.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        listaTarefas.clear();
        popularLista();
        Adapter adapter = new Adapter(this.listaTarefas);
        recyclerTarefasConcluidas.setAdapter(adapter);
    }

    public void popularLista() {
        for (int i = 0; i < bancoDeDados.getQtdLinhas(1); i++) {
            listaTarefas.add(bancoDeDados.getTarefa(i, 1));
        }
    }
}