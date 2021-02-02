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
import android.widget.TextView;

import com.example.listadetarefas.ArmazenamentoBancoDeDados;
import com.example.listadetarefas.R;
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