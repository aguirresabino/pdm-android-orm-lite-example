package com.example.aguirresabino.ormliteexemplocrud;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.aguirresabino.ormliteexemplocrud.db.DBConfig;
import com.example.aguirresabino.ormliteexemplocrud.model.LivroArrayAdapter;
import com.example.aguirresabino.ormliteexemplocrud.model.LivroModel;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.util.List;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener{

    private DBConfig dbConfig = null;
    private ListView listView;
    private int selectPosition = -1;
    private Dao<LivroModel, Integer> livroDao;
    private List<LivroModel> livros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView)findViewById(R.id.listview);

        try {
            livroDao = getDB().getLivroDao();
            livros = livroDao.queryForAll();
            LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.list_view, listView,false);
            listView.setAdapter(new LivroArrayAdapter(this, R.layout.list_view, livros, livroDao));
            listView.addHeaderView(view);
            listView.setOnItemLongClickListener(this);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    private DBConfig getDB() {
        if (dbConfig == null) {
            dbConfig = OpenHelperManager.getHelper(this, DBConfig.class);
        }
        return dbConfig;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(i > 0)
        {
            selectPosition = i - 1;
            showDialog();
        }
        return false;
    }

    private void showDialog() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Deseja deletar o livro?");
        alertDialogBuilder.setTitle("Deletar");
        alertDialogBuilder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    livroDao.delete(livros.get(selectPosition));
                    livros.remove(selectPosition);
                    listView.invalidateViews();
                    selectPosition = -1;
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
