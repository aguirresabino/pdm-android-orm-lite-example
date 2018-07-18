package com.example.aguirresabino.ormliteexemplocrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aguirresabino.ormliteexemplocrud.db.DBConfig;
import com.example.aguirresabino.ormliteexemplocrud.model.LivroModel;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class InsertActivity extends AppCompatActivity {

    private DBConfig dbConfig = null;
    private EditText addTitulo, addAutor;
    private Button addLivro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        addTitulo = (EditText)findViewById(R.id.addTitulo);
        addAutor = (EditText)findViewById(R.id.addAutor);
        addLivro = (Button)findViewById(R.id.addLivro);

        addLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addTitulo.getText().toString().trim().length() > 0 &&
                        addAutor.getText().toString().trim().length() > 0){
                    LivroModel livroModel = new LivroModel();
                    livroModel.titulo = addTitulo.getText().toString();
                    livroModel.autor = addAutor.getText().toString();

                    try {
                        final Dao<LivroModel, Integer> informationDao =
                                getDB().getLivroDao();
                        informationDao.create(livroModel);
                        addTitulo.setText("");
                        addAutor.setText("");
                    } catch (java.sql.SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    private DBConfig getDB() {
        if (dbConfig == null) {
            dbConfig = OpenHelperManager.getHelper(this, DBConfig.class);
        }
        return dbConfig;
    }
}
