package com.example.listadetarefas.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.listadetarefas.ArmazenamentoBancoDeDados;
import com.example.listadetarefas.R;
import com.example.listadetarefas.model.Tarefa;

public class TarefasAtuaisFragment extends Fragment {

    private RecyclerView recyclerViewTarefasAtuais;
    private TextView textViewTeste;

    private ArmazenamentoBancoDeDados bancoDeDados;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tarefas_atuais, container, false);

        recyclerViewTarefasAtuais = view.findViewById(R.id.recyclerViewTarefasAtuais);
        textViewTeste = view.findViewById(R.id.textViewTeste);

        bancoDeDados = new ArmazenamentoBancoDeDados(getActivity());

        Tarefa tarefa = bancoDeDados.getTarefa();

        textViewTeste.setText(tarefa.getTarefa());

        return view;
    }
}