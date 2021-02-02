package com.example.listadetarefas.model;

public class Tarefa {

    private int id;
    private String tarefa;
    private int status;

    public Tarefa() {

    }

    public Tarefa(int id, String tarefa, int status) {
        this.id = id;
        this.tarefa = tarefa;
        this.status = status;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
