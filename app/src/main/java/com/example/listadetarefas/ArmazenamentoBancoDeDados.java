package com.example.listadetarefas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.listadetarefas.model.Tarefa;

public class ArmazenamentoBancoDeDados {

    private Context context;
    private SQLiteDatabase database;

    public ArmazenamentoBancoDeDados(Context context) {
        this.context = context;
        try {
            database = context.openOrCreateDatabase("app_db", Context.MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS tarefas(id INTEGER " +
                    "PRIMARY KEY AUTOINCREMENT, tarefa VARCHAR, status INTEGER)");
        } catch (Exception e) {
            Log.i("INSETO ", e.getMessage());
        }
    }

    public void addTarefa(String tarefa, int status) {
        try {
            database.execSQL("INSERT INTO tarefas(tarefa, status) VALUES ('" + tarefa + "', "+ status +")");
        } catch (Exception e) {
            Log.i("INSETO ", e.getMessage());
        }
    }

    public Tarefa getTarefa(int position, int status) {
        try {
            Cursor cursor = database.rawQuery("SELECT id, tarefa, status FROM tarefas WHERE status = " + status, null);
            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaTarefa = cursor.getColumnIndex("tarefa");
            int indiceColunaStatus = cursor.getColumnIndex("status");
            cursor.moveToPosition(position);
            return new Tarefa(
                    cursor.getInt(indiceColunaId),
                    cursor.getString(indiceColunaTarefa),
                    cursor.getInt(indiceColunaStatus)
            );
        } catch (Exception e) {
            Log.i("INSETO ", e.getMessage());
            return new Tarefa(-1, "Erro", 1111);
        }
    }

    public Tarefa getTarefaById(int id) {
        try {
            String sql = "SELECT id, tarefa, status FROM tarefas WHERE id = " + id;
            Cursor cursor = database.rawQuery(sql, null);
            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaTarefa = cursor.getColumnIndex("tarefa");
            int indiceColunaStatus = cursor.getColumnIndex("status");
            cursor.moveToFirst();
            return new Tarefa(
                    cursor.getInt(indiceColunaId),
                    cursor.getString(indiceColunaTarefa),
                    cursor.getInt(indiceColunaStatus)
            );
        } catch (Exception e) {
            Log.i("INSETO ", e.getMessage());
            return new Tarefa(-1, "Erro", 1111);
        }
    }

    public void alterarTarefa(int id, String tarefa) {
        try {
            database.execSQL("UPDATE tarefas SET tarefa = '"+ tarefa + "' WHERE id = " + id);
        } catch (Exception e) {
            Log.i("INSETO ", e.getMessage());
        }
    }

    public void removerTarefa(int id) {
        try {
            database.execSQL("DELETE FROM tarefas WHERE id = " + id);
        } catch (Exception e) {
            Log.i("INSETO ", e.getMessage());
        }
    }

    public boolean marcarTarefaConcluido(int id) {
        try {
            database.execSQL("UPDATE tarefas SET status = 1 WHERE id = " + id);
            return true;
        } catch (Exception e) {
            Log.i("INSETO ", e.getMessage());
            return false;
        }
    }

    public int getQtdLinhas(int status) {
        try {
            Cursor cursor = database.rawQuery("SELECT id, tarefa, status FROM tarefas WHERE status = " + status, null);
            return cursor.getCount();
        } catch (Exception e) {
            Log.i("INSETO ", e.getMessage());
            return 0;
        }
    }
}
